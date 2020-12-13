package fr.clic1prof.serverapp.api;

import fr.clic1prof.serverapp.security.jwt.JwtRequest;
import fr.clic1prof.serverapp.security.jwt.JwtResponse;
import fr.clic1prof.serverapp.security.jwt.token.Token;
import fr.clic1prof.serverapp.security.jwt.token.TokenProvider;
import fr.clic1prof.serverapp.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtUserDetailsService service;

    @Autowired
    private TokenProvider provider;

    @PostMapping(value="/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest request) {

        try { this.authenticate(request);
        } catch (Exception e) { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); }

        // Retrieving user data from database.
        UserDetails details = this.service.loadUserByUsername(request.getUsername());

        // Generating token.
        Token jwt = this.provider.generateToken(details);
        String token = this.provider.getToken(jwt);

        // Sending the token to the client.
        return ResponseEntity.ok(new JwtResponse(token));
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
