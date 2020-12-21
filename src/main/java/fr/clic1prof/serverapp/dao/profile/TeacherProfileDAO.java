package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.profile.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.profile.Studies;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.profile.model.UserProfile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("TeacherProfileDAO")
public class TeacherProfileDAO extends UserProfileDAO implements ITeacherProfileDAO {

    @Override
    public boolean updateDescription(int id, Description description) {

        String query = "UPDATE teacher SET teacher_description = ? WHERE teacher_id = ?;";

        return this.template.update(query, description.getValue(), id) > 0;
    }

    @Override
    public boolean updateSpeciality(int id, SpecialityModifier modifier) {

        String query = "UPDATE teachable_subject SET teachable_replace_subject_id = ? WHERE " +
                "teachable_subject_id = ? AND teachable_user_id = ? ";

        return this.template.update(query, modifier.getReplaceWith(), modifier.getToReplace(), id) > 0;
    }

    @Override
    public boolean updateStudies(int id, Studies level) {

        String query = "UPDATE teacher SET teacher_study_level = ? WHERE teacher_id = ?;";

        return this.template.update(query, level.getValue(), id) > 0;
    }

    @Override
    public Profile getProfile(int id) {

        String query = "SELECT user_first_name, user_last_name FROM user WHERE user_id = ?";

        RowMapper<Profile> mapper = (result, i) -> {

            String firstName = result.getString("user_first_name");
            String lastName = result.getString("user_last_name");

            return new UserProfile(firstName, lastName);
        };
        return this.template.queryForObject(query, mapper, id);
    }
}
