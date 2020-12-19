package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.attributes.Name;
import fr.clic1prof.serverapp.model.user.attributes.Password;
import fr.clic1prof.serverapp.model.user.attributes.Role;
import fr.clic1prof.serverapp.service.profile.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/student/profile")
@Secured(Role.Names.STUDENT)
public class StudentProfileController extends UserProfileController implements IStudentProfileController {

    @Autowired
    public StudentProfileController(@Qualifier("StudentProfileService") IUserProfileService service) {
        super(service);
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
    public ResponseEntity<?> updatePassword(UserBase user, @Valid Password password) {
        return super.updatePassword(user, password);
    }

    @Override
    public IUserProfileService getService() {
        return super.getService();
    }
}
