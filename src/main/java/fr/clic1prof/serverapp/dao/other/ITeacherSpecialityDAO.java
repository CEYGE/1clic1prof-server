package fr.clic1prof.serverapp.dao.other;

import fr.clic1prof.serverapp.model.profile.Speciality;

import java.util.Collection;
import java.util.List;

public interface ITeacherSpecialityDAO {

    boolean exists(int... ids);

    List<Speciality> getSpecialities(int id);

    List<Speciality> getSpecialities();
}
