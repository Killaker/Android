package com.google.gson.internal;

import java.lang.reflect.*;
import com.google.gson.*;

class ConstructorConstructor$1 implements ObjectConstructor<T> {
    final /* synthetic */ Type val$type;
    final /* synthetic */ InstanceCreator val$typeCreator;
    
    @Override
    public T construct() {
        return this.val$typeCreator.createInstance(this.val$type);
    }
}