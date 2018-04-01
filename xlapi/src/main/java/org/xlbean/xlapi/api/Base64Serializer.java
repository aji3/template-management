package org.xlbean.xlapi.api;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("serial")
public class Base64Serializer extends StdSerializer<byte[]> {

    public Base64Serializer() {
        this(null);
    }

    public Base64Serializer(Class<byte[]> t) {
        super(t);
    }

    @Override
    public void serialize(byte[] value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String base64EncodedStr = Base64.getEncoder().encodeToString(value);
        gen.writeString(base64EncodedStr);
    }

}
