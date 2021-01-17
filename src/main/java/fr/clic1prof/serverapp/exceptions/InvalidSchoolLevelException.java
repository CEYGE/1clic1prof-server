package fr.clic1prof.serverapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSchoolLevelException extends RuntimeException {

    private int schoolLevelId;

    public InvalidSchoolLevelException(String message, int schoolLevelId) {
        super(message);
        this.schoolLevelId = schoolLevelId;
    }

    public InvalidSchoolLevelException(String message, Throwable cause, int schoolLevelId) {
        super(message, cause);
        this.schoolLevelId = schoolLevelId;
    }

    public int getSchoolLevelId() {
        return this.schoolLevelId;
    }
}
