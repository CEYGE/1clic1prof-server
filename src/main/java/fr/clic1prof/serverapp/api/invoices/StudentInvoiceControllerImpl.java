package fr.clic1prof.serverapp.api.invoices;

import fr.clic1prof.serverapp.file.exceptions.DocumentNotFoundException;
import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.FileStored;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.UserRole;
import fr.clic1prof.serverapp.service.invoices.StudentInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Secured(UserRole.Names.STUDENT)
public class StudentInvoiceControllerImpl implements StudentInvoiceController {

    private final StudentInvoiceService service;

    @Autowired
    public StudentInvoiceControllerImpl(StudentInvoiceService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<DocumentModel>> getInvoices(UserBase user) {

        List<DocumentModel> invoices = this.service.getInvoices(user.getId());

        return ResponseEntity.ok(invoices);
    }

    @Override
    public ResponseEntity<Resource> getInvoice(UserBase user, int invoiceId) {

        if(!this.service.hasInvoice(user.getId(), invoiceId))
            throw new DocumentNotFoundException(String.format("No invoice with id %d found.", invoiceId));

        Optional<FileStored> optional = this.service.getInvoice(invoiceId);

        if(!optional.isPresent())
            throw new DocumentNotFoundException(String.format("No invoice with id %d found.", invoiceId));

        FileStored invoice = optional.get();

        ContentDisposition disposition = ContentDisposition.builder("attachment")
                .filename(invoice.getName())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(disposition);
        headers.setContentType(invoice.getType());

        return ResponseEntity.ok().headers(headers).body(invoice.getResource());
    }
}
