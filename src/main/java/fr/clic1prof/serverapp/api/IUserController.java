package fr.clic1prof.serverapp.api;

import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.security.jwt.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IUserController {

    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody JwtRequest request);

    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody Registration registration);
}
