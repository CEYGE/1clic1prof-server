package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.modifier.SpecialityModifier;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.attributes.Role;
import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.security.jwt.token.TokenProvider;
import fr.clic1prof.serverapp.service.profile.TeacherProfileService;
import fr.clic1prof.serverapp.service.profile.UserProfileService;
import fr.clic1prof.serverapp.util.HttpRequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.function.Supplier;

@RestController
@RequestMapping("/profile")
@Secured(Role.Names.TEACHER)
public class TeacherProfileController extends UserProfileController {

    @Autowired
    public TeacherProfileController(@Qualifier("TeacherProfileService") UserProfileService service, TokenProvider provider) {
        super(service, provider);
    }

    @PutMapping("/description")
    public ResponseEntity<?> updateDescription(UserBase user, @Valid Description description, BindingResult result) {

        Supplier<Boolean> supplier = () -> this.getService().updateDescription(user, description);

        return HttpRequestProcessor.update(result, supplier);
    }

    @PutMapping("/speciality")
    public ResponseEntity<?> updateSpeciality(UserBase user, @Valid SpecialityModifier modifier, BindingResult result) {

        Supplier<Boolean> supplier = () -> this.getService().updateSpeciality(user, modifier);

        return HttpRequestProcessor.update(result, supplier);
    }

    @Override
    public TeacherProfileService getService() {
        return (TeacherProfileService) super.getService();
    }
}
