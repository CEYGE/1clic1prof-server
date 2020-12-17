package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.profile.TeacherProfileDAO;
import fr.clic1prof.serverapp.dao.profile.UserProfileDAO;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.modifier.SpecialityModifier;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("TeacherProfileService")
public class TeacherProfileService extends UserProfileService {

    @Autowired
    public TeacherProfileService(@Qualifier("TeacherProfileDAOImpl") UserProfileDAO dao) {
        super(dao);
    }

    public boolean updateDescription(UserBase user, Description description) {
        return this.getDao().updateDescription(user.getId(), description);
    }

    public boolean updateSpeciality(UserBase user, SpecialityModifier modifier) {
        return false;
    }

    @Override
    public TeacherProfileDAO getDao() {
        return (TeacherProfileDAO) super.getDao();
    }
}
