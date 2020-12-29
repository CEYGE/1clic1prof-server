package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.profile.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.profile.Studies;

public interface TeacherProfileDAO extends UserProfileDAO {

    boolean updateDescription(int id, Description description);

    boolean updateSpeciality(int id, SpecialityModifier modifier);

    boolean updateStudies(int id, Studies studies);
}
