package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.other.TeacherSpecialityDAO;
import fr.clic1prof.serverapp.dao.profile.TeacherProfileDAO;
import fr.clic1prof.serverapp.dao.profile.UserProfileDAO;
import fr.clic1prof.serverapp.file.service.DocumentService;
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

@Service("TeacherProfileServiceImpl")
public class TeacherProfileServiceImpl extends UserProfileServiceImpl implements TeacherProfileService {

    private TeacherSpecialityDAO specialityDAO;

    @Autowired
    public TeacherProfileServiceImpl(@Qualifier("TeacherProfileDAOImpl") UserProfileDAO dao,
                                     @Qualifier("TeacherSpecialityDAOImpl") TeacherSpecialityDAO specialityDAO,
                                     PasswordEncoder encoder, DocumentService service) {
        super(dao, encoder, service);
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
    public TeacherProfileDAO getUserDAO() {
        return (TeacherProfileDAO) super.getUserDAO();
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
