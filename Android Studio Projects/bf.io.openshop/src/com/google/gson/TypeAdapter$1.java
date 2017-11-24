package com.google.gson;

import java.io.*;
import com.google.gson.stream.*;

class TypeAdapter$1 extends TypeAdapter<T> {
    @Override
    public T read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return TypeAdapter.this.read(jsonReader);
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final T t) throws IOException {
        if (t == null) {
            jsonWriter.nullValue();
            return;
        }
        TypeAdapter.this.write(jsonWriter, t);
    }
}