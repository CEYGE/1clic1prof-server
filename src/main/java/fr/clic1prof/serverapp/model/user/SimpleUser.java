package fr.clic1prof.serverapp.model.user;

import fr.clic1prof.serverapp.model.user.attributes.Role;

import java.util.Collections;
import java.util.List;

public class SimpleUser extends SimpleUserBase implements User {

    private final String email;
    private final String password;
    private final List<Role> roles;

    public SimpleUser(int id, String email, String password, List<Role> roles) {
        super(id);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public List<Role> getRoles() {
        return Collections.unmodifiableList(this.roles);
    }
}
