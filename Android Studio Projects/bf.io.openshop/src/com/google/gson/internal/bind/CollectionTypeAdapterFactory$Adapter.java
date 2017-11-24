package com.google.gson.internal.bind;

import com.google.gson.internal.*;
import com.google.gson.*;
import java.lang.reflect.*;
import java.io.*;
import com.google.gson.stream.*;
import java.util.*;

private static final class Adapter<E> extends TypeAdapter<Collection<E>>
{
    private final ObjectConstructor<? extends Collection<E>> constructor;
    private final TypeAdapter<E> elementTypeAdapter;
    
    public Adapter(final Gson gson, final Type type, final TypeAdapter<E> typeAdapter, final ObjectConstructor<? extends Collection<E>> constructor) {
        this.elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper<E>(gson, typeAdapter, type);
        this.constructor = constructor;
    }
    
    @Override
    public Collection<E> read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        final Collection collection = (Collection)this.constructor.construct();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            collection.add(this.elementTypeAdapter.read(jsonReader));
        }
        jsonReader.endArray();
        return (Collection<E>)collection;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Collection<E> collection) throws IOException {
        if (collection == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        final Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.elementTypeAdapter.write(jsonWriter, iterator.next());
        }
        jsonWriter.endArray();
    }
}
