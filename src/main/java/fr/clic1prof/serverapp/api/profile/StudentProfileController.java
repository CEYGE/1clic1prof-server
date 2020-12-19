package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.user.UserRole;
import fr.clic1prof.serverapp.service.profile.IUserProfileService;
import fr.clic1prof.serverapp.model.file.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/student/profile")
@Secured(UserRole.Names.STUDENT)
public class StudentProfileController extends UserProfileController implements IStudentProfileController {

    @Autowired
    public StudentProfileController(@Qualifier("StudentProfileService") IUserProfileService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> updatePassword(UserBase user, @Valid PasswordModifier modifier) {
        return super.updatePassword(user, modifier);
    }

    @Override
    public ResponseEntity<?> updateFirstName(UserBase user, @Valid Name name) {
        return super.updateFirstName(user, name);
    }

    @Override
    public ResponseEntity<?> updateLastName(UserBase user, @Valid Name name) {
        return super.updateLastName(user, name);
    }

    @Override
    public ResponseEntity<?> updatePicture(UserBase base, Picture picture) {
        return super.updatePicture(base, picture);
    }

    @Override
    public IUserProfileService getService() {
        return super.getService();
    }
}
