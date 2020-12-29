package fr.clic1prof.serverapp.exceptions.handlers;

import fr.clic1prof.serverapp.file.exceptions.FileNotFoundException;
import fr.clic1prof.serverapp.file.exceptions.FileStorageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FileExceptionHandler {

    @ExceptionHandler(value = FileStorageException.class)
    public ResponseEntity<String> handleFileStorageException() {
        return ResponseEntity.unprocessableEntity().body("An error occurred in file storage.");
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    public ResponseEntity<String> handleFileNotFoundException() {
        return ResponseEntity.unprocessableEntity().body("File not found.");
    }
}
