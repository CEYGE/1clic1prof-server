package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.user.attributes.Role;
import fr.clic1prof.serverapp.security.jwt.token.TokenProvider;
import fr.clic1prof.serverapp.service.profile.StudentProfileService;
import fr.clic1prof.serverapp.service.profile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@Secured(Role.Names.STUDENT)
public class StudentProfileController extends UserProfileController {

    @Autowired
    public StudentProfileController(@Qualifier("StudentProfileService") UserProfileService service, TokenProvider provider) {
        super(service, provider);
    }

    @Override
    public StudentProfileService getService() {
        return (StudentProfileService) super.getService();
    }
}
