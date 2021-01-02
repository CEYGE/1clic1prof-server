package fr.clic1prof.serverapp.api.payslips;

import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TeacherPayslipController {

    @GetMapping("/teacher/payslips")
    ResponseEntity<List<DocumentModel>> getPayslips(UserBase user);

    @GetMapping("/teacher/payslip/{id}")
    ResponseEntity<Resource> getPayslip(UserBase user, @PathVariable("id") int payslipId);
}
