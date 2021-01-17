package fr.clic1prof.serverapp.file.exceptions;

import fr.clic1prof.serverapp.file.model.DocumentType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class FileStorageNotFoundException extends FileStorageException {

    private DocumentType type;

    public FileStorageNotFoundException(String message, DocumentType type) {
        super(message);
        this.type = type;
    }

    public FileStorageNotFoundException(String message, Throwable cause, DocumentType type) {
        super(message, cause);
        this.type = type;
    }

    public DocumentType getType() {
        return this.type;
    }
}
