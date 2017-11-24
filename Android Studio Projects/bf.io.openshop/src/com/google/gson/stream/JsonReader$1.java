package com.google.gson.stream;

import com.google.gson.internal.*;
import com.google.gson.internal.bind.*;
import java.io.*;

static final class JsonReader$1 extends JsonReaderInternalAccess {
    @Override
    public void promoteNameToValue(final JsonReader jsonReader) throws IOException {
        if (jsonReader instanceof JsonTreeReader) {
            ((JsonTreeReader)jsonReader).promoteNameToValue();
            return;
        }
        int n = JsonReader.access$000(jsonReader);
        if (n == 0) {
            n = JsonReader.access$100(jsonReader);
        }
        if (n == 13) {
            JsonReader.access$002(jsonReader, 9);
            return;
        }
        if (n == 12) {
            JsonReader.access$002(jsonReader, 8);
            return;
        }
        if (n == 14) {
            JsonReader.access$002(jsonReader, 10);
            return;
        }
        throw new IllegalStateException("Expected a name but was " + jsonReader.peek() + " " + " at line " + JsonReader.access$200(jsonReader) + " column " + JsonReader.access$300(jsonReader) + " path " + jsonReader.getPath());
    }
}