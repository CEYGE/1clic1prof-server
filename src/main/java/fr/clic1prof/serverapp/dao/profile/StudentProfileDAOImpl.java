package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.profile.SchoolLevel;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("StudentProfileDAOImpl")
public class StudentProfileDAOImpl extends UserProfileDAOImpl implements StudentProfileDAO {

    @Override
    public boolean updateSchoolLevel(int id, SchoolLevel schoolLevel) {

        String query = "UPDATE utilisateur SET user_niveauScolaire = ? WHERE user_id = ?;";

        return this.template.update(query, schoolLevel.getValue(), id) > 0;
    }
}
