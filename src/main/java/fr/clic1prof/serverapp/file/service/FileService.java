package fr.clic1prof.serverapp.file.service;

import fr.clic1prof.serverapp.file.model.DomainType;
import fr.clic1prof.serverapp.file.exceptions.FileStorageException;
import fr.clic1prof.serverapp.file.model.ResourceFile;
import fr.clic1prof.serverapp.file.dao.storage.FileStorage;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileService implements IFileService {

    @Autowired
    public List<FileStorage> storages;

    @Override
    public void storeResource(UserBase owner, DomainType type, MultipartFile file) throws FileStorageException {

        Optional<FileStorage> optional = this.findByDomain(type);

        if(!optional.isPresent())
            throw new FileStorageException("Storage not found.");

        FileStorage storage = optional.get();

        if(!storage.isSupported(file))
            throw new FileStorageException("File not supported.");

        // UUID uuid = storage.store(file);
    }

    @Override
    public void deleteResource(UserBase owner, DomainType type, int id) throws FileStorageException {

        Optional<FileStorage> optional = this.findByDomain(type);

        if(!optional.isPresent())
            throw new FileStorageException("Storage not found.");

        FileStorage storage = optional.get();
    }

    @Override
    public ResourceFile getResource(DomainType type, int id) throws FileStorageException {

        Optional<FileStorage> optional = this.findByDomain(type);

        if(!optional.isPresent())
            throw new FileStorageException("Storage not found.");

        return null;
    }

    @Override
    public Collection<ResourceFile> getResources(DomainType type, int... id) throws FileStorageException {

        Optional<FileStorage> optional = this.findByDomain(type);

        if(!optional.isPresent())
            throw new FileStorageException("Storage not found.");

        return null;
    }

    private Optional<FileStorage> findByDomain(DomainType type) {
        return this.storages.stream()
                .filter(storage -> storage.getDomainType().equals(type))
                .findFirst();
    }
}
