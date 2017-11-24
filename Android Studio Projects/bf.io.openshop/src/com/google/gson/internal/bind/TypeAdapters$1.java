package com.google.gson.internal.bind;

import com.google.gson.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$1 extends TypeAdapter<Class> {
    @Override
    public Class read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Class clazz) throws IOException {
        if (clazz == null) {
            jsonWriter.nullValue();
            return;
        }
        throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + clazz.getName() + ". Forgot to register a type adapter?");
    }
}