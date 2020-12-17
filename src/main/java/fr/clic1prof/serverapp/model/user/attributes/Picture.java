package fr.clic1prof.serverapp.model.user.attributes;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Picture {

    @NotNull
    @Size(min = 2, max = 64)
    private String link;

    public Picture(String name) {
        this.link = name;
    }

    public String getValue() {
        return this.link;
    }
}
