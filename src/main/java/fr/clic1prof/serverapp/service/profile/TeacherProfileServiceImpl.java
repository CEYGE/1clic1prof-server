package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.other.TeacherSpecialityDAO;
import fr.clic1prof.serverapp.dao.profile.TeacherProfileDAO;
import fr.clic1prof.serverapp.dao.profile.UserProfileDAO;
import fr.clic1prof.serverapp.exceptions.InvalidSpecialityException;
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
import java.util.List;

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
    public boolean updateDescription(int userId, Description description) {
        return this.getUserDAO().updateDescription(userId, description);
    }

    @Override
    public boolean updateSpeciality(int userId, SpecialityModifier modifier) {

        this.checkSpecialities(userId, modifier);

        return this.getUserDAO().updateSpeciality(userId, modifier);
    }

    @Override
    public boolean updateStudies(int userId, Studies studies) {
        return this.getUserDAO().updateStudies(userId, studies);
    }

    @Override
    public TeacherProfileDAO getUserDAO() {
        return (TeacherProfileDAO) super.getUserDAO();
    }

    private void checkSpecialities(int userId, SpecialityModifier modifier) {

        List<Speciality> owned = this.specialityDAO.getSpecialities(userId);

        final int toReplace = modifier.getToReplace();
        final int replaceWith = modifier.getReplaceWith();

        // Checking that the user own the speciality he wants to replace.
        boolean isReplaceWithOwned = owned.stream()
                .anyMatch(speciality -> speciality.getId() == toReplace);

        if(!isReplaceWithOwned)
            throw new InvalidSpecialityException("Trying to change a speciality not owned.", toReplace);

        // Checking that the user doesn't own the speciality he wants to replace with.
        boolean isToReplaceValid = owned.stream()
                .noneMatch(speciality -> speciality.getId() == replaceWith);

        if(!isToReplaceValid)
            throw new InvalidSpecialityException("Trying to change a speciality by another one which is already owned.", replaceWith);

        // Checking that the speciality he wants to replace with exists.
        if(!this.specialityDAO.exists(modifier.getReplaceWith()))
            throw new InvalidSpecialityException(String.format("No speciality with the id %d found.", modifier.getReplaceWith()), replaceWith);
    }
}
