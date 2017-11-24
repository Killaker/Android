package com.google.gson.internal.bind;

import com.google.gson.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$17 extends TypeAdapter<StringBuffer> {
    @Override
    public StringBuffer read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return new StringBuffer(jsonReader.nextString());
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final StringBuffer sb) throws IOException {
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