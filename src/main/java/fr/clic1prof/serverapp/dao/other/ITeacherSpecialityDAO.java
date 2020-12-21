package fr.clic1prof.serverapp.dao.other;

import fr.clic1prof.serverapp.model.profile.Speciality;

import java.util.Collection;

public interface ITeacherSpecialityDAO {

    boolean exists(int... ids);

    Collection<Speciality> getSpecialities(int id);

    Collection<Speciality> getSpecialities();
}
