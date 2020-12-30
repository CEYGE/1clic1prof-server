package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.dao.other.TeacherSpecialityDAO;
import fr.clic1prof.serverapp.file.model.DocumentType;
import fr.clic1prof.serverapp.model.profile.Speciality;
import fr.clic1prof.serverapp.model.profile.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.profile.Studies;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.profile.model.TeacherProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TeacherProfileDAOImpl")
public class TeacherProfileDAOImpl extends UserProfileDAOImpl implements TeacherProfileDAO {

    private final TeacherSpecialityDAO specialityDAO;

    @Autowired
    public TeacherProfileDAOImpl(@Qualifier("TeacherSpecialityDAOImpl") TeacherSpecialityDAO specialityDAO) {
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

        String query = "SELECT user_first_name, user_last_name, user_picture, teacher_description, teacher_study_level " +
                "FROM user JOIN teacher ON user_id = teacher_id " +
                "LEFT OUTER JOIN document ON doc_owner_id = user_id " +
                "WHERE user_id = ? AND doc_type = ?;";

        RowMapper<Profile> mapper = (result, i) -> {

            String firstName = result.getString("user_first_name");
            String lastName = result.getString("user_last_name");
            int pictureId = result.getInt("doc_id");
            String description = result.getString("teacher_description");
            String studies = result.getString("teacher_study_level");

            return new TeacherProfile(id, firstName, lastName, pictureId, description, studies, specialities);
        };
        return this.template.queryForObject(query, mapper, id, DocumentType.PROFILE_PICTURE.name());
    }
}
