package fr.clic1prof.serverapp.model.profile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Size;

public class Studies {

    @Size(max = 32)
    @JsonProperty("studies")
    private String studies;

    @JsonCreator
    private Studies() {} // Default constructor used by Jackson.

    public Studies(String studies) {
        this.studies = studies;
    }

    public String getValue() {
        return this.studies;
    }
}
