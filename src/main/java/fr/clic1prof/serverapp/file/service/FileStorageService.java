package fr.clic1prof.serverapp.file.service;

import fr.clic1prof.serverapp.file.model.DocumentType;
import fr.clic1prof.serverapp.file.model.FileStored;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String saveResource(MultipartFile file, DocumentType type);

    void removeResource(String fileId, DocumentType type);

    Resource getResource(String fileId, DocumentType type);
}
