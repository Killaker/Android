package com.google.gson.internal.bind;

import java.io.*;
import com.google.gson.*;
import java.net.*;
import com.google.gson.stream.*;

static final class TypeAdapters$19 extends TypeAdapter<URI> {
    @Override
    public URI read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
        }
        else {
            try {
                final String nextString = jsonReader.nextString();
                if (!"null".equals(nextString)) {
                    return new URI(nextString);
                }
            }
            catch (URISyntaxException ex) {
                throw new JsonIOException(ex);
            }
        }
        return null;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final URI uri) throws IOException {
        String asciiString;
        if (uri == null) {
            asciiString = null;
        }
        else {
            asciiString = uri.toASCIIString();
        }
        jsonWriter.value(asciiString);
    }
}