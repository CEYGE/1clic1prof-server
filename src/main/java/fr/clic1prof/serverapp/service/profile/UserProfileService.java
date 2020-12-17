package fr.clic1prof.serverapp.service.profile;

import fr.clic1prof.serverapp.dao.profile.UserProfileDAO;

public abstract class UserProfileService {

    private final UserProfileDAO dao;

    public UserProfileService(UserProfileDAO dao) {
        this.dao = dao;
    }

    public UserProfileDAO getDao() {
        return this.dao;
    }
}
