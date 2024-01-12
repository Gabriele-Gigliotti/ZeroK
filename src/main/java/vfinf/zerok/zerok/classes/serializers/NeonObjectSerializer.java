package vfinf.zerok.zerok.classes.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import vfinf.zerok.zerok.classes.elements.MapObject;

import java.io.IOException;

public class NeonObjectSerializer extends JsonSerializer<MapObject> {
    @Override
    public void serialize(MapObject value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("rotation",Double.toString(value.getRenderedPath().getRotation()));
        gen.writeStringField("x", Double.toString(value.getCoords().getX()));
        gen.writeStringField("y", Double.toString(value.getCoords().getY()));
        gen.writeEndObject();
    }
}
