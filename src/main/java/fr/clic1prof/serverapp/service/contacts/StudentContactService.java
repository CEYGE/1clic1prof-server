package fr.clic1prof.serverapp.service.contacts;

import fr.clic1prof.serverapp.dao.contacts.ContactDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("StudentContactService")
public class StudentContactService extends UserContactService {

    @Autowired
    public StudentContactService(@Qualifier("StudentContactDAO") ContactDAO dao) {
        super(dao);
    }
}
