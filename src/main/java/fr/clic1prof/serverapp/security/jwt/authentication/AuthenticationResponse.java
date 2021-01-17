package fr.clic1prof.serverapp.security.jwt.authentication;

import fr.clic1prof.serverapp.model.user.UserRole;

public class AuthenticationResponse {

    private final String token;
    private final UserRole role; // Maybe this will be removed later.

    public AuthenticationResponse(String token, UserRole role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return this.token;
    }

    public UserRole getRole() {
        return this.role;
    }
}
