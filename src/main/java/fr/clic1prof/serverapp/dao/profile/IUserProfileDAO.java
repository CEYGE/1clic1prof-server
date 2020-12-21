package fr.clic1prof.serverapp.dao.profile;

import fr.clic1prof.serverapp.model.profile.model.Profile;

import java.util.UUID;

public interface IUserProfileDAO {

    boolean updatePassword(int id, String password);

    boolean updateFirstName(int id, String firstName);

    boolean updateLastName(int id, String lastName);

    boolean updatePicture(int id, String pictureId);

    String deletePicture(int id);

    String getPassword(int id);

    Profile getProfile(int id);
}
