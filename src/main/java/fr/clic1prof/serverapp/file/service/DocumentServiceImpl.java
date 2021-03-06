package fr.clic1prof.serverapp.file.service;

import fr.clic1prof.serverapp.file.dao.DocumentDAO;
import fr.clic1prof.serverapp.file.exceptions.DocumentNotFoundException;
import fr.clic1prof.serverapp.file.exceptions.FileNotFoundException;
import fr.clic1prof.serverapp.file.exceptions.FileStorageException;
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
    public int addDocument(int ownerId, MultipartFile file, DocumentType type) {
        return this.addDocument(ownerId, file, type, file.getOriginalFilename());
    }

    @Override
    public int addDocument(int ownerId, MultipartFile file, DocumentType type, String fileName) {

        // Implicit checks here.
        this.checkFileName(fileName);
        MediaType mediaType = this.getMediaType(file);

        if(!this.storageService.isStorable(file, type))
            throw new FileStorageException("Document not storable.");

        // Saving resource after guessing MediaType. Indeed, if an exception
        // is thrown, the file will not be saved and the document will not be
        // added to the database.
        String fileId = this.storageService.saveResource(file, type);

        // Building a DocumentModel instance.
        DocumentModel document = new Document.Builder()
                .withOwnerId(ownerId)
                .withName(fileName)
                .withFileId(fileId)
                .withSize(file.getSize())
                .withExtension(MediaTypeUtils.getMediaTypeAsString(mediaType))
                .withType(type)
                .build();

        return this.documentDAO.addDocument(document);
    }

    @Override
    public void removeDocument(int documentId) {

        if(!this.exists(documentId)) return;

        DocumentModel document = this.documentDAO.getDocument(documentId);

        this.documentDAO.removeDocument(documentId);
        this.storageService.removeResource(document.getFileId(), document.getType());
    }

    @Override
    public void removeDocument(int ownerId, DocumentType type) {

        if(!this.exists(ownerId, type)) return;

        DocumentModel document = this.documentDAO.getDocument(ownerId, type);

        this.documentDAO.removeDocument(ownerId, type);
        this.storageService.removeResource(document.getFileId(), document.getType());
    }

    @Override
    public int updateDocument(int documentId, MultipartFile file, String fileName) {

        Optional<DocumentModel> optional = this.getDocument(documentId);

        if(!optional.isPresent())
            throw new DocumentNotFoundException("No existing document found. Please, use add method.");

        DocumentModel oldDocument = optional.get();

        // Checks should be performed here.
        this.checkFileName(fileName);

        // Implicit checks here.
        MediaType type = this.getMediaType(file);
        String fileId = this.storageService.saveResource(file, oldDocument.getType());

        if(!this.storageService.isStorable(file, oldDocument.getType()))
            throw new FileStorageException("Document not storable.");

        // Removing existing document. Operation can be performed first because
        // the old document has been retrieved into a variable.
        this.removeDocument(documentId);

        // Building a DocumentModel instance.
        DocumentModel document = new Document.Builder()
                .withOwnerId(oldDocument.getOwnerId())
                .withName(fileName)
                .withFileId(fileId)
                .withSize(file.getSize())
                .withExtension(MediaTypeUtils.getMediaTypeAsString(type))
                .withType(oldDocument.getType())
                .build();

        return this.documentDAO.addDocument(document);
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

    private void checkFileName(String fileName) {

        if(fileName == null || fileName.isEmpty())
            throw new IllegalArgumentException("File cannot be null or blank.");
    }

    private MediaType getMediaType(MultipartFile file) {

        Optional<MediaType> optional = MediaTypeUtils.guessMediaType(file);

        if(!optional.isPresent())
            throw new MediaTypeNotFoundException("No valid MediaType found.");

        return optional.get();
    }
}
