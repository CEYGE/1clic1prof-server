package fr.clic1prof.serverapp.dao.contacts;

import fr.clic1prof.serverapp.model.contacts.ContactModel;
import fr.clic1prof.serverapp.model.contacts.StudentContact;
import fr.clic1prof.serverapp.model.profile.SchoolLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("TeacherContactDAO")

public class TeacherContactDAO implements ContactDAO {

    // This class manage all the contacts of a teacher.
    // So, the contacts retrieved are students.

    private JdbcTemplate template;

    @Autowired
    public TeacherContactDAO(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Collection<ContactModel> getContacts(int id) {

        String query = "SELECT user_first_name, user_last_name, school_level_id, school_level_name " +
                "FROM teaching JOIN user ON teaching_student_id = user_id " +
                "JOIN student ON student_id = teaching_student_id " +
                "JOIN school_level ON school_level_id = student_scholar_level_id " +
                "WHERE teaching_teacher_id = ?";

        RowMapper<ContactModel> mapper = (result, i) -> {

            String firstName = result.getString("user_first_name");
            String lastName = result.getString("user_last_name");
            SchoolLevel level = new SchoolLevel(result.getInt("school_level_id"), result.getString("school_level_name"));

            return new StudentContact(firstName, lastName, level);
        };
        return this.template.queryForList(query, ContactModel.class, mapper, id);
    }
}