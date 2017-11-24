package com.google.gson.internal.bind;

import java.util.*;
import java.io.*;
import com.google.gson.*;
import com.google.gson.stream.*;

static final class TypeAdapters$2 extends TypeAdapter<BitSet> {
    @Override
    public BitSet read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        final BitSet set = new BitSet();
        jsonReader.beginArray();
        int n = 0;
    Label_0215:
        for (JsonToken jsonToken = jsonReader.peek(); jsonToken != JsonToken.END_ARRAY; jsonToken = jsonReader.peek()) {
            int nextBoolean = 0;
            switch (jsonToken) {
                default: {
                    throw new JsonSyntaxException("Invalid bitset value type: " + jsonToken);
                }
                case NUMBER: {
                    if (jsonReader.nextInt() != 0) {
                        nextBoolean = 1;
                        break;
                    }
                    nextBoolean = 0;
                    break;
                }
                case BOOLEAN: {
                    nextBoolean = (jsonReader.nextBoolean() ? 1 : 0);
                    break;
                }
                case STRING: {
                    final String nextString = jsonReader.nextString();
                    try {
                        if (Integer.parseInt(nextString) != 0) {
                            nextBoolean = 1;
                        }
                        else {
                            nextBoolean = 0;
                        }
                        break;
                    }
                    catch (NumberFormatException ex) {
                        throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + nextString);
                    }
                    break Label_0215;
                }
            }
            if (nextBoolean != 0) {
                set.set(n);
            }
            ++n;
        }
        jsonReader.endArray();
        return set;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final BitSet set) throws IOException {
        if (set == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        for (int i = 0; i < set.length(); ++i) {
            boolean b;
            if (set.get(i)) {
                b = true;
            }
            else {
                b = false;
            }
            jsonWriter.value(b ? 1 : 0);
        }
        jsonWriter.endArray();
    }
}