package com.google.gson;

import java.lang.reflect.*;

class Gson$1 implements JsonDeserializationContext {
    @Override
    public <T> T deserialize(final JsonElement jsonElement, final Type type) throws JsonParseException {
        return Gson.this.fromJson(jsonElement, type);
    }
}