package com.google.gson.internal.bind;

import java.math.*;
import java.io.*;
import com.google.gson.*;
import com.google.gson.stream.*;

static final class TypeAdapters$14 extends TypeAdapter<BigDecimal> {
    @Override
    public BigDecimal read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return new BigDecimal(jsonReader.nextString());
        }
        catch (NumberFormatException ex) {
            throw new JsonSyntaxException(ex);
        }
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final BigDecimal bigDecimal) throws IOException {
        jsonWriter.value(bigDecimal);
    }
}