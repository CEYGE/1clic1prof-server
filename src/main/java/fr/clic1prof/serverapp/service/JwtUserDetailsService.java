package fr.clic1prof.serverapp.service;

import fr.clic1prof.serverapp.dao.UserDAO;
import fr.clic1prof.serverapp.model.SimpleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired // Let Spring create an instance.
    @Qualifier("UserDAOImpl")
    private UserDAO dao;

    @Override // Spring will call automatically this method to load a user.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<SimpleUser> optional = this.dao.findByUsername(username);

        if(!optional.isPresent())
            throw new UsernameNotFoundException("User not found.");

        SimpleUser user = optional.get();

        List<GrantedAuthority> authorities = this.getAuthorities(user.getRole().name());

        // Return a user handled by Spring.
        // Using an empty list for permissions because the system does not use roles.
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
