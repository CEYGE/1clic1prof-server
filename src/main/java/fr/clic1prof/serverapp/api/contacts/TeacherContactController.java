package fr.clic1prof.serverapp.api.contacts;

import fr.clic1prof.serverapp.model.contacts.ContactModel;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.UserRole;
import fr.clic1prof.serverapp.service.contacts.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@Secured(UserRole.Names.TEACHER)
public class TeacherContactController extends UserContactController {

    @Autowired
    public TeacherContactController(@Qualifier("TeacherContactService") ContactService service) {
        super(service);
    }

    @Override
    public ResponseEntity<List<ContactModel>> getContacts(UserBase base) {
        return super.getContacts(base);
    }
}
