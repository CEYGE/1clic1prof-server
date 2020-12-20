package fr.clic1prof.serverapp.security.jwt;

import fr.clic1prof.serverapp.security.jwt.token.Token;
import fr.clic1prof.serverapp.security.jwt.token.TokenProvider;
import fr.clic1prof.serverapp.service.JwtUserDetailsService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component // It filters only one time for each request.
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService service;

    @Autowired
    private TokenProvider provider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException, IllegalArgumentException {

        Optional<Token> optional = this.getToken(request);
        optional.ifPresent(this::authenticate);

        chain.doFilter(request, response); // My job is done. Let's continue to filter.
    }

    private Optional<Token> getToken(HttpServletRequest request) throws JwtException {

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Authorization header empty.
        if(authorization == null) return Optional.empty();

        Optional<Token> optional = Optional.empty();

        // Avoid exception when token is malformed or expired.
        // If there is a problem, the user will simply not authenticated.
        try { optional = this.provider.parseToken(authorization);
        } catch (JwtException ignored) {}

        return optional;
    }

    private void authenticate(Token token) {

        if(SecurityContextHolder.getContext().getAuthentication() != null) return;

        UserDetails details = this.service.loadUserByUsername(token.getUsername());

        if(token.isValid(details)) {

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
            authentication.setDetails(details);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}

