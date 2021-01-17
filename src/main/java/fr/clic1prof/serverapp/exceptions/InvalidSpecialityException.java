package fr.clic1prof.serverapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSpecialityException extends RuntimeException {

    private int specialityId;

    public InvalidSpecialityException(String message, int specialityId) {
        super(message);
        this.specialityId = specialityId;
    }

    public InvalidSpecialityException(String message, Throwable cause, int specialityId) {
        super(message, cause);
        this.specialityId = specialityId;
    }

    public int getSpecialityId() {
        return this.specialityId;
    }
}
