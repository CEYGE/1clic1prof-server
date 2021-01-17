package fr.clic1prof.serverapp.file.validation;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("ProfileFileValidator")
public class ProfileFileValidator extends DefaultFileValidator {

    private static final int MAX_SIZE = 2 * 1024 * 1024; // 2 MB.

    @Override
    public boolean validate(MultipartFile file) {

        if(!super.validate(file)) return false;

        return !file.isEmpty() && file.getSize() < MAX_SIZE;
    }
}
