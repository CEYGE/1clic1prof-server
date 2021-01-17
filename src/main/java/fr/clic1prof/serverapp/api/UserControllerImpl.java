package fr.clic1prof.serverapp.api;

import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.user.UserModel;
import fr.clic1prof.serverapp.security.jwt.authentication.AuthenticationRequest;
import fr.clic1prof.serverapp.security.jwt.authentication.AuthenticationResponse;
import fr.clic1prof.serverapp.security.jwt.token.Token;
import fr.clic1prof.serverapp.security.jwt.token.TokenProvider;
import fr.clic1prof.serverapp.service.UserService;
import fr.clic1prof.serverapp.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtUserDetailsService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider provider;

    @Override
    public ResponseEntity<AuthenticationResponse> login(@Valid AuthenticationRequest request) {

        try { this.authenticate(request);
        } catch (Exception e) { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); }

        // Retrieving user data from database.
        UserModel details = this.jwtService.loadUserByUsername(request.getUsername());

        // Generating token.
        Token jwt = this.provider.generateToken(details);
        String token = this.provider.getToken(jwt);

        AuthenticationResponse response = new AuthenticationResponse(token, details.getRole());

        // Sending the token to the client.
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> register(@Valid Registration registration) {

        boolean registered = this.userService.register(registration);

        if(!registered) // Unable to register the user.
            return ResponseEntity.unprocessableEntity().build();

        // Successful request but no data to return.
        return ResponseEntity.noContent().build();
    }

    private void authenticate(AuthenticationRequest request) throws Exception {

        String username = request.getUsername();
        String password = request.getPassword();

        // Creating authentication object.
        // DO NOT hash password here ! It will be automatically hashed by the manager during the comparison.
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        // Trying to authenticate the user.
        // The manager will compare the username and the password provided with the ones stored in database.
        this.manager.authenticate(authentication);
    }
}
