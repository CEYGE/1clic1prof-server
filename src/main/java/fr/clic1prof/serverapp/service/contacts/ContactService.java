package fr.clic1prof.serverapp.service.contacts;

import fr.clic1prof.serverapp.model.contacts.ContactModel;
import fr.clic1prof.serverapp.model.user.UserBase;

import java.util.Collection;

public interface ContactService {

    Collection<ContactModel> getContacts(UserBase base);
}
