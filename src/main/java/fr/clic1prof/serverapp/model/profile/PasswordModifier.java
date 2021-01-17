package fr.clic1prof.serverapp.model.profile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@JsonDeserialize(using = PasswordModifier.PasswordModifierDeserializer.class)
public class PasswordModifier {

    @NotNull @Valid
    private Password oldPassword;

    @NotNull @Valid
    private Password newPassword;

    public PasswordModifier(Password oldPassword, Password newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public Password getOldPassword() {
        return this.oldPassword;
    }

    public Password getNewPassword() {
        return this.newPassword;
    }

    public static class PasswordModifierDeserializer extends JsonDeserializer<PasswordModifier> {

        @Override
        public PasswordModifier deserialize(JsonParser parser, DeserializationContext context) throws IOException {

            ObjectCodec codec = parser.getCodec();
            JsonNode node = codec.readTree(parser);

            if(!node.has("oldPassword") || !node.has("newPassword"))
                throw new JsonParseException(parser, "Missing field.");

            Password oldPassword = new Password(node.get("oldPassword").asText());
            Password newPassword = new Password(node.get("newPassword").asText());

            return new PasswordModifier(oldPassword, newPassword);
        }
    }
}
