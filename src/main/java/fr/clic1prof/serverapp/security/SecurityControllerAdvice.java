package fr.clic1prof.serverapp.security;

import fr.clic1prof.serverapp.model.ServerUserDetails;
import fr.clic1prof.serverapp.model.user.SimpleUserBase;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SecurityControllerAdvice {

    @ModelAttribute
    public UserBase provideUser(Authentication authentication) {

        if(authentication == null) return null;

        Object object = authentication.getDetails();

        if(!(object instanceof ServerUserDetails)) return null;

        ServerUserDetails details = (ServerUserDetails) authentication.getDetails();

        return new SimpleUserBase(details.getId());
    }
}
