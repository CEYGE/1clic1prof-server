package fr.clic1prof.serverapp.service;

import fr.clic1prof.serverapp.dao.IUserDAO;
import fr.clic1prof.serverapp.model.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired // Let Spring create an instance.
    @Qualifier("UserDAO")
    private IUserDAO dao;

    @Override // Spring will call automatically this method to load a user.
    public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserModel> optional = this.dao.findByUsername(username);

        if(!optional.isPresent())
            throw new UsernameNotFoundException("User not found.");

        return optional.get();
    }
}
