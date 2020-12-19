package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.profile.ITeacherProfileDAO;
import fr.clic1prof.serverapp.dao.profile.IUserProfileDAO;
import fr.clic1prof.serverapp.model.modifier.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("TeacherProfileService")
public class TeacherProfileService extends UserProfileService implements ITeacherProfileService {

    @Autowired
    public TeacherProfileService(@Qualifier("TeacherProfileDAO") IUserProfileDAO dao, PasswordEncoder encoder) {
        super(dao, encoder);
    }

    @Override
    public boolean updateDescription(UserBase user, Description description) {
        return this.getUserDAO().updateDescription(user.getId(), description);
    }

    @Override
    public boolean updateSpeciality(UserBase user, SpecialityModifier modifier) {
        return false;
    }

    @Override
    public ITeacherProfileDAO getUserDAO() {
        return (ITeacherProfileDAO) super.getUserDAO();
    }
}
