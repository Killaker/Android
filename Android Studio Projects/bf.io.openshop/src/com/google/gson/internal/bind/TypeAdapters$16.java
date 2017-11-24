package com.google.gson.internal.bind;

import com.google.gson.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$16 extends TypeAdapter<StringBuilder> {
    @Override
    public StringBuilder read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return new StringBuilder(jsonReader.nextString());
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final StringBuilder sb) throws IOException {
        String string;
        if (sb == null) {
            string = null;
        }
        else {
            string = sb.toString();
        }
        jsonWriter.value(string);
    }
}