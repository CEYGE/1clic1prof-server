package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.file.Picture;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IUserProfileController {

    @PutMapping("/password")
    ResponseEntity<?> updatePassword(UserBase user, @Valid @RequestBody PasswordModifier modifier);

    @PutMapping("/first-name")
    ResponseEntity<?> updateFirstName(UserBase user, @Valid @RequestBody Name name);

    @PutMapping("/last-name")
    ResponseEntity<?> updateLastName(UserBase user, @Valid @RequestBody Name name);

    @PutMapping("/picture")
    ResponseEntity<?> updatePicture(UserBase base, @RequestBody Picture picture);
}
