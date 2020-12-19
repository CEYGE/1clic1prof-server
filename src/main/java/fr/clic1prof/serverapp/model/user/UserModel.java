package fr.clic1prof.serverapp.model.user;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public interface UserModel extends UserBase, UserDetails {

    Collection<UserRole> getRoles();
}
