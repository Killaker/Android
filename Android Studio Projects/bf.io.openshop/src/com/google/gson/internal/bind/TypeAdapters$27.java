package com.google.gson.internal.bind;

import com.google.gson.reflect.*;
import com.google.gson.*;

static final class TypeAdapters$27 implements TypeAdapterFactory {
    final /* synthetic */ TypeToken val$type;
    final /* synthetic */ TypeAdapter val$typeAdapter;
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (typeToken.equals(this.val$type)) {
            return (TypeAdapter<T>)this.val$typeAdapter;
        }
        return null;
    }
}