package fr.clic1prof.serverapp.api.agenda;

import fr.clic1prof.serverapp.model.agenda.Event;
import fr.clic1prof.serverapp.model.user.UserBase;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AgendaController {

    @GetMapping("/agenda")
    ResponseEntity<List<Event>> getAgenda(UserBase user,
                                          @Range(min = 1, max = 12) @RequestParam("month") int month,
                                          @Range(min = 2020) @RequestParam("year") int year);
}
