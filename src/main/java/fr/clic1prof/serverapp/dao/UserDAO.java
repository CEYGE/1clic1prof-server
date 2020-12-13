package fr.clic1prof.serverapp.dao;

import fr.clic1prof.serverapp.model.SimpleUser;
import fr.clic1prof.serverapp.model.registration.Registration;

import java.util.Optional;

public interface UserDAO {

    Optional<SimpleUser> findByUsername(String username);

    boolean register(Registration registration);
}
