package fr.clic1prof.serverapp.service.contacts;

import fr.clic1prof.serverapp.dao.contacts.ContactDAO;
import fr.clic1prof.serverapp.model.contacts.ContactModel;
import fr.clic1prof.serverapp.model.user.UserBase;

import java.util.Collection;
import java.util.List;

public abstract class UserContactService implements ContactService {

    private ContactDAO dao;

    public UserContactService(ContactDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<ContactModel> getContacts(int userId) {
        return this.dao.getContacts(userId);
    }

    public ContactDAO getContactDAO() {
        return this.dao;
    }
}
