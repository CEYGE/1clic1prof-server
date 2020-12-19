package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.modifier.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.profile.StudyLevel;

public interface ITeacherProfileDAO extends IUserProfileDAO {

    boolean updateDescription(int id, Description description);

    boolean updateSpeciality(int id, SpecialityModifier modifier);

    boolean updateStudyLevel(int id, StudyLevel studyLevel);
}
