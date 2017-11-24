package com.google.gson.internal;

import com.google.gson.*;
import java.lang.reflect.*;

class ConstructorConstructor$2 implements ObjectConstructor<T> {
    final /* synthetic */ InstanceCreator val$rawTypeCreator;
    final /* synthetic */ Type val$type;
    
    @Override
    public T construct() {
        return this.val$rawTypeCreator.createInstance(this.val$type);
    }
}