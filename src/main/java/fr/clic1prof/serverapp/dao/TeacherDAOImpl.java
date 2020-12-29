package fr.clic1prof.serverapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("TeacherDAOImpl")
public class TeacherDAOImpl implements TeacherDAO {

    private final JdbcTemplate template;

    @Autowired
    public TeacherDAOImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Integer> getStudents(int teacherId) {

        String query = "SELECT teaching_student_id FROM teaching WHERE teaching_teacher_id = ?;";

        return this.template.queryForList(query, Integer.class, teacherId);
    }

    @Override
    public boolean hasStudent(int teacherId, int studentId) {

        List<Integer> students = this.getStudents(teacherId);

        return students.contains(studentId);
    }

    @Override
    public boolean hasStudents(int teacherId, int... studentIds) {

        List<Integer> students = this.getStudents(teacherId);

        return Arrays.stream(studentIds).allMatch(students::contains);
    }

    @Override
    public boolean isTeacher(int id) {

        String query = "SELECT COUNT(1) FROM teacher WHERE teacher_id = ?;";

        Integer value = this.template.queryForObject(query, Integer.class, id);

        return value != null && value == 1;
    }
}
