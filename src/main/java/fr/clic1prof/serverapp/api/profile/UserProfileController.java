package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.service.profile.IUserProfileService;
import fr.clic1prof.serverapp.model.file.Picture;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public abstract class UserProfileController implements IUserProfileController {

    private IUserProfileService service;

    public UserProfileController(IUserProfileService service) {
        this.service = service;
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
    public ResponseEntity<?> updatePicture(UserBase base, @Valid @RequestBody Picture picture) {
        return ResponseEntity.noContent().build();
    }

    public IUserProfileService getService() {
        return this.service;
    }
}
