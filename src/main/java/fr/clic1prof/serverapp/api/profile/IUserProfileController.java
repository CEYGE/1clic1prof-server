package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.profile.Name;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

public interface IUserProfileController {

    @GetMapping("")
    ResponseEntity<?> getProfile(UserBase base);

    @PutMapping("/password")
    ResponseEntity<?> updatePassword(UserBase user, @Valid @RequestBody PasswordModifier modifier);

    @PutMapping("/first-name")
    ResponseEntity<?> updateFirstName(UserBase user, @Valid @RequestBody Name name);

    @PutMapping("/last-name")
    ResponseEntity<?> updateLastName(UserBase user, @Valid @RequestBody Name name);

    @PutMapping("/picture")
    ResponseEntity<?> updatePicture(UserBase base, @RequestPart("picture") MultipartFile file);

    @GetMapping("/picture")
    ResponseEntity<?> getPicture(UserBase base);

    @DeleteMapping("/picture")
    ResponseEntity<?> deletePicture(UserBase base);
}
