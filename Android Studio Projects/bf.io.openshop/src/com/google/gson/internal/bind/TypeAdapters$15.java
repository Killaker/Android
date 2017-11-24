package com.google.gson.internal.bind;

import java.math.*;
import java.io.*;
import com.google.gson.*;
import com.google.gson.stream.*;

static final class TypeAdapters$15 extends TypeAdapter<BigInteger> {
    @Override
    public BigInteger read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return new BigInteger(jsonReader.nextString());
        }
        catch (NumberFormatException ex) {
            throw new JsonSyntaxException(ex);
        }
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final BigInteger bigInteger) throws IOException {
        jsonWriter.value(bigInteger);
    }
}