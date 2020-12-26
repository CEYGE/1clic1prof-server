package fr.clic1prof.serverapp.api.agenda;

import fr.clic1prof.serverapp.model.agenda.Event;
import fr.clic1prof.serverapp.model.agenda.TeacherEvent;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface TeacherAgendaController {

    @PostMapping("/agenda/event")
    ResponseEntity<Event> createEvent(UserBase base, @RequestBody TeacherEvent event);

    @PutMapping("/agenda/event/{id}")
    ResponseEntity<Event> updateEvent(UserBase base, @RequestBody TeacherEvent event, @RequestParam("id") int id);

    @DeleteMapping("/agenda/event/{id}")
    ResponseEntity<Void> deleteEvent(UserBase base, @RequestParam("id") int id);
}
