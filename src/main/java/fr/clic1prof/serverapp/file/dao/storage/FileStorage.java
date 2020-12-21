package fr.clic1prof.serverapp.file.dao.storage;

import fr.clic1prof.serverapp.file.model.DomainType;
import fr.clic1prof.serverapp.file.exceptions.FileStorageException;
import fr.clic1prof.serverapp.file.validation.FileValidator;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

public interface FileStorage {

    UUID store(MultipartFile file) throws IOException;

    void delete(UUID uuid) throws IOException;

    Resource get(UUID uuid);

    Path getDirectory();

    FileValidator getFileValidator();

    DomainType getDomainType();

    boolean isSupported(MultipartFile file);
}
