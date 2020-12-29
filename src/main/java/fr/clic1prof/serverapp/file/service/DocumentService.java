package fr.clic1prof.serverapp.file.service;

import fr.clic1prof.serverapp.file.exceptions.FileNotFoundException;
import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.DocumentType;
import fr.clic1prof.serverapp.file.model.FileStored;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    boolean addDocument(int ownerId, MultipartFile file, DocumentType type);

    boolean removeDocument(int documentId);

    boolean removeDocument(int ownerId, DocumentType type);

    boolean exists(int documentId);

    boolean exists(int ownerId, DocumentType type);

    DocumentModel getDocument(int documentId);

    DocumentModel getDocument(int ownerId, DocumentType type);

    List<DocumentModel> getDocuments(int... documentsIds);

    List<DocumentModel> getDocuments(int ownerId, DocumentType type);

    FileStored getFileStored(int id) throws FileNotFoundException;

    FileStored getFileStored(int ownerId, DocumentType type) throws FileNotFoundException;
}
