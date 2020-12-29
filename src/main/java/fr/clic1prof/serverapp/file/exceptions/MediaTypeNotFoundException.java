package fr.clic1prof.serverapp.file.exceptions;

public class MediaTypeNotFoundException extends RuntimeException {

    public MediaTypeNotFoundException(String message) {
        super(message);
    }

    public MediaTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
