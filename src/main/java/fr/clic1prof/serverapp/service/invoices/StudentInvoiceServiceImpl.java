package fr.clic1prof.serverapp.service.invoices;

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

@Service("StudentInvoiceServiceImpl")
public class StudentInvoiceServiceImpl implements StudentInvoiceService {

    private DocumentService documentService;

    @Autowired
    public StudentInvoiceServiceImpl(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public List<DocumentModel> getInvoices(int userId) {

        List<DocumentModel> invoices = this.documentService.getDocuments(userId, DocumentType.INVOICE);

        // Get all the invoices which are less 1 year old.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);

        return invoices.stream()
                .filter(payslip -> payslip.getCreationDate().after(Date.from(calendar.toInstant())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FileStored> getInvoice(int invoiceId) {

        try { return this.documentService.getFileStored(invoiceId);
        } catch (FileNotFoundException ignored) { return Optional.empty(); }
    }

    @Override
    public boolean hasInvoice(int userId, int invoiceId) {

        Optional<DocumentModel> optional = this.documentService.getDocument(invoiceId);

        return optional.filter(document -> document.getOwnerId() == userId && document.getType() == DocumentType.INVOICE).isPresent();

    }
}
