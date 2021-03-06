package fr.clic1prof.serverapp.service.payslips;

import fr.clic1prof.serverapp.file.exceptions.FileNotFoundException;
import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.DocumentType;
import fr.clic1prof.serverapp.file.model.FileStored;
import fr.clic1prof.serverapp.file.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("TeacherPayslipServiceImpl")
public class TeacherPayslipServiceImpl implements TeacherPayslipService {

    private final DocumentService documentService;

    @Autowired
    public TeacherPayslipServiceImpl(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public List<DocumentModel> getPayslips(int userId) {

        List<DocumentModel> payslips = this.documentService.getDocuments(userId, DocumentType.PAYSLIP);

        // Get all the payslips which are less 1 year old.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);

        return payslips.stream()
                .filter(payslip -> payslip.getCreationDate().after(Date.from(calendar.toInstant())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FileStored> getPayslip(int payslipId) {

        try { return this.documentService.getFileStored(payslipId);
        } catch (FileNotFoundException ignored) { return Optional.empty(); }
    }

    @Override
    public boolean hasPayslip(int userId, int payslipId) {

        Optional<DocumentModel> optional = this.documentService.getDocument(payslipId);

        return optional.filter(document -> document.getOwnerId() == userId && document.getType() == DocumentType.PAYSLIP).isPresent();
    }
}
