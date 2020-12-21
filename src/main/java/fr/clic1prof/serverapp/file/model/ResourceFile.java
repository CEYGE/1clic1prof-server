package fr.clic1prof.serverapp.file.model;

import org.springframework.core.io.Resource;

public class ResourceFile {

    private String name;
    private Resource resource;

    public ResourceFile(String name, Resource resource) {
        this.name = name;
        this.resource = resource;
    }

    public String getName() {
        return this.name;
    }

    public Resource getResource() {
        return this.resource;
    }
}
