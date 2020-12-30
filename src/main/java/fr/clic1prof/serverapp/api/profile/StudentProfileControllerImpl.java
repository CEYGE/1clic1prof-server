package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.profile.SchoolLevelIdMapper;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.UserRole;
import fr.clic1prof.serverapp.service.profile.UserProfileService;
import fr.clic1prof.serverapp.service.profile.StudentProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/student/profile")
@Secured(UserRole.Names.STUDENT)
public class StudentProfileControllerImpl extends UserProfileControllerImpl implements StudentProfileController {

    @Autowired
    public StudentProfileControllerImpl(@Qualifier("StudentProfileServiceImpl") UserProfileService service) {
        super(service);
    }

    @Override
    public ResponseEntity<Profile> getProfile(UserBase base) {
        return super.getProfile(base);
    }

    @Override
    public ResponseEntity<Void> updatePassword(UserBase user, @Valid PasswordModifier modifier) {
        return super.updatePassword(user, modifier);
    }

    @Override
    public ResponseEntity<Void> updateFirstName(UserBase user, @Valid Name name) {
        return super.updateFirstName(user, name);
    }

    @Override
    public ResponseEntity<Void> updateLastName(UserBase user, @Valid Name name) {
        return super.updateLastName(user, name);
    }

    @Override
    public ResponseEntity<Void> updatePicture(UserBase base, MultipartFile file) {
        return super.updatePicture(base, file);
    }

    @Override
    public ResponseEntity<Void> deletePicture(UserBase base) {
        return super.deletePicture(base);
    }

    @Override
    public ResponseEntity<Void> updateSchoolLevel(UserBase base, SchoolLevelIdMapper mapper) {

        boolean updated = this.getService().updateSchoolLevel(base.getId(), mapper);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public StudentProfileServiceImpl getService() {
        return (StudentProfileServiceImpl) super.getService();
    }
}
