package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.profile.IUserProfileDAO;
import fr.clic1prof.serverapp.file.model.Document;
import fr.clic1prof.serverapp.file.model.DocumentModel;
import fr.clic1prof.serverapp.file.model.DocumentType;
import fr.clic1prof.serverapp.file.model.FileStored;
import fr.clic1prof.serverapp.file.service.DocumentService;
import fr.clic1prof.serverapp.file.storage.FileStorageHandler;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

public abstract class UserProfileService implements IUserProfileService {

    private final IUserProfileDAO dao;
    private final PasswordEncoder encoder;
    private final DocumentService documentService;

    @Autowired
    public UserProfileService(IUserProfileDAO dao, PasswordEncoder encoder, DocumentService documentService) {
        this.dao = dao;
        this.encoder = encoder;
        this.documentService = documentService;
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

        DocumentType type = DocumentType.PROFILE_PICTURE;

        this.documentService.removeDocument(base.getId(), type);

        return this.documentService.addDocument(base.getId(), file,type);
    }

    @Override
    public boolean deletePicture(UserBase base) {
        return this.documentService.removeDocument(base.getId(), DocumentType.PROFILE_PICTURE);
    }

    @Override
    public FileStored getPicture(UserBase base) {

        DocumentModel document = this.documentService.getDocument(base.getId(), DocumentType.PROFILE_PICTURE);

        return this.documentService.getFileStored(document.getId());
    }

    @Override
    public Profile getProfile(UserBase base) {
        return this.dao.getProfile(base.getId());
    }

    public IUserProfileDAO getUserDAO() {
        return this.dao;
    }
}
