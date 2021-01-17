package fr.clic1prof.serverapp.file.storage;

import fr.clic1prof.serverapp.file.exceptions.FileNotFoundException;
import fr.clic1prof.serverapp.file.model.DocumentType;
import fr.clic1prof.serverapp.file.validation.FileValidator;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileStorageHandler {

    String storeFile(MultipartFile file) throws IOException;

    void deleteFile(String id) throws IOException;

    boolean isSupported(MultipartFile file);

    Resource getFile(String id) throws FileNotFoundException;

    FileValidator getFileValidator();

    Path getStorageDirectory();

    List<DocumentType> getStorageTypes();
}
