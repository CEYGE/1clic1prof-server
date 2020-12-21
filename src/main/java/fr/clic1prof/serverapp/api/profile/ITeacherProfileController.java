package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.profile.Studies;
import fr.clic1prof.serverapp.model.profile.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ITeacherProfileController extends IUserProfileController {

    @PutMapping("/description")
    ResponseEntity<?> updateDescription(UserBase user, @Valid @RequestBody Description description);

    @PutMapping("/speciality")
    ResponseEntity<?> updateSpeciality(UserBase user, @Valid @RequestBody SpecialityModifier modifier);

    @PutMapping("/studies")
    ResponseEntity<?> updateStudies(UserBase user, @Valid @RequestBody Studies studies);
}
