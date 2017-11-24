package com.google.gson;

import java.io.*;
import com.google.gson.stream.*;

static class FutureTypeAdapter<T> extends TypeAdapter<T>
{
    private TypeAdapter<T> delegate;
    
    @Override
    public T read(final JsonReader jsonReader) throws IOException {
        if (this.delegate == null) {
            throw new IllegalStateException();
        }
        return this.delegate.read(jsonReader);
    }
    
    public void setDelegate(final TypeAdapter<T> delegate) {
        if (this.delegate != null) {
            throw new AssertionError();
        }
        this.delegate = delegate;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final T t) throws IOException {
        if (this.delegate == null) {
            throw new IllegalStateException();
        }
        this.delegate.write(jsonWriter, t);
    }
}
