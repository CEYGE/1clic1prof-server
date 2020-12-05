package fr.clic1prof.serverapp.model;

import fr.clic1prof.serverapp.model.user.Notification;
import fr.clic1prof.serverapp.model.user.Picture;

public class SimpleUser {

    private final String username, password;
    private final Role role;

    public SimpleUser(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
    }

}
