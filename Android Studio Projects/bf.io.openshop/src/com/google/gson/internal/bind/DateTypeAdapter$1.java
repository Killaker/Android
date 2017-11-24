package com.google.gson.internal.bind;

import com.google.gson.reflect.*;
import com.google.gson.*;
import java.util.*;

static final class DateTypeAdapter$1 implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (typeToken.getRawType() == Date.class) {
            return (TypeAdapter<T>)new DateTypeAdapter();
        }
        return null;
    }
}