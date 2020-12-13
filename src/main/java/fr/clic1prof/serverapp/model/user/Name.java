package fr.clic1prof.serverapp.model.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Name {

    @NotNull
    @Size(min = 2, max = 64)
    private String name;

    public Name(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }
}
