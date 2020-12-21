package fr.clic1prof.serverapp.service.contacts;

import fr.clic1prof.serverapp.dao.contacts.ContactDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("TeacherContactService")
public class TeacherContactService extends UserContactService {

    @Autowired
    public TeacherContactService(@Qualifier("TeacherContactDAO") ContactDAO dao) {
        super(dao);
    }
}
