package com.google.gson.internal.bind;

import com.google.gson.*;
import java.util.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$21 extends TypeAdapter<UUID> {
    @Override
    public UUID read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return java.util.UUID.fromString(jsonReader.nextString());
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final UUID uuid) throws IOException {
        String string;
        if (uuid == null) {
            string = null;
        }
        else {
            string = uuid.toString();
        }
        jsonWriter.value(string);
    }
}