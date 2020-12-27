package fr.clic1prof.serverapp.dao.other;

import fr.clic1prof.serverapp.model.profile.Speciality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository("TeacherSpecialityDAO")
public class TeacherSpecialityDAO implements ITeacherSpecialityDAO {

    private JdbcTemplate template;

    @Autowired
    public TeacherSpecialityDAO(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public boolean exists(int... ids) {

        Collection<Speciality> specialities = this.getSpecialities();

        return Arrays.stream(ids)
                .mapToObj(id -> specialities.stream().anyMatch(speciality -> speciality.getId() == id))
                .allMatch(exists -> exists);
    }

    @Override
    public List<Speciality> getSpecialities(int id) {

        String query = "SELECT subject_id, subject_name FROM subject " +
                "JOIN teachable_subject ON teachable_subject_id = subject_id " +
                "WHERE teachable_user_id = ?";

        return this.template.query(query, this.getSpecialityMapper(), id);
    }

    @Override
    public List<Speciality> getSpecialities() {

        String query = "SELECT * FROM subject;";

        return this.template.query(query, this.getSpecialityMapper());
    }

    private RowMapper<Speciality> getSpecialityMapper() {
        return (result, i) -> {

            int id = result.getInt("subject_id");
            String label = result.getString("subject_name");

            return new Speciality(id, label);
        };
    }
}
