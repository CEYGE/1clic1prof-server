package fr.clic1prof.serverapp.service;

import fr.clic1prof.serverapp.dao.IUserDAO;
import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.user.attributes.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    @Qualifier("UserDAO")
    private IUserDAO dao;

    @Override
    public boolean register(Registration registration) {

        // An account with the specified email already exists.
        if(this.isRegistered(registration.getEmail())) return false;

        return this.dao.register(registration);
    }

    @Override
    public boolean isRegistered(Email email) {
        return this.dao.findByUsername(email.getValue()).isPresent();
    }
}
