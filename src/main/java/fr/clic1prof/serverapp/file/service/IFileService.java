package fr.clic1prof.serverapp.file.service;

import fr.clic1prof.serverapp.file.model.DomainType;
import fr.clic1prof.serverapp.file.exceptions.FileStorageException;
import fr.clic1prof.serverapp.file.model.ResourceFile;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface IFileService {

    void storeResource(UserBase owner, DomainType type, MultipartFile file) throws FileStorageException;

    void deleteResource(UserBase owner, DomainType type, int id) throws FileStorageException;

    ResourceFile getResource(DomainType type, int id) throws FileStorageException;

    Collection<ResourceFile> getResources(DomainType type, int... id) throws FileStorageException;
}
