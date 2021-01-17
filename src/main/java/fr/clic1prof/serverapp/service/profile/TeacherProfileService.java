package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.model.profile.Studies;
import fr.clic1prof.serverapp.model.profile.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.user.UserBase;

public interface TeacherProfileService extends UserProfileService {

    boolean updateDescription(int userId, Description description);

    boolean updateSpeciality(int userId, SpecialityModifier modifier);

    boolean updateStudies(int userId, Studies studies);
}
