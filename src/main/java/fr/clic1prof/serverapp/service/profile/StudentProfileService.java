package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.profile.IStudentProfileDAO;
import fr.clic1prof.serverapp.dao.profile.IUserProfileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("StudentProfileService")
public class StudentProfileService extends UserProfileService implements IStudentProfileService {

    @Autowired
    public StudentProfileService(@Qualifier("StudentProfileDAO") IUserProfileDAO dao) {
        super(dao);
    }

    @Override
    public IStudentProfileDAO getUserDAO() {
        return (IStudentProfileDAO) super.getUserDAO();
    }
}
