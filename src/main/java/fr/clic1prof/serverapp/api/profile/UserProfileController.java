package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.user.Email;
import fr.clic1prof.serverapp.model.user.Name;
import fr.clic1prof.serverapp.model.user.Password;
import fr.clic1prof.serverapp.security.jwt.token.Token;
import fr.clic1prof.serverapp.security.jwt.token.TokenProvider;
import fr.clic1prof.serverapp.service.UserProfileService;
import fr.clic1prof.serverapp.util.HttpRequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.function.Supplier;

@RestController
public class UserProfileController {

    @Autowired
    private UserProfileService service;

    @Autowired
    private TokenProvider provider;

    @PutMapping("/update/password")
    public ResponseEntity<?> updatePassword(@RequestHeader("Authorization") String authorization,
                                            @Valid @RequestBody Password password, BindingResult result) {

        Token jwt = this.provider.parseToken(authorization).get();
        Email email = new Email(jwt.getUsername());

        Supplier<Boolean> supplier = () -> this.service.updatePassword(email, password);

        return HttpRequestProcessor.update(result, supplier);
    }

    @PutMapping("/update/first-name")
    public ResponseEntity<?> updateFirstName(@RequestHeader("Authorization") String authorization,
                                             @Valid @RequestBody Name name, BindingResult result) {

        Token jwt = this.provider.parseToken(authorization).get();
        Email email = new Email(jwt.getUsername());

        Supplier<Boolean> supplier = () -> this.service.updateFirstName(email, name);

        return HttpRequestProcessor.update(result, supplier);
    }

    @PutMapping("/update/last-name")
    public ResponseEntity<?> updateLastName(@RequestHeader("Authorization") String authorization,
                                            @Valid @RequestBody Name name, BindingResult result) {

        Token jwt = this.provider.parseToken(authorization).get();
        Email email = new Email(jwt.getUsername());

        Supplier<Boolean> supplier = () -> this.service.updateLastName(email, name);

        return HttpRequestProcessor.update(result, supplier);
    }
}
