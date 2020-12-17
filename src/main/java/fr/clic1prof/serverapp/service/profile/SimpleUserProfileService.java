package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.profile.UserProfileDAO;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.attributes.Name;
import fr.clic1prof.serverapp.model.user.attributes.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("SimpleUserProfileService")
public class SimpleUserProfileService extends UserProfileService {

    @Autowired
    public SimpleUserProfileService(@Qualifier("UserProfileDAOImpl") UserProfileDAO dao) {
        super(dao);
    }

    public boolean updatePassword(UserBase user, Password password) {
        return this.getDao().updatePassword(user.getId(), password);
    }

    public boolean updateFirstName(UserBase user, Name firstName) {
        return this.getDao().updateFirstName(user.getId(), firstName);
    }

    public boolean updateLastName(UserBase user, Name lastName) {
        return this.getDao().updateLastName(user.getId(), lastName);
    }
}
