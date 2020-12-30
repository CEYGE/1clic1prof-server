package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.profile.SchoolLevel;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.profile.model.StudentProfile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("StudentProfileDAOImpl")
public class StudentProfileDAOImpl extends UserProfileDAOImpl implements StudentProfileDAO {

    @Override
    public boolean updateSchoolLevel(int id, int schoolLevelId) {

        String query = "UPDATE student SET student_scholar_level_id = ? WHERE student_id = ?;";

        return this.template.update(query, schoolLevelId, id) > 0;
    }

    @Override
    public Profile getProfile(int id) {

        String query = "SELECT user_first_name, user_last_name, user_picture, school_level_name, school_level_id " +
                "FROM user JOIN student ON user_id = student_id " +
                "LEFT OUTER JOIN school_level ON student_scholar_level_id = school_level_id " +
                "WHERE user_id = ?;";

        RowMapper<Profile> mapper = (result, i) -> {

            String firstName = result.getString("user_first_name");
            String lastName = result.getString("user_last_name");
            String pictureUrl = result.getString("user_picture");

            int schoolLevelId = result.getInt("school_level_id");

            SchoolLevel level = schoolLevelId != 0 ? new SchoolLevel(schoolLevelId, result.getString("school_level_name")) : null;

            return new StudentProfile(id, firstName, lastName, -1, level);
        };
        return this.template.queryForObject(query, mapper, id);
    }
}
