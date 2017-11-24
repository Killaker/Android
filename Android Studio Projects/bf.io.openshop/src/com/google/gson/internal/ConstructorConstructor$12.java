package com.google.gson.internal;

import java.lang.reflect.*;

class ConstructorConstructor$12 implements ObjectConstructor<T> {
    private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
    final /* synthetic */ Class val$rawType;
    final /* synthetic */ Type val$type;
    
    @Override
    public T construct() {
        try {
            return this.unsafeAllocator.newInstance((Class<T>)this.val$rawType);
        }
        catch (Exception ex) {
            throw new RuntimeException("Unable to invoke no-args constructor for " + this.val$type + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", ex);
        }
    }
}