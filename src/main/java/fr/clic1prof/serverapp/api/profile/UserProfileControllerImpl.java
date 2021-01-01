package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.file.exceptions.DocumentNotFoundException;
import fr.clic1prof.serverapp.file.exceptions.FileNotFoundException;
import fr.clic1prof.serverapp.file.model.FileStored;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.service.profile.UserProfileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public abstract class UserProfileControllerImpl implements UserProfileController {

    private final UserProfileService service;

    public UserProfileControllerImpl(UserProfileService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Profile> getProfile(UserBase base) {

        Profile profile = this.service.getProfile(base.getId());

        return profile == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(profile);
    }

    @Override
    public ResponseEntity<Resource> getPicture(UserBase user) {

        Optional<FileStored> optional;

        try { optional = this.service.getPicture(user.getId());
        } catch (FileNotFoundException e) { return ResponseEntity.unprocessableEntity().build(); }

        if(!optional.isPresent())
            throw new DocumentNotFoundException(String.format("No profile picture found for %d.", user.getId()));

        FileStored picture = optional.get();

        ContentDisposition disposition = ContentDisposition.builder("attachment")
                .filename(picture.getName())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(disposition);
        headers.setContentType(picture.getType());

        return ResponseEntity.ok().headers(headers).body(picture.getResource());
    }

    @Override
    public ResponseEntity<Void> updatePassword(UserBase user, PasswordModifier modifier) {

        boolean updated = this.getService().updatePassword(user.getId(), modifier);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public ResponseEntity<Void> updateFirstName(UserBase user, Name name) {

        boolean updated = this.getService().updateFirstName(user.getId(), name);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public ResponseEntity<Void> updateLastName(UserBase user, Name name) {

        boolean updated = this.getService().updateLastName(user.getId(), name);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public ResponseEntity<Void> updatePicture(UserBase user, MultipartFile file) {

        int updated = this.service.updatePicture(user.getId(), file);

        HttpStatus status = updated != 0 ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public ResponseEntity<Void> deletePicture(UserBase user) {

        this.service.deletePicture(user.getId());

        return ResponseEntity.noContent().build();
    }

    public UserProfileService getService() {
        return this.service;
    }
}
