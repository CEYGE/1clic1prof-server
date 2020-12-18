package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.service.profile.UserProfileService;

public abstract class UserProfileController {

    private final UserProfileService service;

    public UserProfileController(UserProfileService service) {
        this.service = service;
    }

    public UserProfileService getService() {
        return this.service;
    }
}
