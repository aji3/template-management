package org.xlbean.xlapi.api;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class Base64Deserializer extends StdDeserializer<byte[]> {

    public Base64Deserializer() {
        this(null);
    }

    public Base64Deserializer(Class<byte[]> t) {
        super(t);
    }

    @Override
    public byte[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        String value = node.asText();
        return Base64.getDecoder().decode(value);
    }

}
