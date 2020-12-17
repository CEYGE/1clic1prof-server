package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.profile.StudentProfileDAO;
import fr.clic1prof.serverapp.dao.profile.UserProfileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("StudentProfileService")
public class StudentProfileService extends UserProfileService {

    @Autowired
    public StudentProfileService(@Qualifier("StudentProfileDAOImpl") UserProfileDAO dao) {
        super(dao);
    }

    @Override
    public StudentProfileDAO getDao() {
        return (StudentProfileDAO) super.getDao();
    }
}
