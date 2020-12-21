package fr.clic1prof.serverapp.dao;

import fr.clic1prof.serverapp.model.profile.SchoolLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("StudentSchoolLevelDAO")
public class StudentSchoolLevelDAO implements IStudentSchoolLevelDAO {

    private JdbcTemplate template;

    @Autowired
    public StudentSchoolLevelDAO(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<SchoolLevel> getSchoolLevels() {

        String query = "SELECT * FROM school_level;";

        return this.template.query(query, this.getSchoolLevelMapper());
    }

    @Override
    public boolean exists(int schoolLevelId) {

        String query = "SELECT COUNT(1) FROM school_level WHERE school_level_id = ?;";

        Integer value = this.template.queryForObject(query, Integer.class, schoolLevelId);

        return value != null && value == 1;
    }

    private RowMapper<SchoolLevel> getSchoolLevelMapper() {
        return (result, i) -> {

            int id = result.getInt("school_level_id");
            String label = result.getString("school_level_name");

            return new SchoolLevel(id, label);
        };
    }
}
