package fr.clic1prof.serverapp.file.exceptions;

import fr.clic1prof.serverapp.file.model.DocumentType;

public class FileStorageNotFoundException extends FileStorageException {

    private DocumentType type;

    public FileStorageNotFoundException(String message, DocumentType type) {
        super(message);
    }

    public FileStorageNotFoundException(String message, Throwable cause, DocumentType type) {
        super(message, cause);
    }

    public DocumentType getType() {
        return this.type;
    }
}
