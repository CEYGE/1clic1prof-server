package fr.clic1prof.serverapp.model.student;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SchoolLevel {

    @NotNull
    @Size(min = 2, max = 64)
    private String level;

    public SchoolLevel(String name) {
        this.level = name;
    }

    public String getValue() {
        return this.level;
    }
}
