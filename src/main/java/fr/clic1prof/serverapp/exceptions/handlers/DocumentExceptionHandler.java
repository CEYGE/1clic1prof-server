package fr.clic1prof.serverapp.exceptions.handlers;

import fr.clic1prof.serverapp.exceptions.DocumentNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DocumentExceptionHandler {

    @ExceptionHandler(value = DocumentNotFoundException.class)
    public ResponseEntity<String> handleFileStorageException() {
        return ResponseEntity.unprocessableEntity().body("No document found.");
    }
}
