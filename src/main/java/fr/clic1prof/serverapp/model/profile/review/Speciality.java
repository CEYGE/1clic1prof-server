package fr.clic1prof.serverapp.model.profile.review;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Speciality {

    @NotNull
    @Size(min = 2, max = 64)
    private String speciality;

    public Speciality(String name) {
        this.speciality = name;
    }

    public String getValue() {
        return this.speciality;
    }
}
