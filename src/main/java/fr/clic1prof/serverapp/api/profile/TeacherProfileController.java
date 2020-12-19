package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.modifier.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.attributes.Name;
import fr.clic1prof.serverapp.model.user.attributes.Password;
import fr.clic1prof.serverapp.model.user.attributes.Role;
import fr.clic1prof.serverapp.service.profile.ITeacherProfileService;
import fr.clic1prof.serverapp.service.profile.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/teacher/profile")
@Secured(Role.Names.TEACHER)
public class TeacherProfileController extends UserProfileController implements ITeacherProfileController {

    @Autowired
    public TeacherProfileController(@Qualifier("TeacherProfileService") IUserProfileService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> updateLastName(UserBase user, @Valid Name name) {
        return super.updateLastName(user, name);
    }

    @Override
    public ResponseEntity<?> updateFirstName(UserBase user, @Valid Name name) {
        return super.updateFirstName(user, name);
    }

    @Override
    public ResponseEntity<?> updatePassword(UserBase user, @Valid Password password) {
        return super.updatePassword(user, password);
    }

    @Override
    @PutMapping("/description")
    public ResponseEntity<?> updateDescription(UserBase user, @Valid Description description) {

        boolean updated = this.getService().updateDescription(user, description);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    @PutMapping("/speciality")
    public ResponseEntity<?> updateSpeciality(UserBase user, @Valid SpecialityModifier modifier) {

        boolean updated = this.getService().updateSpeciality(user, modifier);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public ITeacherProfileService getService() {
        return (ITeacherProfileService) super.getService();
    }
}
