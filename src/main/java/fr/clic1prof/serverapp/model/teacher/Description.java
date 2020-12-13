package fr.clic1prof.serverapp.model.teacher;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Description {

    @NotNull
    @Size(min = 2, max = 64)
    private String description;

    public Description(String name) {
        this.description = name;
    }

    public String getValue() {
        return this.description;
    }
}
