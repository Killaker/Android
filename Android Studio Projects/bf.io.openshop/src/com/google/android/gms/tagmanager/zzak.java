package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.util.*;

abstract class zzak
{
    private final Set<String> zzbiU;
    private final String zzbiV;
    
    public zzak(final String zzbiV, final String... array) {
        this.zzbiV = zzbiV;
        this.zzbiU = new HashSet<String>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            this.zzbiU.add(array[i]);
        }
    }
    
    public abstract boolean zzFW();
    
    public String zzGB() {
        return this.zzbiV;
    }
    
    public Set<String> zzGC() {
        return this.zzbiU;
    }
    
    public abstract zzag.zza zzP(final Map<String, zzag.zza> p0);
    
    boolean zze(final Set<String> set) {
        return set.containsAll(this.zzbiU);
    }
}
