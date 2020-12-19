package fr.clic1prof.serverapp.model.file;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public class Picture extends File {

    @JsonCreator
    public Picture(String name, Extension extension) {
        super(name, extension);
    }

    @Override
    public List<Extension> getSupportedExtensions() {
        return Arrays.asList(Extension.PNG, Extension.JPG);
    }
}
