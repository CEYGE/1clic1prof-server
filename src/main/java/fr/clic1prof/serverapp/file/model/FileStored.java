package fr.clic1prof.serverapp.file.model;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

public class FileStored {

    private Resource resource;
    private String name;
    private MediaType type;
    private long size;

    public FileStored(Resource resource, String name, MediaType type, long size) {
        this.resource = resource;
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public Resource getResource() {
        return this.resource;
    }

    public String getName() {
        return this.name;
    }

    public MediaType getType() {
        return this.type;
    }

    public long getSize() {
        return this.size;
    }
}
