package com.google.gson.internal.bind;

import com.google.gson.*;
import com.google.gson.reflect.*;

static final class TypeAdapters$31 implements TypeAdapterFactory {
    final /* synthetic */ Class val$clazz;
    final /* synthetic */ TypeAdapter val$typeAdapter;
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if (this.val$clazz.isAssignableFrom(typeToken.getRawType())) {
            return (TypeAdapter<T>)this.val$typeAdapter;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Factory[typeHierarchy=" + this.val$clazz.getName() + ",adapter=" + this.val$typeAdapter + "]";
    }
}