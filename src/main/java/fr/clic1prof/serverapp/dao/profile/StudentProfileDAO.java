package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.profile.SchoolLevel;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.profile.model.StudentProfile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("StudentProfileDAO")
public class StudentProfileDAO extends UserProfileDAO implements IStudentProfileDAO {

    @Override
    public boolean updateSchoolLevel(int id, int schoolLevelId) {

        String query = "UPDATE student SET student_scholar_level_id = ? WHERE student_id = ?;";

        return this.template.update(query, schoolLevelId, id) > 0;
    }

    @Override
    public Profile getProfile(int id) {

        String query = "SELECT user_first_name, user_last_name, school_level_name, school_level_id " +
                "FROM user JOIN student ON user_id = student_id " +
                "JOIN school_level ON student_scholar_level_id = school_level_id " +
                "WHERE user_id = ?;";

        RowMapper<Profile> mapper = (result, i) -> {

            String firstName = result.getString("user_first_name");
            String lastName = result.getString("user_last_name");
            String pictureUrl = result.getString("user_picture");
            SchoolLevel level = new SchoolLevel(result.getInt("school_level_id"), result.getString("school_level_name"));

            return new StudentProfile(id, firstName, lastName, pictureUrl, level);
        };
        return this.template.queryForObject(query, mapper, id);
    }
}
