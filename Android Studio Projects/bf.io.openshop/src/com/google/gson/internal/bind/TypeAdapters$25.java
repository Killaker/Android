package com.google.gson.internal.bind;

import com.google.gson.internal.*;
import com.google.gson.*;
import java.io.*;
import java.util.*;
import com.google.gson.stream.*;

static final class TypeAdapters$25 extends TypeAdapter<JsonElement> {
    @Override
    public JsonElement read(final JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
            default: {
                throw new IllegalArgumentException();
            }
            case STRING: {
                return new JsonPrimitive(jsonReader.nextString());
            }
            case NUMBER: {
                return new JsonPrimitive(new LazilyParsedNumber(jsonReader.nextString()));
            }
            case BOOLEAN: {
                return new JsonPrimitive(Boolean.valueOf(jsonReader.nextBoolean()));
            }
            case NULL: {
                jsonReader.nextNull();
                return JsonNull.INSTANCE;
            }
            case BEGIN_ARRAY: {
                final JsonArray jsonArray = new JsonArray();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonArray.add(this.read(jsonReader));
                }
                jsonReader.endArray();
                return jsonArray;
            }
            case BEGIN_OBJECT: {
                final JsonObject jsonObject = new JsonObject();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    jsonObject.add(jsonReader.nextName(), this.read(jsonReader));
                }
                jsonReader.endObject();
                return jsonObject;
            }
        }
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final JsonElement jsonElement) throws IOException {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            jsonWriter.nullValue();
            return;
        }
        if (jsonElement.isJsonPrimitive()) {
            final JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (asJsonPrimitive.isNumber()) {
                jsonWriter.value(asJsonPrimitive.getAsNumber());
                return;
            }
            if (asJsonPrimitive.isBoolean()) {
                jsonWriter.value(asJsonPrimitive.getAsBoolean());
                return;
            }
            jsonWriter.value(asJsonPrimitive.getAsString());
        }
        else {
            if (jsonElement.isJsonArray()) {
                jsonWriter.beginArray();
                final Iterator<JsonElement> iterator = jsonElement.getAsJsonArray().iterator();
                while (iterator.hasNext()) {
                    this.write(jsonWriter, iterator.next());
                }
                jsonWriter.endArray();
                return;
            }
            if (jsonElement.isJsonObject()) {
                jsonWriter.beginObject();
                for (final Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                    jsonWriter.name(entry.getKey());
                    this.write(jsonWriter, entry.getValue());
                }
                jsonWriter.endObject();
                return;
            }
            throw new IllegalArgumentException("Couldn't write " + jsonElement.getClass());
        }
    }
}