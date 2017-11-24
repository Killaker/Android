package com.google.gson.internal.bind;

import com.google.gson.*;
import java.io.*;
import java.util.*;
import com.google.gson.stream.*;

static final class TypeAdapters$24 extends TypeAdapter<Locale> {
    @Override
    public Locale read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(jsonReader.nextString(), "_");
        final boolean hasMoreElements = stringTokenizer.hasMoreElements();
        String nextToken = null;
        if (hasMoreElements) {
            nextToken = stringTokenizer.nextToken();
        }
        final boolean hasMoreElements2 = stringTokenizer.hasMoreElements();
        String nextToken2 = null;
        if (hasMoreElements2) {
            nextToken2 = stringTokenizer.nextToken();
        }
        final boolean hasMoreElements3 = stringTokenizer.hasMoreElements();
        String nextToken3 = null;
        if (hasMoreElements3) {
            nextToken3 = stringTokenizer.nextToken();
        }
        if (nextToken2 == null && nextToken3 == null) {
            return new Locale(nextToken);
        }
        if (nextToken3 == null) {
            return new Locale(nextToken, nextToken2);
        }
        return new Locale(nextToken, nextToken2, nextToken3);
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Locale locale) throws IOException {
        String string;
        if (locale == null) {
            string = null;
        }
        else {
            string = locale.toString();
        }
        jsonWriter.value(string);
    }
}