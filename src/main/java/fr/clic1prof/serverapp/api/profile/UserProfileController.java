package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.service.profile.IUserProfileService;
import org.springframework.http.HttpStatus;
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

    // Récupérer plusieurs fichiers : @RequestParam("files") MultipartFile[] files
    // Json + Fichiers : @RequestPart("files") MultipartFile[] files, @RequestPart("name") Name name

    @Override
    public ResponseEntity<?> getProfile(UserBase base) {

        // TODO complete profile by adding picture in http request body.
        Profile profile = this.service.getProfile(base);

        if(profile == null)
            return ResponseEntity.unprocessableEntity().build();

        return ResponseEntity.ok(profile);
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

        boolean updated = false;

        try { updated = this.service.updatePicture(base, file);
        } catch (IOException ignored) {}

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public ResponseEntity<?> deletePicture(UserBase base) {

        boolean updated = false;

        try { updated = this.service.deletePicture(base);
        } catch (IOException ignored) {}

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    public IUserProfileService getService() {
        return this.service;
    }
}
