package fr.clic1prof.serverapp.file.dao;

import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.DocumentType;

import java.util.List;

public interface DocumentDAO {

    boolean addDocument(DocumentModel model);

    boolean removeDocument(int documentId);

    boolean removeDocument(int ownerId, DocumentType type);

    boolean exists(int documentId);

    boolean exists(int ownerId, DocumentType type);

    DocumentModel getDocument(int documentId);

    DocumentModel getDocument(int ownerId, DocumentType type);

    List<DocumentModel> getDocuments(int... documentsIds);

    List<DocumentModel> getDocuments(int ownerId, DocumentType type);
}
