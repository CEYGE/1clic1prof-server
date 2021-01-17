package fr.clic1prof.serverapp.api.invoices;

import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface StudentInvoiceController {

    @GetMapping("/student/invoices")
    ResponseEntity<List<DocumentModel>> getInvoices(UserBase user);

    @GetMapping("/student/invoice/{id}")
    ResponseEntity<Resource> getInvoice(UserBase user, @PathVariable("id") int invoiceId);
}
