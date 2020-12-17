package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.profile.SchoolLevel;

public interface StudentProfileDAO extends UserProfileDAO {

    boolean updateSchoolLevel(int id, SchoolLevel schoolLevel);
}
