package fr.clic1prof.serverapp.file.dao;

import fr.clic1prof.serverapp.file.model.Document;
import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("DocumentDAOImpl")
public class DocumentDAOImpl implements DocumentDAO {

    private final JdbcTemplate template;

    @Autowired
    public DocumentDAOImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public int addDocument(DocumentModel document) {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.template);
        insert.withTableName("document").usingGeneratedKeyColumns("doc_id", "doc_modification_date", "doc_creation_date");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("doc_owner_id", document.getOwnerId());
        parameters.put("doc_file_id", document.getFileId());
        parameters.put("doc_name", document.getName());
        parameters.put("doc_media_type", document.getMediaType());
        parameters.put("doc_size", document.getSize());
        parameters.put("doc_type", document.getType().name());

        return insert.executeAndReturnKey(parameters).intValue();
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
            String docType = result.getString("doc_type");

            Date creationDate = result.getDate("doc_creation_date");
            Date modificationDate = result.getDate("doc_modification_date");

            DocumentType type = DocumentType.getTypeByName(docType).orElse(DocumentType.UNKNOWN);

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
