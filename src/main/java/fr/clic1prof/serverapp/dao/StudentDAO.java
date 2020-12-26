package fr.clic1prof.serverapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("StudentDAO")
public class StudentDAO implements IStudentDAO {

    private final JdbcTemplate template;

    @Autowired
    public StudentDAO(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Integer> getTeachers(int studentId) {

        String query = "SELECT teaching_teacher_id FROM teaching WHERE teaching_student_id = ?;";

        return this.template.queryForList(query, Integer.class, studentId);
    }

    @Override
    public boolean hasTeacher(int studentId, int teacherId) {

        List<Integer> teachers = this.getTeachers(studentId);

        return teachers.contains(teacherId);
    }

    @Override
    public boolean hasTeachers(int studentId, int... teacherIds) {

        List<Integer> teachers = this.getTeachers(studentId);

        return Arrays.stream(teacherIds).allMatch(teachers::contains);
    }

    @Override
    public boolean isStudent(int id) {

        String query = "SELECT COUNT(1) FROM student WHERE student_id = ?;";

        Integer value = this.template.queryForObject(query, Integer.class);

        return value != null && value == 1;
    }
}
