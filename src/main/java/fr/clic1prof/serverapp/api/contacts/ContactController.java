package fr.clic1prof.serverapp.api.contacts;

import fr.clic1prof.serverapp.model.contacts.ContactModel;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ContactController {

    @GetMapping("/contacts")
    ResponseEntity<List<ContactModel>> getContacts(UserBase base);
}
