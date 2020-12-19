package fr.clic1prof.serverapp.model.file;

public class FileInfo {

    private final String name;
    private final Extension extension;
    private final long size;

    public FileInfo(String name, Extension extension, long size) {
        this.name = name;
        this.extension = extension;
        this.size = size;
    }

    public String getName() {
        return this.name;
    }

    public Extension getExtension() {
        return this.extension;
    }

    public long getSize() {
        return this.size;
    }
}
