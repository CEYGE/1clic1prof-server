package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.profile.review.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.profile.Studies;
import org.springframework.stereotype.Repository;

@Repository("TeacherProfileDAO")
public class TeacherProfileDAO extends UserProfileDAO implements ITeacherProfileDAO {

    @Override
    public boolean updateDescription(int id, Description description) {

        String query = "UPDATE utilisateur SET user_descriptionProfil = ? WHERE user_id = ?;";

        return this.template.update(query, description.getValue(), id) > 0;
    }

    @Override
    public boolean updateSpeciality(int id, SpecialityModifier modifier) {
        return false;
    }

    @Override
    public boolean updateStudies(int id, Studies level) {

        String query = "UPDATE utilisateur SET user_niveauEtude = ? WHERE user_id = ?;";

        return this.template.update(query, level.getValue(), id) > 0;
    }
}
