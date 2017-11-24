package com.google.gson.internal.bind;

import com.google.gson.reflect.*;
import com.google.gson.*;
import java.sql.*;

static final class TimeTypeAdapter$1 implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (typeToken.getRawType() == Time.class) {
            return (TypeAdapter<T>)new TimeTypeAdapter();
        }
        return null;
    }
}