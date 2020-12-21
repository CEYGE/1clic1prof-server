package fr.clic1prof.serverapp.dao.contacts;

import fr.clic1prof.serverapp.model.contacts.ContactModel;
import fr.clic1prof.serverapp.model.contacts.TeacherContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("StudentContactDAO")
public class StudentContactDAO implements ContactDAO {

    // This class manage all the contacts of a student.
    // So, the contacts retrieved are teachers.

    private JdbcTemplate template;

    @Autowired
    public StudentContactDAO(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Collection<ContactModel> getContacts(int id) {

        String query = "SELECT user_first_name, user_last_name, teacher_study_level " +
                "FROM teaching JOIN user ON teaching_teacher_id = user_id " +
                "JOIN teacher ON teacher_id = teaching_teacher__id " +
                "WHERE teaching_student_id = ?";

        RowMapper<ContactModel> mapper = (result, i) -> {

            String firstName = result.getString("user_first_name");
            String lastName = result.getString("user_last_name");
            String studyLevel = result.getString("teacher_study_level");

            return new TeacherContact(firstName, lastName, studyLevel);
        };
        return this.template.query(query, mapper, ContactModel.class, id);
    }
}
