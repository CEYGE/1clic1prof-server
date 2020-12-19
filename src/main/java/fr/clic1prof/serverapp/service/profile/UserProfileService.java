package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.profile.IUserProfileDAO;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.profile.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class UserProfileService implements IUserProfileService {

    private IUserProfileDAO dao;
    private PasswordEncoder encoder;

    @Autowired
    public UserProfileService(IUserProfileDAO dao, PasswordEncoder encoder) {
        this.dao = dao;
        this.encoder = encoder;
    }

    @Override
    public boolean updatePassword(UserBase user, PasswordModifier modifier) {

        String encodedOldPassword = this.dao.getPassword(user.getId());
        String rawOldPassword = modifier.getOldPassword().getPassword();

        // Passwords are different.
        if(!this.encoder.matches(rawOldPassword, encodedOldPassword)) return false;

        String newPassword = modifier.getNewPassword().getPassword();
        String encodedNewPassword = this.encoder.encode(newPassword);

        return this.getUserDAO().updatePassword(user.getId(), encodedNewPassword);
    }

    @Override
    public boolean updateFirstName(UserBase user, Name firstName) {
        return this.getUserDAO().updateFirstName(user.getId(), firstName.getValue());
    }

    @Override
    public boolean updateLastName(UserBase user, Name lastName) {
        return this.getUserDAO().updateLastName(user.getId(), lastName.getValue());
    }

    public IUserProfileDAO getUserDAO() {
        return this.dao;
    }
}
