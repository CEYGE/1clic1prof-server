package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.profile.review.SchoolLevel;
import org.springframework.stereotype.Repository;

@Repository("StudentProfileDAO")
public class StudentProfileDAO extends UserProfileDAO implements IStudentProfileDAO {

    @Override
    public boolean updateSchoolLevel(int id, SchoolLevel schoolLevel) {

        String query = "UPDATE student SET user_student_scholar_level_id = ? WHERE user_id = ?;";

        return this.template.update(query, schoolLevel.getValue(), id) > 0;
    }
}
