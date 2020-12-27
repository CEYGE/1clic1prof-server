package fr.clic1prof.serverapp.file.service;

import fr.clic1prof.serverapp.file.dao.IDocumentDAO;
import fr.clic1prof.serverapp.file.exceptions.FileStorageException;
import fr.clic1prof.serverapp.file.model.DomainType;
import fr.clic1prof.serverapp.file.storage.FileStorage;
import fr.clic1prof.serverapp.file.model.Document;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("FileService")
public class FileService implements IFileService {

    private IDocumentDAO dao;
    private List<FileStorage> storages;

    @Autowired
    public FileService(@Qualifier("DocumentDAO") IDocumentDAO dao, List<FileStorage> storages) {
        this.dao = dao;
        this.storages = storages;
    }

    @Override
    public boolean storeResource(UserBase owner, DomainType type, MultipartFile file) {

        FileStorage storage = this.getFileStorage(type);

        if(!storage.isSupported(file))
            throw new FileStorageException("File not supported.");

        String id;

        try { id = storage.storeFile(file);
        } catch (IOException e) { return false; }

        Document document = new Document.Builder(file.getOriginalFilename(), id, owner.getId()).build();

        return this.dao.addDocument(document);
    }

    @Override
    public boolean deleteResource(UserBase owner, DomainType type, int id) {

        Document document = this.dao.getDocument(id);

        if(document == null) return false;

        FileStorage storage = this.getFileStorage(type);

        boolean deleted = false;

        try {
            storage.deleteFile(document.getPath());
            deleted = this.dao.deleteDocument(id);
        } catch (IOException e) { e.printStackTrace(); }

        return deleted;
    }

    @Override
    public Resource getResource(DomainType type, int id) {

        Document document = this.getDocument(id);
        FileStorage storage = this.getFileStorage(type);

        return storage.getFile(document.getPath());
    }

    @Override
    public Document getDocument(int id) {
        return this.dao.getDocument(id);
    }

    @Override
    public Collection<Document> getResources(int... ids) {
        return this.dao.getDocuments(ids);
    }

    private Optional<FileStorage> findByDomain(DomainType type) {
        return this.storages.stream()
                .filter(storage -> storage.getDomainType().equals(type))
                .findFirst();
    }

    private FileStorage getFileStorage(DomainType type) {

        Optional<FileStorage> optional = this.findByDomain(type);

        if(!optional.isPresent())
            throw new FileStorageException("Storage not found.");

        return optional.get();
    }
}
