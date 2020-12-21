package fr.clic1prof.serverapp.file.dao.storage;

import fr.clic1prof.serverapp.file.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public abstract class AbstractFileStorage implements FileStorage {

    private static final int HEXA_DIGIT_NBR = 2;
    private static final int TREE_LEVEL = 2;

    @Value("${file-storage.root-folder}")
    private String root;

    @Override
    public UUID store(MultipartFile file) throws IOException {

        UUID uuid = UUID.randomUUID();
        Path path = this.getPath(uuid);

        // Ensure that two paths cannot have the same UUID.
        // It is improbable but not impossible so it's better to do a check.
        while(Files.exists(path)) {
            uuid = UUID.randomUUID();
            path = this.getPath(uuid);
        }

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        return uuid;
    }

    @Override
    public void delete(UUID uuid) throws IOException {
        Path path = this.getPath(uuid);
        Files.deleteIfExists(path);
    }

    @Override
    public Resource get(UUID uuid) {

        Path path = this.getPath(uuid);

        FileSystemResource resource = new FileSystemResource(path);

        return resource.exists() ? resource : null;
    }

    @Override
    public Path getDirectory() {
        return Paths.get(this.root);
    }

    // Get a relative path with the UUID and the parent directory.
    private Path getPath(UUID uuid) {

        String path = this.getStringPath(uuid);
        Path folder = this.getDirectory();

        return Paths.get(folder.toString(), path);
    }

    // Get a relative path only using the UUID.
    private String getStringPath(UUID uuid) {

        String uuidToString = uuid.toString();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < TREE_LEVEL * HEXA_DIGIT_NBR; i += HEXA_DIGIT_NBR) {

            for(int j = 0; j < HEXA_DIGIT_NBR; j++) sb.append(uuidToString.charAt(i + j));

            sb.append("/");
        }
        sb.append(uuidToString);

        return sb.toString();
    }
}
