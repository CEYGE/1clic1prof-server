package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.user.attributes.Name;
import fr.clic1prof.serverapp.model.user.attributes.Password;

import java.nio.file.Path;

public interface IUserProfileDAO {

    boolean updatePassword(int id, Password password);

    boolean updateFirstName(int id, Name name);

    boolean updateLastName(int id, Name name);

    boolean updatePicture(int id, Path picture);

    boolean updateNotificationState(int id, boolean flag);
}
