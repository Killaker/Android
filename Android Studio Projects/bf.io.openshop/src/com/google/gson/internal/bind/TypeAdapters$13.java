package com.google.gson.internal.bind;

import com.google.gson.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$13 extends TypeAdapter<String> {
    @Override
    public String read(final JsonReader jsonReader) throws IOException {
        final JsonToken peek = jsonReader.peek();
        if (peek == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        if (peek == JsonToken.BOOLEAN) {
            return Boolean.toString(jsonReader.nextBoolean());
        }
        return jsonReader.nextString();
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final String s) throws IOException {
        jsonWriter.value(s);
    }
}