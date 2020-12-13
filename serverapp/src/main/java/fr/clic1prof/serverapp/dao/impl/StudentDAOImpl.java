package fr.clic1prof.serverapp.dao.impl;

import fr.clic1prof.serverapp.dao.StudentDAO;
import fr.clic1prof.serverapp.dao.UserDAO;
import fr.clic1prof.serverapp.model.student.SchoolLevel;
import fr.clic1prof.serverapp.model.teacher.Description;
import fr.clic1prof.serverapp.model.teacher.StudyLevel;
import fr.clic1prof.serverapp.model.user.Email;

public class StudentDAOImpl extends UserDAOImpl implements StudentDAO {
    @Override
    public boolean updateSchoolLevel(Email email, SchoolLevel schoolLevel) {
        String query = "UPDATE utilisateur SET user_niveauScolaire = ? WHERE user_email = ?;";
        return this.template.update(query, schoolLevel.getValue(), email.getValue()) == 1;
    }
}
