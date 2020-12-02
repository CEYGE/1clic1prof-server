package fr.clic1prof.serverapp.model;

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
