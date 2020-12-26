package fr.clic1prof.serverapp.dao;

import java.util.List;

public interface ITeacherDAO {

    List<Integer> getStudents(int teacherId);

    boolean hasStudent(int teacherId, int studentId);

    boolean hasStudents(int teacherId, int... studentIds);

    boolean isTeacher(int id);
}
