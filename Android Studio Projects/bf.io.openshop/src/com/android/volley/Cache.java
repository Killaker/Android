package com.android.volley;

import java.util.*;

public interface Cache
{
    void clear();
    
    Entry get(final String p0);
    
    void initialize();
    
    void invalidate(final String p0, final boolean p1);
    
    void put(final String p0, final Entry p1);
    
    void remove(final String p0);
    
    public static class Entry
    {
        public byte[] data;
        public String etag;
        public long lastModified;
        public Map<String, String> responseHeaders;
        public long serverDate;
        public long softTtl;
        public long ttl;
        
        public Entry() {
            this.responseHeaders = Collections.emptyMap();
        }
        
        public boolean isExpired() {
            return this.ttl < System.currentTimeMillis();
        }
        
        public boolean refreshNeeded() {
            return this.softTtl < System.currentTimeMillis();
        }
    }
}
