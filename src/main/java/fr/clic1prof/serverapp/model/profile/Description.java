package fr.clic1prof.serverapp.model.profile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Description {

    @NotNull
    @Size(max = 256)
    @JsonProperty("description")
    private String description;

    @JsonCreator // Used by Jackson for deserialization.
    private Description() {}

    public Description(String name) {
        this.description = name;
    }

    public String getValue() {
        return this.description;
    }
}
