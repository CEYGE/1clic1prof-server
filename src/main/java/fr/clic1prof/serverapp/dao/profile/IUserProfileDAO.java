package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.file.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IUserProfileDAO {

    boolean updatePassword(int id, String password);

    boolean updateFirstName(int id, String firstName);

    boolean updateLastName(int id, String lastName);

    boolean updatePicture(int id, UUID uuid);

    UUID deletePicture(int id);

    String getPassword(int id);
}
