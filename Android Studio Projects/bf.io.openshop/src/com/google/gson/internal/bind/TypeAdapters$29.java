package com.google.gson.internal.bind;

import com.google.gson.*;
import com.google.gson.reflect.*;

static final class TypeAdapters$29 implements TypeAdapterFactory {
    final /* synthetic */ Class val$boxed;
    final /* synthetic */ TypeAdapter val$typeAdapter;
    final /* synthetic */ Class val$unboxed;
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        final Class<? super T> rawType = typeToken.getRawType();
        if (rawType == this.val$unboxed || rawType == this.val$boxed) {
            return (TypeAdapter<T>)this.val$typeAdapter;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Factory[type=" + this.val$boxed.getName() + "+" + this.val$unboxed.getName() + ",adapter=" + this.val$typeAdapter + "]";
    }
}