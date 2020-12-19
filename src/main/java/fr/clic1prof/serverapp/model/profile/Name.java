package fr.clic1prof.serverapp.model.profile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Name {

    @NotNull @NotBlank
    @Size(min = 2, max = 64)
    @JsonProperty("name")
    private String name;

    @JsonCreator // Default constructor used by Jackson.
    private Name() {}

    public Name(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }
}
