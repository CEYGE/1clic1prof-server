package fr.clic1prof.serverapp.model.file;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public class Document extends File {

    @JsonCreator
    public Document(String name, Extension extension) {
        super(name, extension);
    }

    @Override
    public List<Extension> getSupportedExtensions() {
        return Arrays.asList(Extension.values());
    }
}
