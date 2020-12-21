package fr.clic1prof.serverapp.file.storage;

import fr.clic1prof.serverapp.file.model.DomainType;
import fr.clic1prof.serverapp.file.validation.FileValidator;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface FileStorage {

    String store(MultipartFile file) throws IOException;

    void delete(String id) throws IOException;

    Resource get(String id);

    Path getDirectory();

    FileValidator getFileValidator();

    DomainType getDomainType();

    boolean isSupported(MultipartFile file);
}
