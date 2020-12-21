package fr.clic1prof.serverapp.file.dao;

import fr.clic1prof.serverapp.file.model.Document;

import java.util.Collection;

public interface IDocumentDAO {

    boolean addDocument(Document document);

    boolean deleteDocument(int id);

    boolean exists(int id);

    Document getDocument(int id);

    Collection<Document> getDocuments(int... ids);
}
