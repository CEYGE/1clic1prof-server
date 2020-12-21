package fr.clic1prof.serverapp.dao.contacts;

import fr.clic1prof.serverapp.model.contacts.ContactModel;

import java.util.Collection;

public interface ContactDAO {

    Collection<ContactModel> getContacts(int id);
}
