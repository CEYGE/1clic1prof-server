package fr.clic1prof.serverapp.file.service;

import fr.clic1prof.serverapp.exceptions.DocumentNotFoundException;
import fr.clic1prof.serverapp.file.dao.DocumentDAO;
import fr.clic1prof.serverapp.file.exceptions.FileNotFoundException;
import fr.clic1prof.serverapp.file.exceptions.MediaTypeNotFoundException;
import fr.clic1prof.serverapp.file.model.Document;
import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.DocumentType;
import fr.clic1prof.serverapp.file.model.FileStored;
import fr.clic1prof.serverapp.file.util.MediaTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service("DocumentServiceImpl")
public class DocumentServiceImpl implements DocumentService {

    private final DocumentDAO documentDAO;
    private final FileStorageService storageService;

    @Autowired
    public DocumentServiceImpl(@Qualifier("DocumentDAOImpl") DocumentDAO documentDAO,
                               @Qualifier("FileStorageServiceImpl") FileStorageService storageService) {
        this.documentDAO = documentDAO;
        this.storageService = storageService;
    }

    @Override
    public boolean addDocument(int ownerId, MultipartFile file, DocumentType type) {

        String fileId = this.storageService.saveResource(file, type);

        Optional<MediaType> optional = MediaTypeUtils.guessMediaType(file);

        if(!optional.isPresent())
            throw new MediaTypeNotFoundException("No valid MediaType found.");

        DocumentModel document = new Document.Builder()
                .withOwnerId(ownerId)
                .withName(file.getOriginalFilename())
                .withFileId(fileId)
                .withSize(file.getSize())
                .withExtension(file.getContentType())
                .withType(type)
                .build();

        return this.documentDAO.addDocument(document);
    }

    @Override
    public boolean removeDocument(int documentId) {

        if(!this.exists(documentId))
            throw new DocumentNotFoundException("No document found.");

        DocumentModel document = this.documentDAO.getDocument(documentId);

        this.storageService.removeResource(document.getFileId(), document.getType());

        return true;
    }

    @Override
    public boolean removeDocument(int ownerId, DocumentType type) {

        if(!this.exists(ownerId, type))
            throw new DocumentNotFoundException("No document found.");

        DocumentModel document = this.documentDAO.getDocument(ownerId, type);

        this.storageService.removeResource(document.getFileId(), document.getType());

        return true;
    }

    @Override
    public boolean exists(int documentId) {
        return this.documentDAO.exists(documentId);
    }

    @Override
    public boolean exists(int ownerId, DocumentType type) {
        return this.documentDAO.exists(ownerId, type);
    }

    @Override
    public Optional<DocumentModel> getDocument(int documentId) {

        if(!this.exists(documentId)) return Optional.empty();

        DocumentModel document = this.documentDAO.getDocument(documentId);

        return Optional.of(document);
    }

    @Override
    public Optional<DocumentModel> getDocument(int ownerId, DocumentType type) {

        if(!this.exists(ownerId, type)) return Optional.empty();

        DocumentModel document = this.documentDAO.getDocument(ownerId, type);

        return Optional.of(document);
    }

    @Override
    public List<DocumentModel> getDocuments(int... documentsIds) {
        return this.documentDAO.getDocuments(documentsIds);
    }

    @Override
    public List<DocumentModel> getDocuments(int ownerId, DocumentType type) {
        return this.documentDAO.getDocuments(ownerId, type);
    }

    @Override
    public Optional<FileStored> getFileStored(int documentId) throws FileNotFoundException {

        Optional<DocumentModel> optional = this.getDocument(documentId);

        if(!optional.isPresent()) return Optional.empty();

        DocumentModel document = optional.get();

        Resource resource = this.storageService.getResource(document.getFileId(), document.getType());
        FileStored file = this.getFileStored(document, resource);

        return Optional.of(file);
    }

    @Override
    public Optional<FileStored> getFileStored(int ownerId, DocumentType type) throws FileNotFoundException {

        Optional<DocumentModel> optional = this.getDocument(ownerId, type);

        if(!optional.isPresent()) return Optional.empty();

        DocumentModel document = optional.get();

        Resource resource = this.storageService.getResource(document.getFileId(), document.getType());
        FileStored file = this.getFileStored(document, resource);

        return Optional.of(file);
    }

    private FileStored getFileStored(DocumentModel document, Resource resource) {

        Optional<MediaType> optional = MediaTypeUtils.getMediaType(document.getMediaType());

        if(!optional.isPresent())
            throw new MediaTypeNotFoundException("Invalid MediaType.");

        return new FileStored(resource, document.getName(), optional.get(), document.getSize());
    }
}
