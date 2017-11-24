package com.google.gson;

import java.io.*;
import com.google.gson.stream.*;

class Gson$3 extends TypeAdapter<Number> {
    @Override
    public Double read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return jsonReader.nextDouble();
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Number n) throws IOException {
        if (n == null) {
            jsonWriter.nullValue();
            return;
        }
        Gson.access$000(Gson.this, n.doubleValue());
        jsonWriter.value(n);
    }
}