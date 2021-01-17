package fr.clic1prof.serverapp.dao.profile;

public interface StudentProfileDAO extends UserProfileDAO {

    boolean updateSchoolLevel(int id, int schoolLevelId);
}
