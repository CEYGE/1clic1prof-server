package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUserProfileService {

    boolean updatePassword(UserBase user, PasswordModifier modifier);

    boolean updateFirstName(UserBase user, Name firstName);

    boolean updateLastName(UserBase user, Name lastName);

    boolean updatePicture(UserBase base, MultipartFile file) throws IOException;

    boolean deletePicture(UserBase base) throws IOException;
}
