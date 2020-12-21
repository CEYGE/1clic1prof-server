package fr.clic1prof.serverapp.dao;

import fr.clic1prof.serverapp.model.profile.SchoolLevel;

import java.util.Collection;

public interface IStudentSchoolLevelDAO {

    Collection<SchoolLevel> getSchoolLevels();

    boolean exists(int schoolLevelId);
}
