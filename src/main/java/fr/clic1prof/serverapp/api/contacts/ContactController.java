package fr.clic1prof.serverapp.api.contacts;

import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface ContactController {

    @GetMapping("/contacts")
    ResponseEntity<?> getContacts(UserBase base);
}
