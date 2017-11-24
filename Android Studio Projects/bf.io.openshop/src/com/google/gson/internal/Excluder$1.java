package com.google.gson.internal;

import com.google.gson.reflect.*;
import com.google.gson.*;
import java.io.*;
import com.google.gson.stream.*;

class Excluder$1 extends TypeAdapter<T> {
    private TypeAdapter<T> delegate;
    final /* synthetic */ Gson val$gson;
    final /* synthetic */ boolean val$skipDeserialize;
    final /* synthetic */ boolean val$skipSerialize;
    final /* synthetic */ TypeToken val$type;
    
    private TypeAdapter<T> delegate() {
        final TypeAdapter<T> delegate = this.delegate;
        if (delegate != null) {
            return delegate;
        }
        return this.delegate = this.val$gson.getDelegateAdapter(Excluder.this, (TypeToken<T>)this.val$type);
    }
    
    @Override
    public T read(final JsonReader jsonReader) throws IOException {
        if (this.val$skipDeserialize) {
            jsonReader.skipValue();
            return null;
        }
        return this.delegate().read(jsonReader);
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final T t) throws IOException {
        if (this.val$skipSerialize) {
            jsonWriter.nullValue();
            return;
        }
        this.delegate().write(jsonWriter, t);
    }
}