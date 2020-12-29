package fr.clic1prof.serverapp.service.agenda;

import fr.clic1prof.serverapp.model.agenda.Event;
import fr.clic1prof.serverapp.model.user.UserBase;

public interface TeacherAgendaService extends AgendaService {

    boolean createEvent(UserBase base, Event event);

    boolean updateEvent(UserBase base, Event event, int id);

    boolean deleteEvent(UserBase base, int id);
}
