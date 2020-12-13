package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.user.Email;
import fr.clic1prof.serverapp.model.user.Name;
import fr.clic1prof.serverapp.model.user.Password;

import java.nio.file.Path;

public interface UserProfileDAO {

    boolean updatePassword(Email email, Password password);

    boolean updateFirstName(Email email, Name name);

    boolean updateLastName(Email email, Name name);

    boolean updatePicture(Email email, Path picture);

    boolean updateNotificationState(Email email, boolean flag);
}
