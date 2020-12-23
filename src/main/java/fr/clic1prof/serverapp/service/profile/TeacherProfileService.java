package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.other.ITeacherSpecialityDAO;
import fr.clic1prof.serverapp.dao.profile.ITeacherProfileDAO;
import fr.clic1prof.serverapp.dao.profile.IUserProfileDAO;
import fr.clic1prof.serverapp.model.profile.Speciality;
import fr.clic1prof.serverapp.model.profile.Studies;
import fr.clic1prof.serverapp.model.profile.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("TeacherProfileService")
public class TeacherProfileService extends UserProfileService implements ITeacherProfileService {

    private ITeacherSpecialityDAO specialityDAO;

    @Autowired
    public TeacherProfileService(@Qualifier("TeacherProfileDAO") IUserProfileDAO dao,
                                 @Qualifier("TeacherSpecialityDAO") ITeacherSpecialityDAO specialityDAO,
                                 PasswordEncoder encoder) {
        super(dao, encoder);
        this.specialityDAO = specialityDAO;
    }

    @Override
    public boolean updateDescription(UserBase user, Description description) {
        return this.getUserDAO().updateDescription(user.getId(), description);
    }

    @Override
    public boolean updateSpeciality(UserBase user, SpecialityModifier modifier) {

        if(!this.isValid(user, modifier)) return false;

        return this.getUserDAO().updateSpeciality(user.getId(), modifier);
    }

    @Override
    public boolean updateStudies(UserBase user, Studies studies) {
        return this.getUserDAO().updateStudies(user.getId(), studies);
    }

    @Override
    public ITeacherProfileDAO getUserDAO() {
        return (ITeacherProfileDAO) super.getUserDAO();
    }

    private boolean isValid(UserBase base, SpecialityModifier modifier) {

        Collection<Speciality> owned = this.specialityDAO.getSpecialities(base.getId());

        // Checking that the user own the speciality he wants to replace.
        boolean isReplaceWithOwned = owned.stream()
                .anyMatch(speciality -> speciality.getId() == modifier.getToReplace());

        if(!isReplaceWithOwned) return false;

        // Checking that the user doesn't own the speciality he wants to replace with.
        boolean isToReplaceValid = owned.stream()
                .noneMatch(speciality -> speciality.getId() == modifier.getReplaceWith());

        if(!isToReplaceValid) return false;

        // Checking that the speciality he wants to replace with exists.
        return this.specialityDAO.exists(modifier.getReplaceWith());
    }
}
