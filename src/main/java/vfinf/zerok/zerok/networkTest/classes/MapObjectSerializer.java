package vfinf.zerok.zerok.networkTest.classes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MapObjectSerializer extends JsonSerializer<MapObject> {
    @Override
    public void serialize(MapObject value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("x", Double.toString(value.getCoords().getX()));
        gen.writeStringField("y", Double.toString(value.getCoords().getY()));
        gen.writeEndObject();
    }
}
