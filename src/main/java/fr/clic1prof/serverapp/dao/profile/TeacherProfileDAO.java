package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.modifier.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.profile.StudyLevel;
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
    public boolean updateStudyLevel(int id, StudyLevel studyLevel) {

        String query = "UPDATE utilisateur SET user_niveauEtude = ? WHERE user_id = ?;";

        return this.template.update(query, studyLevel.getValue(), id) > 0;
    }
}
