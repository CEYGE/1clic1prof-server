package fr.clic1prof.serverapp.service;

import fr.clic1prof.serverapp.dao.profile.UserProfileDAO;
import fr.clic1prof.serverapp.model.user.Email;
import fr.clic1prof.serverapp.model.user.Name;
import fr.clic1prof.serverapp.model.user.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    @Qualifier("UserProfileDAOImpl")
    private UserProfileDAO dao;

    public boolean updatePassword(Email email, Password password) {
        return this.dao.updatePassword(email, password);
    }

    public boolean updateFirstName(Email email, Name firstName) {
        return this.dao.updateFirstName(email, firstName);
    }

    public boolean updateLastName(Email email, Name lastName) {
        return this.dao.updateLastName(email, lastName);
    }
}
