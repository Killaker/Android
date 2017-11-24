package com.google.gson.internal.bind;

import java.io.*;
import com.google.gson.stream.*;

abstract static class BoundField
{
    final boolean deserialized;
    final String name;
    final boolean serialized;
    
    protected BoundField(final String name, final boolean serialized, final boolean deserialized) {
        this.name = name;
        this.serialized = serialized;
        this.deserialized = deserialized;
    }
    
    abstract void read(final JsonReader p0, final Object p1) throws IOException, IllegalAccessException;
    
    abstract void write(final JsonWriter p0, final Object p1) throws IOException, IllegalAccessException;
    
    abstract boolean writeField(final Object p0) throws IOException, IllegalAccessException;
}
