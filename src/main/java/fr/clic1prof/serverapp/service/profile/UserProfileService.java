package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.profile.IUserProfileDAO;
import fr.clic1prof.serverapp.file.dao.storage.FileStorage;
import fr.clic1prof.serverapp.file.exceptions.FileStorageException;
import fr.clic1prof.serverapp.model.file.Picture;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public abstract class UserProfileService implements IUserProfileService {

    private IUserProfileDAO dao;
    private PasswordEncoder encoder;

    @Autowired
    @Qualifier("ProfileStorage")
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
        return this.getUserDAO().updateFirstName(user.getId(), firstName.getValue());
    }

    @Override
    public boolean updateLastName(UserBase user, Name lastName) {
        return this.getUserDAO().updateLastName(user.getId(), lastName.getValue());
    }

    @Override
    public boolean updatePicture(UserBase base, MultipartFile file) throws IOException {

        if(!this.storage.isSupported(file))
            throw new FileStorageException("File not supported.");

        UUID uuid = this.storage.store(file);

        return this.dao.updatePicture(base.getId(), uuid);
    }

    @Override
    public boolean deletePicture(UserBase base) throws IOException {

        UUID uuid = this.dao.deletePicture(base.getId());

        if(uuid == null) return false;

        this.storage.delete(uuid);

        return true;
    }

    @Override
    public Profile getProfile(UserBase base) {
        return this.dao.getProfile(base.getId());
    }

    public IUserProfileDAO getUserDAO() {
        return this.dao;
    }
}
