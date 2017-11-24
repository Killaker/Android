package com.google.gson.internal;

import java.lang.reflect.*;
import com.google.gson.*;
import java.util.*;

class ConstructorConstructor$5 implements ObjectConstructor<T> {
    final /* synthetic */ Type val$type;
    
    @Override
    public T construct() {
        if (!(this.val$type instanceof ParameterizedType)) {
            throw new JsonIOException("Invalid EnumSet type: " + this.val$type.toString());
        }
        final Type type = ((ParameterizedType)this.val$type).getActualTypeArguments()[0];
        if (type instanceof Class) {
            return (T)EnumSet.noneOf((Class<Enum>)type);
        }
        throw new JsonIOException("Invalid EnumSet type: " + this.val$type.toString());
    }
}