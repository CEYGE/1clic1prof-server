package fr.clic1prof.serverapp.model.profile.review;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SpecialityModifier {

    @NotNull @NotBlank
    @JsonProperty("toReplace")
    private String toReplaceSpeciality;

    @NotNull @NotBlank
    @JsonProperty("replaceWith")
    private String replaceWithSpeciality;

    @JsonCreator // Used by Jackson for deserialization.
    private SpecialityModifier() {}

    public SpecialityModifier(String toReplaceSpeciality, String replaceWithSpeciality) {
        this.toReplaceSpeciality = toReplaceSpeciality;
        this.replaceWithSpeciality = replaceWithSpeciality;
    }

    public String getToReplaceSpeciality() {
        return this.toReplaceSpeciality;
    }

    public String getReplaceWithSpeciality() {
        return this.replaceWithSpeciality;
    }
}
