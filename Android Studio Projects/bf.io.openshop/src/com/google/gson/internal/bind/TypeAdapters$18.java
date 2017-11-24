package com.google.gson.internal.bind;

import com.google.gson.*;
import java.net.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$18 extends TypeAdapter<URL> {
    @Override
    public URL read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
        }
        else {
            final String nextString = jsonReader.nextString();
            if (!"null".equals(nextString)) {
                return new URL(nextString);
            }
        }
        return null;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final URL url) throws IOException {
        String externalForm;
        if (url == null) {
            externalForm = null;
        }
        else {
            externalForm = url.toExternalForm();
        }
        jsonWriter.value(externalForm);
    }
}