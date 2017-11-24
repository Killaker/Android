package com.google.gson.internal;

import java.lang.reflect.*;

static final class UnsafeAllocator$2 extends UnsafeAllocator {
    final /* synthetic */ int val$constructorId;
    final /* synthetic */ Method val$newInstance;
    
    @Override
    public <T> T newInstance(final Class<T> clazz) throws Exception {
        return (T)this.val$newInstance.invoke(null, clazz, this.val$constructorId);
    }
}