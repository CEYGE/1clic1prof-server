package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.dao.other.ITeacherSpecialityDAO;
import fr.clic1prof.serverapp.model.profile.Speciality;
import fr.clic1prof.serverapp.model.profile.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.profile.Studies;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.profile.model.TeacherProfile;
import fr.clic1prof.serverapp.model.profile.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository("TeacherProfileDAO")
public class TeacherProfileDAO extends UserProfileDAO implements ITeacherProfileDAO {

    private final ITeacherSpecialityDAO specialityDAO;

    @Autowired
    public TeacherProfileDAO(@Qualifier("TeacherSpecialityDAO") ITeacherSpecialityDAO specialityDAO) {
        this.specialityDAO = specialityDAO;
    }

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

        List<Speciality> specialities = this.specialityDAO.getSpecialities(id);

        String query = "SELECT user_first_name, user_last_name, user_picture, teacher_description, teacher_studies " +
                "FROM user JOIN teacher ON user_id = teacher_id " +
                "WHERE user_id = ?";

        RowMapper<Profile> mapper = (result, i) -> {

            String firstName = result.getString("user_first_name");
            String lastName = result.getString("user_last_name");
            String pictureUrl = result.getString("user_picture");
            String description = result.getString("teacher_description");
            String studies = result.getString("teacher_studies");

            return new TeacherProfile(firstName, lastName, pictureUrl, description, studies, specialities);
        };
        return this.template.queryForObject(query, mapper, id);
    }
}
