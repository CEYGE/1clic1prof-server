package fr.clic1prof.serverapp.security.jwt.authentication;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AuthenticationRequest {

    @NotNull @NotBlank
    private final String username;

    @NotNull @NotBlank
    private final String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
