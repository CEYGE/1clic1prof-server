package fr.clic1prof.serverapp.dao;

import fr.clic1prof.serverapp.model.Role;
import fr.clic1prof.serverapp.model.SimpleUser;
import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.teacher.Description;
import fr.clic1prof.serverapp.model.teacher.StudyLevel;
import fr.clic1prof.serverapp.model.user.Email;
import fr.clic1prof.serverapp.model.user.Name;
import fr.clic1prof.serverapp.model.user.Password;

import java.util.Optional;

public interface UserDAO {

    Optional<SimpleUser> findByUsername(String username);

    boolean register(Registration registration);

    boolean updatePassword(Email email, Password password);

    boolean updateFirstName(Email email, Name name);

    boolean updateLastName(Email email, Name name);

    boolean updateRole(Email email, Role name);

    boolean updatePicture(Email email, String picture);

    boolean updateNotification(Email email, boolean flag);
}
