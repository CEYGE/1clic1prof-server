package fr.clic1prof.serverapp.dao;

import java.util.List;

public interface StudentDAO {

    List<Integer> getTeachers(int studentId);

    boolean hasTeacher(int studentId, int teacherId);

    boolean hasTeachers(int studentId, int... teacherIds);

    boolean isStudent(int id);
}
