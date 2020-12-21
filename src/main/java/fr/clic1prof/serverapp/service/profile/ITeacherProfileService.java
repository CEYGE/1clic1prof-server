package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.model.profile.Studies;
import fr.clic1prof.serverapp.model.profile.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.user.UserBase;

public interface ITeacherProfileService extends IUserProfileService {

    boolean updateDescription(UserBase user, Description description);

    boolean updateSpeciality(UserBase user, SpecialityModifier modifier);

    boolean updateStudies(UserBase user, Studies studies);
}
