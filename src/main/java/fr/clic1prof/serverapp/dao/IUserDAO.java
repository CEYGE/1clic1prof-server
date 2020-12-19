package fr.clic1prof.serverapp.dao;

import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.user.UserModel;

import java.util.Optional;

public interface IUserDAO {

    Optional<UserModel> findByUsername(String username);

    boolean register(Registration registration);
}
