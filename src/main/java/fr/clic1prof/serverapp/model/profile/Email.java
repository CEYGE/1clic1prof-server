package fr.clic1prof.serverapp.model.profile;

import javax.validation.constraints.NotNull;

public class Email {

    @NotNull
    @javax.validation.constraints.Email
    private String email;

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
