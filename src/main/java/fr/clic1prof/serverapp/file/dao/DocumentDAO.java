package fr.clic1prof.serverapp.file.dao;

import fr.clic1prof.serverapp.file.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("DocumentDAO")
public class DocumentDAO implements IDocumentDAO {

    private JdbcTemplate template;

    @Autowired
    public DocumentDAO(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public boolean addDocument(Document document) {
        return false;
    }

    @Override
    public boolean deleteDocument(int id) {
        return false;
    }

    @Override
    public boolean exists(int id) {
        return false;
    }

    @Override
    public Document getDocument(int id) {
        return null;
    }

    @Override
    public Collection<Document> getDocuments(int... ids) {
        return null;
    }
}
