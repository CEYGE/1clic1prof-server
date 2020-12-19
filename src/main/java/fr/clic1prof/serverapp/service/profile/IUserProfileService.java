package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.attributes.Name;
import fr.clic1prof.serverapp.model.user.attributes.Password;

public interface IUserProfileService {

    boolean updatePassword(UserBase user, Password password);

    boolean updateFirstName(UserBase user, Name firstName);

    boolean updateLastName(UserBase user, Name lastName);
}
