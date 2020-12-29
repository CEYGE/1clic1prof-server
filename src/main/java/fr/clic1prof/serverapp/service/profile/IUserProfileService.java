package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.file.model.FileStored;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUserProfileService {

    boolean updatePassword(UserBase user, PasswordModifier modifier);

    boolean updateFirstName(UserBase user, Name firstName);

    boolean updateLastName(UserBase user, Name lastName);

    boolean updatePicture(UserBase base, MultipartFile file);

    boolean deletePicture(UserBase base);

    Profile getProfile(UserBase base);

    FileStored getPicture(UserBase base);
}
