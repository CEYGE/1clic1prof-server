package fr.clic1prof.serverapp.service;

import fr.clic1prof.serverapp.model.registration.Registration;
import fr.clic1prof.serverapp.model.user.attributes.Email;

public interface IUserService {

    boolean register(Registration registration);

    boolean isRegistered(Email email);
}
