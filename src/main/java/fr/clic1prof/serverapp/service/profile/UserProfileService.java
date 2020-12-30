package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.file.exceptions.FileNotFoundException;
import fr.clic1prof.serverapp.file.model.FileStored;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserProfileService {

    boolean updatePassword(int userId, PasswordModifier modifier);

    boolean updateFirstName(int userId, Name firstName);

    boolean updateLastName(int userId, Name lastName);

    boolean updatePicture(int userId, MultipartFile file);

    void deletePicture(int userId);

    Profile getProfile(int userId);

    Optional<FileStored> getPicture(int userId) throws FileNotFoundException;
}
