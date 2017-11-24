package com.google.gson.internal.bind;

import com.google.gson.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$12 extends TypeAdapter<Character> {
    @Override
    public Character read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        final String nextString = jsonReader.nextString();
        if (nextString.length() != 1) {
            throw new JsonSyntaxException("Expecting character, got: " + nextString);
        }
        return nextString.charAt(0);
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Character c) throws IOException {
        String value;
        if (c == null) {
            value = null;
        }
        else {
            value = String.valueOf(c);
        }
        jsonWriter.value(value);
    }
}