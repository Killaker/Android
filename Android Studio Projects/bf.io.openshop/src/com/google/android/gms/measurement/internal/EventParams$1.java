package com.google.android.gms.measurement.internal;

import java.util.*;

class EventParams$1 implements Iterator<String> {
    Iterator<String> zzaVT = EventParams.zza(EventParams.this).keySet().iterator();
    
    @Override
    public boolean hasNext() {
        return this.zzaVT.hasNext();
    }
    
    @Override
    public String next() {
        return this.zzaVT.next();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}