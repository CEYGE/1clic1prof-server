package fr.clic1prof.serverapp.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.util.Iterator;

@JsonSerialize(using = Mapper.MapperSerializer.class)
@JsonDeserialize(using = Mapper.MapperDeserializer.class)
public class Mapper<T> {

    private String field;
    private T value;

    public Mapper(String field, T value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return this.field;
    }

    public T getValue() {
        return this.value;
    }

    public static class MapperDeserializer extends JsonDeserializer<Mapper<?>> {

        @Override
        public Mapper<?> deserialize(JsonParser parser, DeserializationContext context) throws IOException {

            ObjectCodec codec = parser.getCodec();
            JsonNode node = codec.readTree(parser);

            Iterator<String> iterator = node.fieldNames();

            if(!iterator.hasNext())
                throw new JsonParseException(parser, "Invalid syntax.");

            String field = iterator.next();
            Object value = node.get(field);

            return new Mapper<>(field, value);
        }
    }

    public static class MapperSerializer extends JsonSerializer<Mapper<?>> {

        @Override
        public void serialize(Mapper<?> mapper, JsonGenerator generator, SerializerProvider provider) throws IOException {

            generator.writeStartObject();
            generator.writeObjectField(mapper.getField(), mapper.getValue());
            generator.writeEndObject();
        }
    }
}
