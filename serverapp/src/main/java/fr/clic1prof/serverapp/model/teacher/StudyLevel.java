package fr.clic1prof.serverapp.model.teacher;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudyLevel {

    @NotNull
    @Size(min = 2, max = 64)
    private String level;

    public StudyLevel(String name) {
        this.level = name;
    }

    public String getValue() {
        return this.level;
    }
}
