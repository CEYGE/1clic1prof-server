package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.student.SchoolLevel;
import fr.clic1prof.serverapp.model.user.Email;

public class StudentProfileDAOImpl extends UserProfileDAOImpl implements StudentProfileDAO {

    @Override
    public boolean updateSchoolLevel(Email email, SchoolLevel schoolLevel) {

        String query = "UPDATE utilisateur SET user_niveauScolaire = ? WHERE user_email = ?;";

        return this.template.update(query, schoolLevel.getValue(), email.getValue()) > 0;
    }
}
