package com.google.gson.internal.bind;

import com.google.gson.reflect.*;
import com.google.gson.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import com.google.gson.stream.*;

static final class TypeAdapters$22 implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (typeToken.getRawType() != Timestamp.class) {
            return null;
        }
        return (TypeAdapter<T>)new TypeAdapter<Timestamp>() {
            final /* synthetic */ TypeAdapter val$dateTypeAdapter = gson.getAdapter(Date.class);
            
            @Override
            public Timestamp read(final JsonReader jsonReader) throws IOException {
                final Date date = this.val$dateTypeAdapter.read(jsonReader);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }
            
            @Override
            public void write(final JsonWriter jsonWriter, final Timestamp timestamp) throws IOException {
                this.val$dateTypeAdapter.write(jsonWriter, timestamp);
            }
        };
    }
}