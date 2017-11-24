package com.squareup.picasso;

public enum MemoryPolicy
{
    NO_CACHE(1), 
    NO_STORE(2);
    
    final int index;
    
    private MemoryPolicy(final int index) {
        this.index = index;
    }
    
    static boolean shouldReadFromMemoryCache(final int n) {
        return (n & MemoryPolicy.NO_CACHE.index) == 0x0;
    }
    
    static boolean shouldWriteToMemoryCache(final int n) {
        return (n & MemoryPolicy.NO_STORE.index) == 0x0;
    }
}
