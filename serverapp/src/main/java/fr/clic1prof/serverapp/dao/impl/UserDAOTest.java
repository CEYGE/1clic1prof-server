package fr.clic1prof.serverapp.dao.impl;

import fr.clic1prof.serverapp.dao.UserDAO;
import fr.clic1prof.serverapp.model.SimpleUser;
import fr.clic1prof.serverapp.model.Role;
import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.teacher.Description;
import fr.clic1prof.serverapp.model.teacher.StudyLevel;
import fr.clic1prof.serverapp.model.user.Email;
import fr.clic1prof.serverapp.model.user.Name;
import fr.clic1prof.serverapp.model.user.Password;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserDAOTest")
public class UserDAOTest implements UserDAO {

    @Override
    public Optional<SimpleUser> findByUsername(String username) {

        SimpleUser user = new SimpleUser("renardo", "fox", Role.TEACHER);

        return Optional.of(user);
    }

    @Override
    public boolean register(Registration registration) {
        return true;
    }

    @Override
    public boolean updatePassword(Email email, Password password) {
        return false;
    }

    @Override
    public boolean updateFirstName(Email email, Name name) {
        return false;
    }

    @Override
    public boolean updateLastName(Email email, Name name) {
        return false;
    }

    @Override
    public boolean updateRole(Email email, Role name) {
        return false;
    }

    @Override
    public boolean updatePicture(Email email, String picture) {
        return false;
    }

    @Override
    public boolean updateNotification(Email email, boolean flag) {
        return false;
    }
}
