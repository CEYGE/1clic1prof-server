package fr.clic1prof.serverapp.dao.impl;

import fr.clic1prof.serverapp.dao.TeacherDAO;
import fr.clic1prof.serverapp.dao.UserDAO;
import fr.clic1prof.serverapp.model.teacher.Description;
import fr.clic1prof.serverapp.model.teacher.Speciality;
import fr.clic1prof.serverapp.model.teacher.StudyLevel;
import fr.clic1prof.serverapp.model.user.Email;

public class TeacherDAOImpl extends UserDAOImpl implements TeacherDAO {
    @Override
    public boolean updateDescription(Email email, Description description) {
        String query = "UPDATE utilisateur SET user_descriptionProfil = ? WHERE user_email = ?;";
        return this.template.update(query, description.getValue(), email.getValue()) == 1;
    }

    @Override
    public boolean updateSpeciality(Email email, Speciality speciality) {
        String query = "UPDATE utilisateur SET user_specialite = ? WHERE user_email = ?;";
        return this.template.update(query, speciality.getValue(), email.getValue()) == 1;
    }

    @Override
    public boolean updateStudyLevel(Email email, StudyLevel studyLevel) {
        String query = "UPDATE utilisateur SET user_niveauEtude = ? WHERE user_email = ?;";
        return this.template.update(query, studyLevel.getValue(), email.getValue()) == 1;
    }
}
