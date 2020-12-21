package fr.clic1prof.serverapp.file.service;

import fr.clic1prof.serverapp.file.model.Document;
import fr.clic1prof.serverapp.file.model.DomainType;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface IFileService {

    boolean storeResource(UserBase owner, DomainType type, MultipartFile file);

    boolean deleteResource(UserBase owner, DomainType type, int id);

    Resource getResource(DomainType type, int id);

    Document getDocument(int id);

    Collection<Document> getResources(int... ids);
}
