package fr.clic1prof.serverapp.dao.other;

import fr.clic1prof.serverapp.model.profile.SchoolLevel;

import java.util.Collection;
import java.util.List;

public interface StudentSchoolLevelDAO {

    List<SchoolLevel> getSchoolLevels();

    boolean exists(int schoolLevelId);
}
