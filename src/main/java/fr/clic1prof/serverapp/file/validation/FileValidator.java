package fr.clic1prof.serverapp.file.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

public interface FileValidator {

    boolean validate(MultipartFile file);
}
