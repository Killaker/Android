package com.google.gson;

import java.io.*;
import com.google.gson.stream.*;

class Gson$5 extends TypeAdapter<Number> {
    @Override
    public Number read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return jsonReader.nextLong();
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Number n) throws IOException {
        if (n == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.value(n.toString());
    }
}