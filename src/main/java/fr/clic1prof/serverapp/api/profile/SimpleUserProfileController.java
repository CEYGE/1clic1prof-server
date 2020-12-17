package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.user.User;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.attributes.Name;
import fr.clic1prof.serverapp.model.user.attributes.Password;
import fr.clic1prof.serverapp.security.jwt.token.TokenProvider;
import fr.clic1prof.serverapp.service.profile.SimpleUserProfileService;
import fr.clic1prof.serverapp.service.profile.UserProfileService;
import fr.clic1prof.serverapp.util.HttpRequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.function.Supplier;

@RestController
@RequestMapping("/profile")
public class SimpleUserProfileController extends UserProfileController {

    @Autowired
    public SimpleUserProfileController(@Qualifier("SimpleUserProfileService") UserProfileService service, TokenProvider provider) {
        super(service, provider);
    }

    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(UserBase user, @Valid @RequestBody Password password, BindingResult result) {

        Supplier<Boolean> supplier = () -> this.getService().updatePassword(user, password);

        return HttpRequestProcessor.update(result, supplier);
    }

    @PutMapping("/first-name")
    public ResponseEntity<?> updateFirstName(UserBase user, @Valid @RequestBody Name name, BindingResult result) {

        Supplier<Boolean> supplier = () -> this.getService().updateFirstName(user, name);

        return HttpRequestProcessor.update(result, supplier);
    }

    @PutMapping("/last-name")
    public ResponseEntity<?> updateLastName(UserBase user, @Valid @RequestBody Name name, BindingResult result) {

        Supplier<Boolean> supplier = () -> this.getService().updateLastName(user, name);

        return HttpRequestProcessor.update(result, supplier);
    }

    @Override
    public SimpleUserProfileService getService() {
        return (SimpleUserProfileService) super.getService();
    }
}
