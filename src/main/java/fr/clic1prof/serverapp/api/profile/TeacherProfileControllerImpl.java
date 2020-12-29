package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.profile.Description;
import fr.clic1prof.serverapp.model.profile.Name;
import fr.clic1prof.serverapp.model.profile.PasswordModifier;
import fr.clic1prof.serverapp.model.profile.Studies;
import fr.clic1prof.serverapp.model.profile.SpecialityModifier;
import fr.clic1prof.serverapp.model.profile.model.Profile;
import fr.clic1prof.serverapp.model.user.UserBase;
import fr.clic1prof.serverapp.model.user.UserRole;
import fr.clic1prof.serverapp.service.profile.TeacherProfileService;
import fr.clic1prof.serverapp.service.profile.UserProfileService;
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
@RequestMapping("/teacher/profile")
@Secured(UserRole.Names.TEACHER)
public class TeacherProfileControllerImpl extends UserProfileControllerImpl implements TeacherProfileController {

    @Autowired
    public TeacherProfileControllerImpl(@Qualifier("TeacherProfileServiceImpl") UserProfileService service) {
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
    public ResponseEntity<Void> updateLastName(UserBase user, @Valid Name name) {
        return super.updateLastName(user, name);
    }

    @Override
    public ResponseEntity<Void> updateFirstName(UserBase user, @Valid Name name) {
        return super.updateFirstName(user, name);
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
    public ResponseEntity<Void> updateDescription(UserBase user, @Valid Description description) {

        boolean updated = this.getService().updateDescription(user, description);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public ResponseEntity<Void> updateSpeciality(UserBase user, @Valid SpecialityModifier modifier) {

        boolean updated = this.getService().updateSpeciality(user, modifier);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public ResponseEntity<Void> updateStudies(UserBase user, @Valid Studies studies) {

        boolean updated = this.getService().updateStudies(user, studies);

        HttpStatus status = updated ? HttpStatus.NO_CONTENT : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).build();
    }

    @Override
    public TeacherProfileService getService() {
        return (TeacherProfileService) super.getService();
    }
}
