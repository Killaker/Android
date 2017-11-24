package com.google.gson.internal.bind;

import com.google.gson.reflect.*;
import com.google.gson.*;

static final class ObjectTypeAdapter$1 implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (typeToken.getRawType() == Object.class) {
            return (TypeAdapter<T>)new ObjectTypeAdapter(gson, null);
        }
        return null;
    }
}