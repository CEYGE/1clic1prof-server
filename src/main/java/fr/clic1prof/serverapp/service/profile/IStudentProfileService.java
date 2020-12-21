package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.model.profile.SchoolLevelIdMapper;
import fr.clic1prof.serverapp.model.user.UserBase;

public interface IStudentProfileService extends IUserProfileService {

    boolean updateSchoolLevel(UserBase base, SchoolLevelIdMapper mapper);
}
