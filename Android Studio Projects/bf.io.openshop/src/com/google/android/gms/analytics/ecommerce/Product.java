package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.common.internal.*;
import com.google.android.gms.analytics.*;
import com.google.android.gms.measurement.*;
import java.util.*;

public class Product
{
    Map<String, String> zzPU;
    
    public Product() {
        this.zzPU = new HashMap<String, String>();
    }
    
    void put(final String s, final String s2) {
        zzx.zzb(s, "Name should be non-null");
        this.zzPU.put(s, s2);
    }
    
    public Product setBrand(final String s) {
        this.put("br", s);
        return this;
    }
    
    public Product setCategory(final String s) {
        this.put("ca", s);
        return this;
    }
    
    public Product setCouponCode(final String s) {
        this.put("cc", s);
        return this;
    }
    
    public Product setCustomDimension(final int n, final String s) {
        this.put(zzc.zzae(n), s);
        return this;
    }
    
    public Product setCustomMetric(final int n, final int n2) {
        this.put(zzc.zzaf(n), Integer.toString(n2));
        return this;
    }
    
    public Product setId(final String s) {
        this.put("id", s);
        return this;
    }
    
    public Product setName(final String s) {
        this.put("nm", s);
        return this;
    }
    
    public Product setPosition(final int n) {
        this.put("ps", Integer.toString(n));
        return this;
    }
    
    public Product setPrice(final double n) {
        this.put("pr", Double.toString(n));
        return this;
    }
    
    public Product setQuantity(final int n) {
        this.put("qt", Integer.toString(n));
        return this;
    }
    
    public Product setVariant(final String s) {
        this.put("va", s);
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
