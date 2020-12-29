package fr.clic1prof.serverapp.file.util;

import org.springframework.http.MediaType;
import org.springframework.util.InvalidMimeTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;
import java.util.Optional;

public class MediaTypeUtils {

    public static Optional<MediaType> getProvidedMediaType(MultipartFile file) {

        if(file.getContentType() == null) return Optional.empty();

        return getMediaType(file.getContentType());
    }

    public static Optional<MediaType> guessMediaType(MultipartFile file) {

        String contentBasedType;

        try { contentBasedType = URLConnection.guessContentTypeFromStream(file.getInputStream());
        } catch (IOException exception) { return Optional.empty(); }

        return getMediaType(contentBasedType);
    }

    public static Optional<MediaType> getMediaType(String typeAsString) {

        MediaType mediaType = null;

        try { mediaType = MediaType.parseMediaType(typeAsString);
        } catch (InvalidMimeTypeException ignored) {}

        return Optional.ofNullable(mediaType);
    }
}
