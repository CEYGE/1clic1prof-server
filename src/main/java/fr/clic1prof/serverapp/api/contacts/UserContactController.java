package fr.clic1prof.serverapp.api.contacts;

import fr.clic1prof.serverapp.model.contacts.Contact;
import fr.clic1prof.serverapp.model.contacts.ContactModel;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.service.contacts.ContactService;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public class UserContactController implements ContactController {

    private ContactService service;

    public UserContactController(ContactService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<ContactModel>> getContacts(UserBase user) {

        List<ContactModel> contacts = this.service.getContacts(user.getId());

        if(contacts == null) return ResponseEntity.unprocessableEntity().build();

        return ResponseEntity.ok(contacts);
    }

    public ContactService getService() {
        return this.service;
    }
}
