package com.google.gson.internal;

import java.lang.reflect.*;

static final class UnsafeAllocator$1 extends UnsafeAllocator {
    final /* synthetic */ Method val$allocateInstance;
    final /* synthetic */ Object val$unsafe;
    
    @Override
    public <T> T newInstance(final Class<T> clazz) throws Exception {
        return (T)this.val$allocateInstance.invoke(this.val$unsafe, clazz);
    }
}