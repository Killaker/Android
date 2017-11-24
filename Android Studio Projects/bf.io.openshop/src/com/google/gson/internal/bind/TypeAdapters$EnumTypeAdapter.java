package com.google.gson.internal.bind;

import com.google.gson.*;
import java.util.*;
import com.google.gson.annotations.*;
import java.io.*;
import com.google.gson.stream.*;

private static final class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T>
{
    private final Map<T, String> constantToName;
    private final Map<String, T> nameToConstant;
    
    public EnumTypeAdapter(final Class<T> clazz) {
        this.nameToConstant = new HashMap<String, T>();
        this.constantToName = new HashMap<T, String>();
        try {
            for (final Enum<T> enum1 : clazz.getEnumConstants()) {
                String s = enum1.name();
                final SerializedName serializedName = clazz.getField(s).getAnnotation(SerializedName.class);
                if (serializedName != null) {
                    s = serializedName.value();
                    final String[] alternate = serializedName.alternate();
                    for (int length2 = alternate.length, j = 0; j < length2; ++j) {
                        this.nameToConstant.put(alternate[j], (T)enum1);
                    }
                }
                this.nameToConstant.put(s, (T)enum1);
                this.constantToName.put((T)enum1, s);
            }
        }
        catch (NoSuchFieldException ex) {
            throw new AssertionError();
        }
    }
    
    @Override
    public T read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return this.nameToConstant.get(jsonReader.nextString());
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final T t) throws IOException {
        String s;
        if (t == null) {
            s = null;
        }
        else {
            s = this.constantToName.get(t);
        }
        jsonWriter.value(s);
    }
}
