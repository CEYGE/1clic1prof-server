package fr.clic1prof.serverapp.file.service;

import fr.clic1prof.serverapp.file.exceptions.FileNotFoundException;
import fr.clic1prof.serverapp.file.exceptions.FileStorageException;
import fr.clic1prof.serverapp.file.model.DocumentType;
import fr.clic1prof.serverapp.file.storage.FileStorageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service("FileStorageServiceImpl")
public class FileStorageServiceImpl implements FileStorageService {

    private final List<FileStorageHandler> handlers;

    @Autowired
    public FileStorageServiceImpl(List<FileStorageHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public String saveResource(MultipartFile file, DocumentType type) {

        Optional<FileStorageHandler> optional = this.findStorageHandler(type);

        if(!optional.isPresent())
            throw new FileStorageException(String.format("No storage found for type %s.", type.name()));

        FileStorageHandler storage = optional.get();

        String id;

        try {
            id = storage.storeFile(file);
        } catch (IOException exception) {
            throw new FileStorageException("An error occurred while trying to store a file.", exception);
        }
        return id;
    }

    @Override
    public void removeResource(String fileId, DocumentType type) {

        Optional<FileStorageHandler> optional = this.findStorageHandler(type);

        if(!optional.isPresent())
            throw new FileStorageException(String.format("No storage found for type %s.", type.name()));

        FileStorageHandler storage = optional.get();

        try {
            storage.deleteFile(fileId);
        } catch (IOException exception) {
            throw new FileStorageException("An error occurred while trying to delete a file.", exception);
        }
    }

    @Override
    public Resource getResource(String fileId, DocumentType type) throws FileNotFoundException {

        Optional<FileStorageHandler> optional = this.findStorageHandler(type);

        if(!optional.isPresent())
            throw new FileStorageException(String.format("No storage found for type %s.", type.name()));

        FileStorageHandler storage = optional.get();

        return storage.getFile(fileId);
    }

    private Optional<FileStorageHandler> findStorageHandler(DocumentType type) {
        return this.handlers.stream()
                .filter(storage -> storage.getStorageTypes().contains(type))
                .findFirst();
    }
}
