package fr.clic1prof.serverapp.model.agenda;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Event {

    @NotNull @NotBlank
    @JsonProperty("title")
    private String title;

    @Length(max = 256)
    @JsonProperty("description")
    private String description;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("start")
    private LocalDate start;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("end")
    private LocalDate end;

    @JsonProperty("reminders")
    private List<EventReminder> reminders;

    @JsonCreator
    public Event(String title, String description, LocalDate start, LocalDate end, List<EventReminder> reminders) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
        this.reminders = reminders;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getStart() {
        return this.start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    public List<EventReminder> getReminders() {
        return Collections.unmodifiableList(this.reminders);
    }
}
