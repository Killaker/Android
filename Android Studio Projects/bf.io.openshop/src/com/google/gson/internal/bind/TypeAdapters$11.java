package com.google.gson.internal.bind;

import com.google.gson.*;
import com.google.gson.internal.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$11 extends TypeAdapter<Number> {
    @Override
    public Number read(final JsonReader jsonReader) throws IOException {
        final JsonToken peek = jsonReader.peek();
        switch (peek) {
            default: {
                throw new JsonSyntaxException("Expecting number, got: " + peek);
            }
            case NULL: {
                jsonReader.nextNull();
                return null;
            }
            case NUMBER: {
                return new LazilyParsedNumber(jsonReader.nextString());
            }
        }
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Number n) throws IOException {
        jsonWriter.value(n);
    }
}