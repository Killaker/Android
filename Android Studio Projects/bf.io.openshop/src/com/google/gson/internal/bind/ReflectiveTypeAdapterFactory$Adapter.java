package com.google.gson.internal.bind;

import com.google.gson.internal.*;
import com.google.gson.*;
import java.io.*;
import com.google.gson.stream.*;
import java.util.*;

public static final class Adapter<T> extends TypeAdapter<T>
{
    private final Map<String, BoundField> boundFields;
    private final ObjectConstructor<T> constructor;
    
    private Adapter(final ObjectConstructor<T> constructor, final Map<String, BoundField> boundFields) {
        this.constructor = constructor;
        this.boundFields = boundFields;
    }
    
    @Override
    public T read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        final T construct = this.constructor.construct();
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                final BoundField boundField = this.boundFields.get(jsonReader.nextName());
                if (boundField != null && boundField.deserialized) {
                    goto Label_0091;
                }
                jsonReader.skipValue();
            }
        }
        catch (IllegalStateException ex) {
            throw new JsonSyntaxException(ex);
        }
        catch (IllegalAccessException ex2) {
            throw new AssertionError((Object)ex2);
        }
        jsonReader.endObject();
        return construct;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final T t) throws IOException {
        if (t == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        try {
            for (final BoundField boundField : this.boundFields.values()) {
                if (boundField.writeField(t)) {
                    jsonWriter.name(boundField.name);
                    boundField.write(jsonWriter, t);
                }
            }
        }
        catch (IllegalAccessException ex) {
            throw new AssertionError();
        }
        jsonWriter.endObject();
    }
}
