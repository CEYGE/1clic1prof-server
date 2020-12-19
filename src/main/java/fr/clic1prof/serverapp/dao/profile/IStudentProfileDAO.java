package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.profile.review.SchoolLevel;

public interface IStudentProfileDAO extends IUserProfileDAO {

    boolean updateSchoolLevel(int id, SchoolLevel schoolLevel);
}
