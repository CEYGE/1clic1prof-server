package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.profile.Name;

public interface IUserProfileService {

    boolean updatePassword(UserBase user, PasswordModifier modifier);

    boolean updateFirstName(UserBase user, Name firstName);

    boolean updateLastName(UserBase user, Name lastName);
}
