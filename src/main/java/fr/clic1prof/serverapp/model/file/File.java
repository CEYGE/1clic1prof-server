package fr.clic1prof.serverapp.model.file;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.clic1prof.serverapp.exceptions.UnsupportedFileExtensionException;
import fr.clic1prof.serverapp.util.FileEncoderDecoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class File {

    @NotNull @NotBlank
    @Size(min = 2, max = 64)
    @JsonProperty("name")
    private String name;

    @NotNull @NotBlank
    private Extension extension;

    @NotNull @NotBlank
    @JsonProperty("content")
    private String content;

    public File(String name, Extension extension) {

        if(!this.isExtensionSupported(extension))
            throw new UnsupportedFileExtensionException(extension);

        this.name = name;
        this.extension = extension;
    }

    @JsonCreator
    public File(String name, Extension extension, String content) {
        this(name, extension);
        this.content = content;
    }

    public abstract List<Extension> getSupportedExtensions();

    public void encode(Path path, FileEncoderDecoder encoder) throws IOException {
        byte[] bytes = Files.readAllBytes(path);
        this.content = encoder.encode(bytes);
    }

    public byte[] decode(FileEncoderDecoder decoder) {
        return decoder.decode(this.content);
    }

    public void write(FileEncoderDecoder decoder, Path directory) throws IOException {

        Path path = this.getPath(directory);
        byte[] bytes = this.decode(decoder);

        Files.write(path, bytes);
    }

    public boolean isExtensionSupported(Extension extension) {
        return this.getSupportedExtensions().stream().anyMatch(ext -> ext.equals(extension));
    }

    public Path getPath(Path directory) {
        return Paths.get(directory.toString(), this.name, ".", this.extension.getName());
    }

    public String getName() {
        return this.name;
    }

    public Extension getExtension() {
        return this.extension;
    }

    public String getContent() {
        return this.content;
    }
}
