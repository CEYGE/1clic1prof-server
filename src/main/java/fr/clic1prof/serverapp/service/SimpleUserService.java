package fr.clic1prof.serverapp.service;

import fr.clic1prof.serverapp.dao.UserDAO;
import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.user.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserService {

    @Autowired
    @Qualifier("UserDAOImpl")
    private UserDAO dao;

    public boolean register(Registration registration) {

        // An account with the specified email already exists.
        if(this.isRegistered(registration.getEmail())) return false;

        return this.dao.register(registration);
    }

    public boolean isRegistered(Email email) {
        return this.dao.findByUsername(email.getValue()).isPresent();
    }
}
