package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.other.StudentSchoolLevelDAO;
import fr.clic1prof.serverapp.dao.profile.StudentProfileDAO;
import fr.clic1prof.serverapp.dao.profile.UserProfileDAO;
import fr.clic1prof.serverapp.file.service.DocumentService;
import fr.clic1prof.serverapp.model.profile.SchoolLevelIdMapper;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("StudentProfileServiceImpl")
public class StudentProfileServiceImpl extends UserProfileServiceImpl implements StudentProfileService {

    @Autowired
    @Qualifier("StudentSchoolLevelDAOImpl")
    private StudentSchoolLevelDAO dao;

    @Autowired
    public StudentProfileServiceImpl(@Qualifier("StudentProfileDAOImpl") UserProfileDAO dao, PasswordEncoder encoder, DocumentService service) {
        super(dao, encoder, service);
    }

    @Override
    public boolean updateSchoolLevel(UserBase base, SchoolLevelIdMapper mapper) {

        if(!this.dao.exists(mapper.getId())) return false;

        return this.getUserDAO().updateSchoolLevel(base.getId(), mapper.getId());
    }

    @Override
    public StudentProfileDAO getUserDAO() {
        return (StudentProfileDAO) super.getUserDAO();
    }
}
