package fr.clic1prof.serverapp.model.registration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.clic1prof.serverapp.model.user.attributes.Email;
import fr.clic1prof.serverapp.model.user.attributes.Name;
import fr.clic1prof.serverapp.model.user.attributes.Password;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@JsonDeserialize(using = Registration.RegistrationDeserializer.class)
public class Registration {

    @Valid @NotNull
    private Name firstName, lastName;

    @Valid @NotNull
    private Email email;

    @Valid @NotNull
    private Password password;

    @NotNull
    private RegistrationType type;

    private String encodedPassword;

    public Name getFirstName() {
        return this.firstName;
    }

    public Name getLastName() {
        return this.lastName;
    }

    public Email getEmail() {
        return this.email;
    }

    public Password getPassword() {
        return this.password;
    }

    public RegistrationType getType() {
        return this.type;
    }

    public String getEncodedPassword() {
        return this.encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public static class RegistrationDeserializer extends JsonDeserializer<Registration> {

        private static final String[] FIELDS = {"firstName", "lastName", "email", "password", "type"};

        @Override
        public Registration deserialize(JsonParser parser, DeserializationContext context) throws IOException {

            ObjectCodec codec = parser.getCodec();
            JsonNode node = codec.readTree(parser);

            Optional<String> missing = this.isJsonValid(node);

            if(missing.isPresent())
                throw new JsonParseException(parser, "Missing field" + missing.get());

            Registration registration = new Registration();

            registration.firstName = new Name(node.get("firstName").asText());
            registration.lastName = new Name(node.get("lastName").asText());
            registration.email = new Email(node.get("email").asText());
            registration.password = new Password(node.get("password").asText());

            Optional<RegistrationType> optional = RegistrationType.getByName(node.get("type").asText());

            if(!optional.isPresent())
                throw new JsonParseException(parser, "Invalid registration type.");

            registration.type = optional.get();

            return registration;
        }

        private Optional<String> isJsonValid(JsonNode node) {
            return Arrays.stream(FIELDS).filter(field -> !node.has(field)).findFirst();
        }
    }
}
