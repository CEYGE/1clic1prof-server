package fr.clic1prof.serverapp.file.storage;

import fr.clic1prof.serverapp.file.model.DomainType;
import fr.clic1prof.serverapp.file.validation.FileValidator;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public interface FileStorage {

    String storeFile(MultipartFile file) throws IOException;

    void deleteFile(String id) throws IOException;

    boolean isSupported(MultipartFile file);

    Resource getFile(String id);

    Path getDirectory();

    DomainType getDomainType();

    FileValidator getFileValidator();
}
