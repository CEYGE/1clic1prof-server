package fr.clic1prof.serverapp.model.agenda;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class TeacherEvent extends Event {

    @NotEmpty // At least one student concerned by the event.
    @JsonProperty("students")
    private List<Integer> students;

    public TeacherEvent(String title, String description, LocalDate start, LocalDate end, List<EventReminder> reminders, List<Integer> students) {
        super(title, description, start, end, reminders);
        this.students = students;
    }

    public List<Integer> getStudents() {
        return Collections.unmodifiableList(this.students);
    }
}
