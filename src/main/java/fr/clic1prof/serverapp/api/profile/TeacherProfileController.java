package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.profile.Studies;
import fr.clic1prof.serverapp.model.profile.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface TeacherProfileController extends UserProfileController {

    @PutMapping("/description")
    ResponseEntity<Void> updateDescription(UserBase user, @Valid @RequestBody Description description);

    @PutMapping("/speciality")
    ResponseEntity<Void> updateSpeciality(UserBase user, @Valid @RequestBody SpecialityModifier modifier);

    @PutMapping("/studies")
    ResponseEntity<Void> updateStudies(UserBase user, @Valid @RequestBody Studies studies);
}
