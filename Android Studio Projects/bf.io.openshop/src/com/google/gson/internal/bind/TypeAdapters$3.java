package com.google.gson.internal.bind;

import com.google.gson.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$3 extends TypeAdapter<Boolean> {
    @Override
    public Boolean read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        if (jsonReader.peek() == JsonToken.STRING) {
            return Boolean.parseBoolean(jsonReader.nextString());
        }
        return jsonReader.nextBoolean();
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Boolean b) throws IOException {
        if (b == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.value(b);
    }
}