package fr.clic1prof.serverapp.api.payslips;

import fr.clic1prof.serverapp.file.exceptions.DocumentNotFoundException;
import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.FileStored;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.UserRole;
import fr.clic1prof.serverapp.service.payslips.TeacherPayslipService;
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
@Secured(UserRole.Names.TEACHER)
public class TeacherPayslipControllerImpl implements TeacherPayslipController {

    private final TeacherPayslipService service;

    @Autowired
    public TeacherPayslipControllerImpl(TeacherPayslipService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<DocumentModel>> getPayslips(UserBase user) {

        List<DocumentModel> payslips = this.service.getPayslips(user.getId());

        return ResponseEntity.ok(payslips);
    }

    @Override
    public ResponseEntity<Resource> getPayslip(UserBase user, int payslipId) {

        if(!this.service.hasPayslip(user.getId(), payslipId))
            throw new DocumentNotFoundException(String.format("No payslip with id %d found.", payslipId));

        Optional<FileStored> optional = this.service.getPayslip(payslipId);

        if(!optional.isPresent())
            throw new DocumentNotFoundException(String.format("No payslip with id %d found.", payslipId));

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
