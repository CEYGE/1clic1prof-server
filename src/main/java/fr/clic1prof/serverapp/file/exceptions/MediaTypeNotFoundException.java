package fr.clic1prof.serverapp.file.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class MediaTypeNotFoundException extends RuntimeException {

    public MediaTypeNotFoundException(String message) {
        super(message);
    }

    public MediaTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
