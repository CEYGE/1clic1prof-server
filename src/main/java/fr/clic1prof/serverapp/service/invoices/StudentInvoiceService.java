package fr.clic1prof.serverapp.service.invoices;

import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.FileStored;

import java.util.List;
import java.util.Optional;

public interface StudentInvoiceService {

    List<DocumentModel> getInvoices(int userId);

    Optional<FileStored> getInvoice(int invoiceId);

    boolean hasInvoice(int userId, int invoiceId);
}
