package fr.clic1prof.serverapp.api;

import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.security.jwt.token.TokenProvider;
import fr.clic1prof.serverapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    @Autowired
    private UserService service;

    @Autowired
    private TokenProvider provider;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Registration registration, BindingResult result) {

        if(result.hasErrors()) // Request is understood but there are data errors.
            return ResponseEntity.unprocessableEntity().build();

        boolean registered = this.service.register(registration);

        if(!registered) // Unable to register the user.
            return ResponseEntity.unprocessableEntity().build();

        // Successful request but no data to return.
        return ResponseEntity.noContent().build();
    }
}
