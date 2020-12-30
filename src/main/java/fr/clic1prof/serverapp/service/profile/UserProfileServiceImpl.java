package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.profile.UserProfileDAO;
import fr.clic1prof.serverapp.exceptions.PasswordModificationException;
import fr.clic1prof.serverapp.file.exceptions.FileNotFoundException;
import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.DocumentType;
import fr.clic1prof.serverapp.file.model.FileStored;
import fr.clic1prof.serverapp.file.service.DocumentService;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.profile.model.UserProfile;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public abstract class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileDAO dao;
    private final PasswordEncoder encoder;
    private final DocumentService documentService;

    @Autowired
    public UserProfileServiceImpl(UserProfileDAO dao, PasswordEncoder encoder, DocumentService documentService) {
        this.dao = dao;
        this.encoder = encoder;
        this.documentService = documentService;
    }

    @Override
    public boolean updatePassword(int userId, PasswordModifier modifier) {

        String encodedOldPassword = this.dao.getPassword(userId);
        String rawOldPassword = modifier.getOldPassword().getPassword();

        // Passwords are different.
        if(!this.encoder.matches(rawOldPassword, encodedOldPassword))
            throw new PasswordModificationException("The current password doesn't match with the provided password.");

        String newPassword = modifier.getNewPassword().getPassword();
        String encodedNewPassword = this.encoder.encode(newPassword);

        return this.getUserDAO().updatePassword(userId, encodedNewPassword);
    }

    @Override
    public boolean updateFirstName(int userId, Name firstName) {
        return this.getUserDAO().updateFirstName(userId, firstName.getName());
    }

    @Override
    public boolean updateLastName(int userId, Name lastName) {
        return this.getUserDAO().updateLastName(userId, lastName.getName());
    }

    @Override
    public boolean updatePicture(int userId, MultipartFile file) {

        DocumentType type = DocumentType.PROFILE_PICTURE;

        this.documentService.removeDocument(userId, type);

        return this.documentService.addDocument(userId, file, type);
    }

    @Override
    public void deletePicture(int userId) {
        this.documentService.removeDocument(userId, DocumentType.PROFILE_PICTURE);
    }

    @Override
    public Optional<FileStored> getPicture(int userId) throws FileNotFoundException {
        return this.documentService.getFileStored(userId, DocumentType.PROFILE_PICTURE);
    }

    @Override
    public Profile getProfile(int userId) {

        // If you read this code, please try to think to a better solution than mine.
        // Casts must be avoided in code when possible.
        UserProfile profile = (UserProfile) this.dao.getProfile(userId);

        Optional<DocumentModel> optional = this.documentService.getDocument(userId, DocumentType.PROFILE_PICTURE);
        optional.ifPresent(document -> profile.setPictureId(document.getId()));

        return this.dao.getProfile(userId);
    }

    public UserProfileDAO getUserDAO() {
        return this.dao;
    }
}
