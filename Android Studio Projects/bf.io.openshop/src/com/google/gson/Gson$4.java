package com.google.gson;

import java.io.*;
import com.google.gson.stream.*;

class Gson$4 extends TypeAdapter<Number> {
    @Override
    public Float read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return (float)jsonReader.nextDouble();
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Number n) throws IOException {
        if (n == null) {
            jsonWriter.nullValue();
            return;
        }
        Gson.access$000(Gson.this, n.floatValue());
        jsonWriter.value(n);
    }
}