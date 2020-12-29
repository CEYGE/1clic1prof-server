package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.other.IStudentSchoolLevelDAO;
import fr.clic1prof.serverapp.dao.profile.IStudentProfileDAO;
import fr.clic1prof.serverapp.dao.profile.IUserProfileDAO;
import fr.clic1prof.serverapp.file.service.DocumentService;
import fr.clic1prof.serverapp.model.profile.SchoolLevelIdMapper;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("StudentProfileService")
public class StudentProfileService extends UserProfileService implements IStudentProfileService {

    @Autowired
    @Qualifier("StudentSchoolLevelDAO")
    private IStudentSchoolLevelDAO dao;

    @Autowired
    public StudentProfileService(@Qualifier("StudentProfileDAO") IUserProfileDAO dao, PasswordEncoder encoder, DocumentService service) {
        super(dao, encoder, service);
    }

    @Override
    public boolean updateSchoolLevel(UserBase base, SchoolLevelIdMapper mapper) {

        if(!this.dao.exists(mapper.getId())) return false;

        return this.getUserDAO().updateSchoolLevel(base.getId(), mapper.getId());
    }

    @Override
    public IStudentProfileDAO getUserDAO() {
        return (IStudentProfileDAO) super.getUserDAO();
    }
}
