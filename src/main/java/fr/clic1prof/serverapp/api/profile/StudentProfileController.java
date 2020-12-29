package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.model.profile.SchoolLevelIdMapper;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface StudentProfileController extends UserProfileController {

    @PutMapping("/school-level")
    ResponseEntity<Void> updateSchoolLevel(UserBase base, @RequestBody SchoolLevelIdMapper schoolLevelId);
}
