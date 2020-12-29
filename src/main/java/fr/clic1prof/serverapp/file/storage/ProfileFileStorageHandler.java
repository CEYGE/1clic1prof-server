package fr.clic1prof.serverapp.file.storage;

import fr.clic1prof.serverapp.file.model.DomainType;
import fr.clic1prof.serverapp.file.validation.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component("ProfileFileStorage")
public class ProfileFileStorage extends AbstractFileStorage {

    private static final List<MediaType> SUPPORTED_TYPES = Arrays.asList(MediaType.IMAGE_PNG, MediaType.IMAGE_JPEG);

    @Value("${file-storage.profile-picture-folder}")
    private String folder;

    private FileValidator validator;

    @Autowired
    public ProfileFileStorage(@Qualifier("ProfileFileValidator") FileValidator validator) {
        this.validator = validator;
    }

    @Override
    public Path getDirectory() {
        return Paths.get(super.getDirectory() + "/" + this.folder);
    }

    @Override
    public FileValidator getFileValidator() {
        return this.validator;
    }

    @Override
    public DomainType getDomainType() {
        return DomainType.PROFILE;
    }

    @Override
    public List<MediaType> getSupportedTypes() {
        return SUPPORTED_TYPES;
    }
}
