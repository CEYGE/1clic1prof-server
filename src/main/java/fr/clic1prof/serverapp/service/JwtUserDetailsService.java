package fr.clic1prof.serverapp.service;

import fr.clic1prof.serverapp.dao.UserDAO;
import fr.clic1prof.serverapp.model.user.attributes.Role;
import fr.clic1prof.serverapp.model.ServerUserDetails;
import fr.clic1prof.serverapp.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired // Let Spring create an instance.
    @Qualifier("UserDAOImpl")
    private UserDAO dao;

    @Override // Spring will call automatically this method to load a user.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optional = this.dao.findByUsername(username);

        if(!optional.isPresent())
            throw new UsernameNotFoundException("User not found.");

        User user = optional.get();

        List<GrantedAuthority> authorities = this.getAuthorities(user.getRoles());

        // Return a user handled by Spring.
        // Using an empty list for permissions because the system does not use roles.
        return new ServerUserDetails.Builder(user.getId(), user.getEmail(), user.getPassword())
                .authorities(authorities)
                .build();
    }

    private List<GrantedAuthority> getAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
