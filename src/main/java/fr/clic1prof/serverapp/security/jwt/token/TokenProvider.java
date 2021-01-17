package fr.clic1prof.serverapp.security.jwt.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component // Let Spring have knowledge about this class.
public class TokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    public Token generateToken(UserDetails details) {
        return new Token(details.getUsername());
    }

    public Optional<Token> parseToken(String token) {

        String[] format = token.split(" ");

        if(format.length != 2 || !format[0].equals("Bearer")) return Optional.empty();

        Token jwt = new Token(format[1], this.secretKey);

        return Optional.of(jwt);
    }

    public String getToken(Token token) {
        return token.getToken(this.secretKey);
    }
}
