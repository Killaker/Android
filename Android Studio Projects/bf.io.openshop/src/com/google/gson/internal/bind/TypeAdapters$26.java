package com.google.gson.internal.bind;

import com.google.gson.reflect.*;
import com.google.gson.*;
import java.io.*;

static final class TypeAdapters$26 implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        Serializable s = typeToken.getRawType();
        if (!Enum.class.isAssignableFrom((Class<?>)s) || s == Enum.class) {
            return null;
        }
        if (!((Class)s).isEnum()) {
            s = ((Class<Enum>)s).getSuperclass();
        }
        return new EnumTypeAdapter<T>((Class<T>)s);
    }
}