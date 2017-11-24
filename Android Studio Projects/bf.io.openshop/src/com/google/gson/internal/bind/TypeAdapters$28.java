package com.google.gson.internal.bind;

import com.google.gson.*;
import com.google.gson.reflect.*;

static final class TypeAdapters$28 implements TypeAdapterFactory {
    final /* synthetic */ Class val$type;
    final /* synthetic */ TypeAdapter val$typeAdapter;
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (typeToken.getRawType() == this.val$type) {
            return (TypeAdapter<T>)this.val$typeAdapter;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Factory[type=" + this.val$type.getName() + ",adapter=" + this.val$typeAdapter + "]";
    }
}