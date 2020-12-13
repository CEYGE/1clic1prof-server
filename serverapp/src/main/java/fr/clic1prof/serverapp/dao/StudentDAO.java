package fr.clic1prof.serverapp.dao;

import fr.clic1prof.serverapp.model.student.SchoolLevel;
import fr.clic1prof.serverapp.model.user.Email;

public interface StudentDAO extends UserDAO {
    boolean updateSchoolLevel(Email email, SchoolLevel schoolLevel);
}
