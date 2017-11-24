package com.google.gson.internal.bind;

import com.google.gson.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$7 extends TypeAdapter<Number> {
    @Override
    public Number read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return jsonReader.nextInt();
        }
        catch (NumberFormatException ex) {
            throw new JsonSyntaxException(ex);
        }
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Number n) throws IOException {
        jsonWriter.value(n);
    }
}