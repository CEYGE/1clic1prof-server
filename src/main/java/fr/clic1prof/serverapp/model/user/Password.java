package fr.clic1prof.serverapp.model.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Password {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    @Size(min = 8, max = 32)
    // Required : 8 chars with 1 min, 1 maj, 1 special char and 1 number.
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,32}$")
    private String password;

    public Password() {} // Used by JSON.

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public String getHashed() {
        return encoder.encode(this.password);
    }
}
