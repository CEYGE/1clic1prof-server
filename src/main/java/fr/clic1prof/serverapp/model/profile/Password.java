package fr.clic1prof.serverapp.model.profile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Password {

    // Required : 8 chars with 1 min, 1 maj, 1 special char and 1 number.
    // Must be between 8 and 32 characters.
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,32}$";

    @NotNull
    @Pattern(regexp = PASSWORD_REGEX)
    private String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
