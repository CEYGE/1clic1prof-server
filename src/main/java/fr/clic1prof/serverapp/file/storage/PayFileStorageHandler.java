package fr.clic1prof.serverapp.file.storage;

import fr.clic1prof.serverapp.file.model.DocumentType;
import fr.clic1prof.serverapp.file.validation.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component("PayFileStorageHandler")
public class PayFileStorageHandler extends AbstractFileStorageHandler {

    private static final List<MediaType> SUPPORTED_TYPES = Collections.singletonList(MediaType.APPLICATION_PDF);

    @Value("${file-storage.pay-file-folder}")
    private String folder;

    private final FileValidator validator;

    @Autowired
    public PayFileStorageHandler(@Qualifier("PayFileValidator") FileValidator validator) {
        this.validator = validator;
    }

    @Override
    public Path getStorageDirectory() {
        return Paths.get(super.getStorageDirectory() + "/" + this.folder);
    }

    @Override
    public List<MediaType> getAuthorizedMediaTypes() {
        return SUPPORTED_TYPES;
    }

    @Override
    public FileValidator getFileValidator() {
        return this.validator;
    }

    @Override
    public List<DocumentType> getStorageTypes() {
        return Arrays.asList(DocumentType.INVOICE, DocumentType.PAYSLIP);
    }
}
