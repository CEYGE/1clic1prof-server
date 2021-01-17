package fr.clic1prof.serverapp.model.profile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;

public class SchoolLevelIdMapper {

    @Range(min = 0) // Cannot have a negative value.
    @JsonProperty("id")
    private int id;

    @JsonCreator
    public SchoolLevelIdMapper(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
