package fr.clic1prof.serverapp.service.agenda;

import fr.clic1prof.serverapp.model.agenda.Event;
import fr.clic1prof.serverapp.model.user.UserBase;

import java.util.List;

public class TeacherAgendaService extends AgendaService implements ITeacherAgendaService {

    @Override
    public boolean createEvent(UserBase base, Event event) {
        return false;
    }

    @Override
    public boolean updateEvent(UserBase base, Event event, int id) {
        return false;
    }

    @Override
    public boolean deleteEvent(UserBase base, int id) {
        return false;
    }

    @Override
    public List<Event> getEvents(int month, int year) {
        return null;
    }
}
