package fr.clic1prof.serverapp.dao.profile;

public interface IUserProfileDAO {

    boolean updatePassword(int id, String password);

    boolean updateFirstName(int id, String firstName);

    boolean updateLastName(int id, String lastName);

    // boolean updatePicture(int id, Picture picture);

    String getPassword(int id);
}
