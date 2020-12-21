package fr.clic1prof.serverapp.service.contacts;

import fr.clic1prof.serverapp.dao.contacts.ContactDAO;
import fr.clic1prof.serverapp.model.contacts.ContactModel;
import fr.clic1prof.serverapp.model.user.UserBase;

import java.util.Collection;

public abstract class UserContactService implements ContactService {

    private ContactDAO dao;

    public UserContactService(ContactDAO dao) {
        this.dao = dao;
    }

    @Override
    public Collection<ContactModel> getContacts(UserBase base) {
        return this.dao.getContacts(base.getId());
    }

    public ContactDAO getContactDAO() {
        return this.dao;
    }
}
