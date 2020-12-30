package fr.clic1prof.serverapp.file.validation;

import org.springframework.web.multipart.MultipartFile;

public class DefaultFileValidator implements FileValidator {

    @Override
    public boolean validate(MultipartFile file) {

        if(file.getContentType() == null) return false;

        if(file.getOriginalFilename() == null) return false;

        return file.getSize() != 0;
    }
}
