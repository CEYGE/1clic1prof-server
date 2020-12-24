package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.profile.IUserProfileDAO;
import fr.clic1prof.serverapp.file.exceptions.FileStorageException;
import fr.clic1prof.serverapp.file.storage.FileStorage;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public abstract class UserProfileService implements IUserProfileService {

    private IUserProfileDAO dao;
    private PasswordEncoder encoder;

    @Autowired
    @Qualifier("ProfileFileStorage")
    private FileStorage storage;

    @Autowired
    public UserProfileService(IUserProfileDAO dao, PasswordEncoder encoder) {
        this.dao = dao;
        this.encoder = encoder;
    }

    @Override
    public boolean updatePassword(UserBase user, PasswordModifier modifier) {

        String encodedOldPassword = this.dao.getPassword(user.getId());
        String rawOldPassword = modifier.getOldPassword().getPassword();

        // Passwords are different.
        if(!this.encoder.matches(rawOldPassword, encodedOldPassword)) return false;

        String newPassword = modifier.getNewPassword().getPassword();
        String encodedNewPassword = this.encoder.encode(newPassword);

        return this.getUserDAO().updatePassword(user.getId(), encodedNewPassword);
    }

    @Override
    public boolean updateFirstName(UserBase user, Name firstName) {
        return this.getUserDAO().updateFirstName(user.getId(), firstName.getName());
    }

    @Override
    public boolean updateLastName(UserBase user, Name lastName) {
        return this.getUserDAO().updateLastName(user.getId(), lastName.getName());
    }

    @Override
    public boolean updatePicture(UserBase base, MultipartFile file) {

        if(!this.storage.isSupported(file)) return false;

        String id = null;

        try { id = this.storage.store(file);
        } catch (IOException e) { e.printStackTrace(); }

        if(id == null) return false;

        return this.dao.updatePicture(base.getId(), id);
    }

    @Override
    public boolean deletePicture(UserBase base) {

        String id = this.dao.deletePicture(base.getId());

        if(id == null) return false;

        boolean updated = true;

        try { this.storage.delete(id);
        } catch (IOException e) { updated = false; e.printStackTrace(); }

        return updated;
    }

    @Override
    public Profile getProfile(UserBase base) {

        Profile profile = this.dao.getProfile(base.getId());

        return this.dao.getProfile(base.getId());
    }

    public IUserProfileDAO getUserDAO() {
        return this.dao;
    }
}
