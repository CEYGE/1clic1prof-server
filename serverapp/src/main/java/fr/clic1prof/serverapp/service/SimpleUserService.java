package fr.clic1prof.serverapp.service;

import fr.clic1prof.serverapp.dao.UserDAO;
import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.user.Email;
import fr.clic1prof.serverapp.model.user.Name;
import fr.clic1prof.serverapp.model.user.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserService {

    @Autowired
    @Qualifier("UserDAOImpl")
    private UserDAO dao;

    public boolean register(Registration registration) {

        if(this.isRegistered(registration.getEmail())) return false;

        return this.dao.register(registration);
    }

    public boolean updatePassword(Email email, Password password) {
        return this.dao.updatePassword(email, password);
    }

    public boolean updateFirstName(Email email, Name name) {
        return this.dao.updateFirstName(email, name);
    }

    public boolean updateLastName(Email email, Name name) {
        return this.dao.updateLastName(email, name);
    }

    public boolean isRegistered(Email email) {
        return this.dao.findByUsername(email.getValue()).isPresent();
    }
}
