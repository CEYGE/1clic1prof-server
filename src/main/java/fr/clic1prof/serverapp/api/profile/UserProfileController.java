package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.file.model.FileStored;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.service.profile.IUserProfileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

public abstract class UserProfileController implements IUserProfileController {

    private IUserProfileService service;

    public UserProfileController(IUserProfileService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> getProfile(UserBase base) {

        Profile profile = this.service.getProfile(base);

        return profile == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(profile);
    }

    @Override
    public ResponseEntity<?> getPicture(UserBase base) {

        FileStored picture = this.service.getPicture(base);

        ContentDisposition disposition = ContentDisposition.builder("attachment")
                .filename(picture.getName())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(disposition);
        headers.setContentType(picture.getType());

        return ResponseEntity.ok().headers(headers).body(picture.getResource());
    }

    @Override
    public ResponseEntity<?> updatePassword(UserBase user, @Valid @RequestBody PasswordModifier modifier) {

        boolean updated = this.getService().updatePassword(user, modifier);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public ResponseEntity<?> updateFirstName(UserBase user, @Valid @RequestBody Name name) {

        boolean updated = this.getService().updateFirstName(user, name);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public ResponseEntity<?> updateLastName(UserBase user, @Valid @RequestBody Name name) {

        boolean updated = this.getService().updateLastName(user, name);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public ResponseEntity<?> updatePicture(UserBase base, MultipartFile file) {

        boolean updated = this.service.updatePicture(base, file);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public ResponseEntity<?> deletePicture(UserBase base) {

        boolean updated = this.service.deletePicture(base);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    public IUserProfileService getService() {
        return this.service;
    }
}
