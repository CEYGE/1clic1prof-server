package fr.clic1prof.serverapp.service.agenda;

import fr.clic1prof.serverapp.model.agenda.Event;

import java.util.List;

public interface IAgendaService {

    List<Event> getEvents(int month, int year);
}
