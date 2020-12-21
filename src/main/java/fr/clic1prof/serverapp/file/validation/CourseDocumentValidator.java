package fr.clic1prof.serverapp.file.validation;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("CourseDocumentValidator")
public class CourseDocumentValidator implements FileValidator {

    private static final int MAX_SIZE = 10 * 1024 * 1024; // 10 MB.

    @Override
    public boolean validate(MultipartFile file) {
        return !file.isEmpty() && file.getSize() < MAX_SIZE;
    }
}
