package com.google.gson;

import java.lang.reflect.*;

class Gson$2 implements JsonSerializationContext {
    @Override
    public JsonElement serialize(final Object o) {
        return Gson.this.toJsonTree(o);
    }
    
    @Override
    public JsonElement serialize(final Object o, final Type type) {
        return Gson.this.toJsonTree(o, type);
    }
}