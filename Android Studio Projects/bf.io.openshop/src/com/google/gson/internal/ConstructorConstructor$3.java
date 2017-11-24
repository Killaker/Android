package com.google.gson.internal;

import java.lang.reflect.*;

class ConstructorConstructor$3 implements ObjectConstructor<T> {
    final /* synthetic */ Constructor val$constructor;
    
    @Override
    public T construct() {
        try {
            return this.val$constructor.newInstance((Object[])null);
        }
        catch (InstantiationException ex) {
            throw new RuntimeException("Failed to invoke " + this.val$constructor + " with no args", ex);
        }
        catch (InvocationTargetException ex2) {
            throw new RuntimeException("Failed to invoke " + this.val$constructor + " with no args", ex2.getTargetException());
        }
        catch (IllegalAccessException ex3) {
            throw new AssertionError((Object)ex3);
        }
    }
}