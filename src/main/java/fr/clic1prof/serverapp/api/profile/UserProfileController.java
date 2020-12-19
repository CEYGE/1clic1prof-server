package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.attributes.Name;
import fr.clic1prof.serverapp.model.user.attributes.Password;
import fr.clic1prof.serverapp.service.profile.IUserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public abstract class UserProfileController implements IUserProfileController {

    private IUserProfileService service;

    public UserProfileController(IUserProfileService service) {
        this.service = service;
    }

    @Override
    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(UserBase user, @Valid @RequestBody PasswordModifier modifier) {

        boolean updated = this.getService().updatePassword(user, modifier);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    @PutMapping("/first-name")
    public ResponseEntity<?> updateFirstName(UserBase user, @Valid @RequestBody Name name) {

        boolean updated = this.getService().updateFirstName(user, name);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    @PutMapping("/last-name")
    public ResponseEntity<?> updateLastName(UserBase user, @Valid @RequestBody Name name) {

        boolean updated = this.getService().updateLastName(user, name);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    public IUserProfileService getService() {
        return this.service;
    }
}
