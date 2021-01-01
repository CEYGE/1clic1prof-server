package fr.clic1prof.serverapp.file.service;

import fr.clic1prof.serverapp.file.exceptions.FileNotFoundException;
import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.DocumentType;
import fr.clic1prof.serverapp.file.model.FileStored;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    int addDocument(int ownerId, MultipartFile file, DocumentType type);

    int addDocument(int ownerId, MultipartFile file, DocumentType type, String fileName);

    void removeDocument(int documentId);

    void removeDocument(int ownerId, DocumentType type);

    int updateDocument(int documentId, MultipartFile file, String fileName);

    boolean exists(int documentId);

    boolean exists(int ownerId, DocumentType type);

    Optional<DocumentModel> getDocument(int documentId);

    Optional<DocumentModel> getDocument(int ownerId, DocumentType type);

    List<DocumentModel> getDocuments(int... documentsIds);

    List<DocumentModel> getDocuments(int ownerId, DocumentType type);

    Optional<FileStored> getFileStored(int documentId) throws FileNotFoundException;

    Optional<FileStored> getFileStored(int ownerId, DocumentType type) throws FileNotFoundException;
}
