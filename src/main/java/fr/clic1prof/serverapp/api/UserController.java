package fr.clic1prof.serverapp.api;

import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.security.jwt.authentication.AuthenticationRequest;
import fr.clic1prof.serverapp.security.jwt.authentication.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface UserController {

    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request);

    @PostMapping("/register")
    ResponseEntity<Void> register(@Valid @RequestBody Registration registration);
}
