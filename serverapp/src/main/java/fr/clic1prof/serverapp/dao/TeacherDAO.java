package fr.clic1prof.serverapp.dao;

import fr.clic1prof.serverapp.model.teacher.Description;
import fr.clic1prof.serverapp.model.teacher.Speciality;
import fr.clic1prof.serverapp.model.teacher.StudyLevel;
import fr.clic1prof.serverapp.model.user.Email;

public interface TeacherDAO extends UserDAO {
    boolean updateDescription(Email email, Description description);
    boolean updateSpeciality(Email email, Speciality speciality);
    boolean updateStudyLevel(Email email, StudyLevel studyLevel);
}
