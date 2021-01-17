package fr.clic1prof.serverapp.service.agenda;

import fr.clic1prof.serverapp.model.agenda.Event;

import java.util.List;

public interface AgendaService {

    List<Event> getEvents(int month, int year);
}
