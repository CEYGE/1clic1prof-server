package fr.clic1prof.serverapp.exceptions;

import fr.clic1prof.serverapp.model.file.Extension;

public class UnsupportedFileExtensionException extends RuntimeException {

    public UnsupportedFileExtensionException(Extension extension) {
        super(extension.name() + " not supported.");
    }
}
