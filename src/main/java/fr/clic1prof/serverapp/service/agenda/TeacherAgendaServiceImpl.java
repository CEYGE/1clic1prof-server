package fr.clic1prof.serverapp.service.agenda;

import fr.clic1prof.serverapp.model.agenda.Event;
import fr.clic1prof.serverapp.model.user.UserBase;

import java.util.List;

public class TeacherAgendaServiceImpl extends AgendaServiceImpl implements TeacherAgendaService {

    @Override
    public boolean createEvent(int userId, Event event) {
        return false;
    }

    @Override
    public boolean updateEvent(int userId, Event event, int id) {
        return false;
    }

    @Override
    public boolean deleteEvent(int userId, int id) {
        return false;
    }

    @Override
    public List<Event> getEvents(int month, int year) {
        return null;
    }
}
