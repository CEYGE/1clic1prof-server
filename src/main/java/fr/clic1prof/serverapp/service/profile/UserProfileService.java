package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.profile.IUserProfileDAO;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.attributes.Name;
import fr.clic1prof.serverapp.model.user.attributes.Password;

public abstract class UserProfileService implements IUserProfileService {

    private IUserProfileDAO dao;

    public UserProfileService(IUserProfileDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean updatePassword(UserBase user, Password password) {
        return this.getUserDAO().updatePassword(user.getId(), password);
    }

    @Override
    public boolean updateFirstName(UserBase user, Name firstName) {
        return this.getUserDAO().updateFirstName(user.getId(), firstName);
    }

    @Override
    public boolean updateLastName(UserBase user, Name lastName) {
        return this.getUserDAO().updateLastName(user.getId(), lastName);
    }

    public IUserProfileDAO getUserDAO() {
        return this.dao;
    }
}
