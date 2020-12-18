package fr.clic1prof.serverapp.security.jwt;

import fr.clic1prof.serverapp.security.jwt.token.Token;
import fr.clic1prof.serverapp.security.jwt.token.TokenProvider;
import fr.clic1prof.serverapp.service.JwtUserDetailsService;
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

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null) {
            chain.doFilter(request, response); // My job is done. Let's continue to filter.
            return;
        }

        Optional<Token> optional = this.provider.parseToken(authorization);

        if(!optional.isPresent()) {
            chain.doFilter(request, response); // My job is done. Let's continue to filter.
            return;
        }
        Token token = optional.get();

        if(SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails details = this.service.loadUserByUsername(token.getUsername());

            if(token.isValid(details)) {

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
                authentication.setDetails(details);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response); // My job is done. Let's continue to filter.
    }
}

