package com.google.gson.internal.bind;

import com.google.gson.*;
import com.google.gson.reflect.*;

static final class TypeAdapters$30 implements TypeAdapterFactory {
    final /* synthetic */ Class val$base;
    final /* synthetic */ Class val$sub;
    final /* synthetic */ TypeAdapter val$typeAdapter;
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        final Class<? super T> rawType = typeToken.getRawType();
        if (rawType == this.val$base || rawType == this.val$sub) {
            return (TypeAdapter<T>)this.val$typeAdapter;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Factory[type=" + this.val$base.getName() + "+" + this.val$sub.getName() + ",adapter=" + this.val$typeAdapter + "]";
    }
}