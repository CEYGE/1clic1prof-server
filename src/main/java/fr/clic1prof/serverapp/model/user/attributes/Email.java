package fr.clic1prof.serverapp.model.user.attributes;

import javax.validation.constraints.NotNull;

public class Email {

    @NotNull
    @javax.validation.constraints.Email
    private String username;

    public Email(String username) {
        this.username = username;
    }

    public String getValue() {
        return this.username;
    }
}
