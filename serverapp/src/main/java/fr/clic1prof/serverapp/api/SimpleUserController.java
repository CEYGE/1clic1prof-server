package fr.clic1prof.serverapp.api;

import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.user.Email;
import fr.clic1prof.serverapp.model.user.Name;
import fr.clic1prof.serverapp.model.user.Password;
import fr.clic1prof.serverapp.security.jwt.token.Token;
import fr.clic1prof.serverapp.security.jwt.token.TokenProvider;
import fr.clic1prof.serverapp.service.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SimpleUserController {

    @Autowired
    private SimpleUserService service;

    @Autowired
    private TokenProvider provider;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Registration registration, BindingResult result) {

        if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        boolean registered = this.service.register(registration);

        if(!registered) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/password")
    public ResponseEntity<?> updatePassword(@RequestHeader("Authorization") String authorization,
                                            @Valid @RequestBody Password password, BindingResult result) {

        Token jwt = this.provider.parseToken(authorization).get();
        Email email = new Email(jwt.getUsername());

        if(result.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        this.service.updatePassword(email, password);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/first-name")
    public ResponseEntity<?> updateFirstName(@RequestHeader("Authorization") String authorization,
                                             @Valid @RequestBody Name name, BindingResult result) {

        Token jwt = this.provider.parseToken(authorization).get();
        Email email = new Email(jwt.getUsername());

        if(result.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        this.service.updateLastName(email, name);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/last-name")
    public ResponseEntity<?> updateLastName(@RequestHeader("Authorization") String authorization,
                                            @Valid @RequestBody Name name, BindingResult result) {

        Token jwt = this.provider.parseToken(authorization).get();
        Email email = new Email(jwt.getUsername());

        if(result.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        this.service.updateLastName(email, name);

        return ResponseEntity.ok().build();
    }
}
