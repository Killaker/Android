package com.google.gson.internal.bind;

import com.google.gson.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$4 extends TypeAdapter<Boolean> {
    @Override
    public Boolean read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return Boolean.valueOf(jsonReader.nextString());
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Boolean b) throws IOException {
        String string;
        if (b == null) {
            string = "null";
        }
        else {
            string = b.toString();
        }
        jsonWriter.value(string);
    }
}