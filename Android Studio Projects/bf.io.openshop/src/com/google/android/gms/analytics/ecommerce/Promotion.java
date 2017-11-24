package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.common.internal.*;
import com.google.android.gms.measurement.*;
import java.util.*;

public class Promotion
{
    public static final String ACTION_CLICK = "click";
    public static final String ACTION_VIEW = "view";
    Map<String, String> zzPU;
    
    public Promotion() {
        this.zzPU = new HashMap<String, String>();
    }
    
    void put(final String s, final String s2) {
        zzx.zzb(s, "Name should be non-null");
        this.zzPU.put(s, s2);
    }
    
    public Promotion setCreative(final String s) {
        this.put("cr", s);
        return this;
    }
    
    public Promotion setId(final String s) {
        this.put("id", s);
        return this;
    }
    
    public Promotion setName(final String s) {
        this.put("nm", s);
        return this;
    }
    
    public Promotion setPosition(final String s) {
        this.put("ps", s);
        return this;
    }
    
    @Override
    public String toString() {
        return zze.zzO(this.zzPU);
    }
    
    public Map<String, String> zzba(final String s) {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (final Map.Entry<String, String> entry : this.zzPU.entrySet()) {
            hashMap.put(s + entry.getKey(), entry.getValue());
        }
        return (Map<String, String>)hashMap;
    }
}
