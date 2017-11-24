package com.google.gson.internal.bind;

import com.google.gson.reflect.*;
import com.google.gson.*;
import com.google.gson.internal.*;
import java.lang.reflect.*;

static final class ArrayTypeAdapter$1 implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        final Type type = typeToken.getType();
        if (!(type instanceof GenericArrayType) && (!(type instanceof Class) || !((Class)type).isArray())) {
            return null;
        }
        final Type arrayComponentType = $Gson$Types.getArrayComponentType(type);
        return (TypeAdapter<T>)new ArrayTypeAdapter(gson, (TypeAdapter<Object>)gson.getAdapter(TypeToken.get(arrayComponentType)), (Class<Object>)$Gson$Types.getRawType(arrayComponentType));
    }
}