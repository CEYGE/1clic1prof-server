package fr.clic1prof.serverapp.api;

import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.security.jwt.JwtRequest;
import fr.clic1prof.serverapp.security.jwt.JwtResponse;
import fr.clic1prof.serverapp.security.jwt.token.Token;
import fr.clic1prof.serverapp.security.jwt.token.TokenProvider;
import fr.clic1prof.serverapp.service.JwtUserDetailsService;
import fr.clic1prof.serverapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController implements IUserController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtUserDetailsService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider provider;

    @Override
    public ResponseEntity<?> login(@Valid JwtRequest request) {

        try { this.authenticate(request);
        } catch (Exception e) { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); }

        // Retrieving user data from database.
        UserDetails details = this.jwtService.loadUserByUsername(request.getUsername());

        // Generating token.
        Token jwt = this.provider.generateToken(details);
        String token = this.provider.getToken(jwt);

        // Sending the token to the client.
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Override
    public ResponseEntity<?> register(@Valid Registration registration) {

        boolean registered = this.userService.register(registration);

        if(!registered) // Unable to register the user.
            return ResponseEntity.unprocessableEntity().build();

        // Successful request but no data to return.
        return ResponseEntity.noContent().build();
    }

    private void authenticate(JwtRequest request) throws Exception {

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
