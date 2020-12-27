package fr.clic1prof.serverapp.file.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Component
public abstract class AbstractFileStorage implements FileStorage {

    private static final int HEXA_DIGIT_NBR = 2;
    private static final int TREE_LEVEL = 2;

    @Value("${file-storage.root-folder}")
    private String root;

    public abstract List<MediaType> getSupportedTypes();

    @Override
    public String storeFile(MultipartFile file) throws IOException {

        if(!this.isSupported(file)) return null;

        String id = UUID.randomUUID().toString();
        Path path = this.getPath(id);

        // Ensure that two paths cannot have the same UUID.
        // It is improbable but not impossible so it's better to do a check.
        while(Files.exists(path)) {
            id = UUID.randomUUID().toString();
            path = this.getPath(id);
        }

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        return id;
    }

    @Override
    public void deleteFile(String id) throws IOException {
        Path path = this.getPath(id);
        Files.deleteIfExists(path);
    }

    @Override
    public boolean isSupported(MultipartFile file) {

        if(!this.checkProvidedType(file)) return false;

        try { return this.checkContentBasedType(file);
        } catch (IOException e) { e.printStackTrace(); return false; }
    }

    @Override
    public Resource getFile(String id) {

        Path path = this.getPath(id);

        FileSystemResource resource = new FileSystemResource(path);

        return resource.exists() ? resource : null;
    }

    @Override
    public Path getDirectory() {
        return Paths.get(this.root);
    }

    // Get a relative path with the UUID and the parent directory.
    private Path getPath(String id) {

        String path = this.getStringPath(id);
        Path folder = this.getDirectory();

        return Paths.get(folder.toString(), path);
    }

    // Get a relative path only using the UUID.
    private String getStringPath(String id) {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < TREE_LEVEL * HEXA_DIGIT_NBR; i += HEXA_DIGIT_NBR) {

            for(int j = 0; j < HEXA_DIGIT_NBR; j++) sb.append(id.charAt(i + j));

            sb.append("/");
        }
        sb.append(id);

        return sb.toString();
    }

    private boolean checkContentBasedType(MultipartFile file) throws IOException {

        String contentBasedType = URLConnection.guessContentTypeFromStream(file.getInputStream());

        if(contentBasedType == null) return false;

        MediaType type = MediaType.parseMediaType(contentBasedType);

        return this.getSupportedTypes().contains(type);
    }

    private boolean checkProvidedType(MultipartFile file) {

        if(file.getContentType() == null) return false;

        MediaType type = MediaType.parseMediaType(file.getContentType());

        return this.getSupportedTypes().contains(type);
    }
}
