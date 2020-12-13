package fr.clic1prof.serverapp.model.teacher;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudyLevel {
    @NotNull
    private int level;

    public StudyLevel(int level) {
        this.level = level;
    }

    public int getValue() {
        return this.level;
    }
}
