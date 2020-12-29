package fr.clic1prof.serverapp.service;

import fr.clic1prof.serverapp.dao.UserDAO;
import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.profile.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO dao;
    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(@Qualifier("UserDAOImpl") UserDAO dao, PasswordEncoder encoder) {
        this.dao = dao;
        this.encoder = encoder;
    }

    @Override
    public boolean register(Registration registration) {

        // An account with the specified email already exists.
        if(this.isRegistered(registration.getEmail())) return false;

        String password = registration.getPassword().getPassword();
        String encoded = this.encoder.encode(password);

        registration.setEncodedPassword(encoded);

        return this.dao.register(registration);
    }

    @Override
    public boolean isRegistered(Email email) {
        return this.dao.findByUsername(email.getEmail()).isPresent();
    }
}
