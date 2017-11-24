package com.google.gson.internal.bind;

import com.google.gson.*;
import java.net.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$20 extends TypeAdapter<InetAddress> {
    @Override
    public InetAddress read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return InetAddress.getByName(jsonReader.nextString());
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final InetAddress inetAddress) throws IOException {
        String hostAddress;
        if (inetAddress == null) {
            hostAddress = null;
        }
        else {
            hostAddress = inetAddress.getHostAddress();
        }
        jsonWriter.value(hostAddress);
    }
}