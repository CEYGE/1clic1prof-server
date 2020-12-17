package fr.clic1prof.serverapp.model.user;

import fr.clic1prof.serverapp.model.user.attributes.Role;

import java.util.List;

public interface User extends UserBase {

    String getEmail();

    String getPassword();

    List<Role> getRoles();
}
