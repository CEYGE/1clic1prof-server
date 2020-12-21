package fr.clic1prof.serverapp.dao.profile;

public interface IStudentProfileDAO extends IUserProfileDAO {

    boolean updateSchoolLevel(int id, int schoolLevelId);
}
