package fr.clic1prof.serverapp.api.profile;

import fr.clic1prof.serverapp.security.jwt.token.TokenProvider;
import fr.clic1prof.serverapp.service.profile.UserProfileService;

public abstract class UserProfileController {

    private final UserProfileService service;
    private final TokenProvider provider;

    public UserProfileController(UserProfileService service, TokenProvider provider) {
        this.service = service;
        this.provider = provider;
    }

    public UserProfileService getService() {
        return this.service;
    }

    public TokenProvider getProvider() {
        return this.provider;
    }
}
