package fr.clic1prof.serverapp.api.contacts;

import fr.clic1prof.serverapp.model.contacts.ContactModel;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.service.contacts.ContactService;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public class UserContactController implements ContactController {

    private ContactService service;

    public UserContactController(ContactService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> getContacts(UserBase base) {

        Collection<ContactModel> contacts = this.service.getContacts();

        if(contacts == null) return ResponseEntity.unprocessableEntity().build();

        return ResponseEntity.ok(contacts);
    }

    public ContactService getService() {
        return this.service;
    }
}
