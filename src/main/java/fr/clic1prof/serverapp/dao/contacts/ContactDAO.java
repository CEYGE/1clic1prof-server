package fr.clic1prof.serverapp.dao.contacts;

import fr.clic1prof.serverapp.model.contacts.ContactModel;

import java.util.Collection;
import java.util.List;

public interface ContactDAO {

    List<ContactModel> getContacts(int id);
}
