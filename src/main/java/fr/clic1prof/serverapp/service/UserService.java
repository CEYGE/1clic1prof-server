package fr.clic1prof.serverapp.service;

import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.profile.Email;

public interface UserService {

    boolean register(Registration registration);

    boolean isRegistered(Email email);
}
