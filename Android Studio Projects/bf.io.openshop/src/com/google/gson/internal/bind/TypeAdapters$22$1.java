package com.google.gson.internal.bind;

import com.google.gson.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import com.google.gson.stream.*;

class TypeAdapters$22$1 extends TypeAdapter<Timestamp> {
    final /* synthetic */ TypeAdapter val$dateTypeAdapter;
    
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
}