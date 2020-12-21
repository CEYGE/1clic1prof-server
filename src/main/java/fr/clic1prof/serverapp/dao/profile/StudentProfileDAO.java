package fr.clic1prof.serverapp.dao.profile;

import org.springframework.stereotype.Repository;

@Repository("StudentProfileDAO")
public class StudentProfileDAO extends UserProfileDAO implements IStudentProfileDAO {

    @Override
    public boolean updateSchoolLevel(int id, int schoolLevelId) {

        String query = "UPDATE student SET student_scholar_level_id = ? WHERE student_id = ?;";

        return this.template.update(query, schoolLevelId, id) > 0;
    }
}
