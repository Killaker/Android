package com.google.gson.internal.bind;

import com.google.gson.*;
import java.lang.reflect.*;
import com.google.gson.reflect.*;
import java.io.*;
import com.google.gson.stream.*;

class ReflectiveTypeAdapterFactory$1 extends BoundField {
    final TypeAdapter<?> typeAdapter = ReflectiveTypeAdapterFactory.access$100(ReflectiveTypeAdapterFactory.this, this.val$context, this.val$field, this.val$fieldType);
    final /* synthetic */ Gson val$context;
    final /* synthetic */ Field val$field;
    final /* synthetic */ TypeToken val$fieldType;
    final /* synthetic */ boolean val$isPrimitive;
    
    @Override
    void read(final JsonReader jsonReader, final Object o) throws IOException, IllegalAccessException {
        final Object read = this.typeAdapter.read(jsonReader);
        if (read != null || !this.val$isPrimitive) {
            this.val$field.set(o, read);
        }
    }
    
    @Override
    void write(final JsonWriter jsonWriter, final Object o) throws IOException, IllegalAccessException {
        new TypeAdapterRuntimeTypeWrapper<Object>(this.val$context, this.typeAdapter, this.val$fieldType.getType()).write(jsonWriter, this.val$field.get(o));
    }
    
    public boolean writeField(final Object o) throws IOException, IllegalAccessException {
        return this.serialized && this.val$field.get(o) != o;
    }
}