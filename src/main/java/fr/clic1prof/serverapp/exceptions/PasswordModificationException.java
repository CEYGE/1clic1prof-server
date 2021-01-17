package fr.clic1prof.serverapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordModificationException extends RuntimeException {

    public PasswordModificationException(String message) {
        super(message);
    }

    public PasswordModificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
