package fr.clic1prof.serverapp.file.dao;

import fr.clic1prof.serverapp.file.model.Document;
import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("DocumentDAOImpl")
public class DocumentDAOImpl implements DocumentDAO {

    private final JdbcTemplate template;

    @Autowired
    public DocumentDAOImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public boolean addDocument(DocumentModel document) {

        String query = "INSERT INTO document (" +
                "doc_owner_id, doc_file_id, doc_name, doc_media_type, doc_size, doc_type" +
                ") VALUES (?, ?, ?, ?, ?, ?);";

        int rows = this.template.update(query,
                document.getOwnerId(),
                document.getFileId(),
                document.getName(),
                document.getMediaType(),
                document.getSize(),
                document.getType());

        return rows > 0;
    }

    @Override
    public boolean removeDocument(int documentId) {

        String query = "DELETE FROM document WHERE doc_id = ?;";

        return this.template.update(query, documentId) > 0;
    }

    @Override
    public boolean removeDocument(int ownerId, DocumentType type) {

        String query = "DELETE FROM document WHERE doc_owner_id = ? AND doc_type = ?;";

        return this.template.update(query, ownerId, type.name()) > 0;
    }

    @Override
    public boolean exists(int documentId) {

        String query = "SELECT COUNT(1) FROM document WHERE doc_id = ?;";

        Integer value = this.template.queryForObject(query, Integer.class, documentId);

        return value != null && value == 1;
    }

    @Override
    public boolean exists(int ownerId, DocumentType type) {

        String query = "SELECT COUNT(1) FROM document WHERE doc_owner_id = ? AND doc_type = ?;";

        Integer value = this.template.queryForObject(query, Integer.class, ownerId, type.name());

        return value != null && value == 1;
    }

    @Override
    public DocumentModel getDocument(int documentId) {

        String query = "SELECT * FROM document WHERE doc_id = ?";

        return this.template.queryForObject(query, this.getDocumentMapper(), documentId);
    }

    @Override
    public DocumentModel getDocument(int ownerId, DocumentType type) {

        String query = "SELECT * FROM document WHERE doc_owner_id = ? AND doc_type = ?;";

        return this.template.queryForObject(query, this.getDocumentMapper(), ownerId, type.name());
    }

    // TODO
    @Override
    public List<DocumentModel> getDocuments(int... documentsIds) {
        return null;
    }

    // TODO
    @Override
    public List<DocumentModel> getDocuments(int ownerId, DocumentType type) {
        return null;
    }

    private RowMapper<DocumentModel> getDocumentMapper() {
        return (result, i) -> {

            int id = result.getInt("doc_id");
            int ownerId = result.getInt("doc_owner_id");
            long size = result.getLong("doc_size");

            String fileId = result.getString("doc_file_id");
            String name = result.getString("doc_name");
            String mediaType = result.getString("doc_media_type");

            Date creationDate = result.getDate("doc_creation_date");
            Date modificationDate = result.getDate("doc_modification_date");

            DocumentType type = DocumentType.getTypeByName(name).orElse(DocumentType.UNKNOWN);

            return new Document.Builder()
                    .withId(id)
                    .withOwnerId(ownerId)
                    .withSize(size)
                    .withFileId(fileId)
                    .withName(name)
                    .withExtension(mediaType)
                    .withCreationDate(creationDate)
                    .withModificationDate(modificationDate)
                    .withType(type)
                    .build();
        };
    }
}
