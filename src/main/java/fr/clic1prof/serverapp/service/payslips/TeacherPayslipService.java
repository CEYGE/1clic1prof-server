package fr.clic1prof.serverapp.service.payslips;

import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.FileStored;

import java.util.List;
import java.util.Optional;

public interface TeacherPayslipService {

    List<DocumentModel> getPayslips(int userId);

    Optional<FileStored> getPayslip(int payslipId);

    boolean hasPayslip(int userId, int payslipId);
}
