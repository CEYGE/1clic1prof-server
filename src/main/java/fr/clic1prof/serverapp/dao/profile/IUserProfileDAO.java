package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.user.attributes.Name;
import fr.clic1prof.serverapp.model.user.attributes.Password;

import java.nio.file.Path;

public interface IUserProfileDAO {

    boolean updatePassword(int id, String password);

    boolean updateFirstName(int id, String firstName);

    boolean updateLastName(int id, String lastName);

    // boolean updatePicture(int id, Path picture);

    String getPassword(int id);
}
