package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.student.SchoolLevel;
import fr.clic1prof.serverapp.model.user.Email;

public interface StudentProfileDAO {

    boolean updateSchoolLevel(Email email, SchoolLevel schoolLevel);
}
