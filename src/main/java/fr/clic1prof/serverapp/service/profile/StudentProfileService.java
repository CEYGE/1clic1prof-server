package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.model.profile.SchoolLevelIdMapper;
import fr.clic1prof.serverapp.model.user.UserBase;

public interface StudentProfileService extends UserProfileService {

    boolean updateSchoolLevel(UserBase base, SchoolLevelIdMapper mapper);
}
