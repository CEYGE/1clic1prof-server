package fr.clic1prof.serverapp.service.agenda;

import fr.clic1prof.serverapp.model.agenda.Event;
import fr.clic1prof.serverapp.model.user.UserBase;

public interface TeacherAgendaService extends AgendaService {

    boolean createEvent(int userId, Event event);

    boolean updateEvent(int userId, Event event, int id);

    boolean deleteEvent(int userId, int id);
}
