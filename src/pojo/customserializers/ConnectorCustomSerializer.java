package pojo.customserializers;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pojo.Connector;

import java.io.IOException;

public class ConnectorCustomSerializer extends JsonSerializer<Connector> {

    @Override
    public void serialize(Connector connector, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(connector.getX() + "_" + connector.getY());
    }



}
