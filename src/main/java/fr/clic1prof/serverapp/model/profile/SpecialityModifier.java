package fr.clic1prof.serverapp.model.profile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SpecialityModifier {

    @Range(min = 0)
    @JsonProperty("toReplace")
    private int toReplace;

    @Range(min = 0)
    @JsonProperty("replaceWith")
    private int replaceWith;

    @JsonCreator
    public SpecialityModifier(int toReplace, int replaceWith) {
        this.toReplace = toReplace;
        this.replaceWith = replaceWith;
    }

    public int getToReplace() {
        return this.toReplace;
    }

    public int getReplaceWith() {
        return this.replaceWith;
    }
}
