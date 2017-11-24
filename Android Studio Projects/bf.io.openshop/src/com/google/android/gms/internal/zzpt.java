package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import com.google.android.gms.analytics.ecommerce.*;
import java.util.*;

public final class zzpt extends zze<zzpt>
{
    private ProductAction zzPn;
    private final Map<String, List<Product>> zzPo;
    private final List<Promotion> zzPp;
    private final List<Product> zzPq;
    
    public zzpt() {
        this.zzPq = new ArrayList<Product>();
        this.zzPp = new ArrayList<Promotion>();
        this.zzPo = new HashMap<String, List<Product>>();
    }
    
    @Override
    public String toString() {
        final HashMap<String, List<Product>> hashMap = new HashMap<String, List<Product>>();
        if (!this.zzPq.isEmpty()) {
            hashMap.put("products", this.zzPq);
        }
        if (!this.zzPp.isEmpty()) {
            hashMap.put("promotions", (List<Product>)this.zzPp);
        }
        if (!this.zzPo.isEmpty()) {
            hashMap.put("impressions", (List<Product>)this.zzPo);
        }
        hashMap.put("productAction", (List<Product>)this.zzPn);
        return zze.zzF(hashMap);
    }
    
    public ProductAction zzAV() {
        return this.zzPn;
    }
    
    public List<Product> zzAW() {
        return Collections.unmodifiableList((List<? extends Product>)this.zzPq);
    }
    
    public Map<String, List<Product>> zzAX() {
        return this.zzPo;
    }
    
    public List<Promotion> zzAY() {
        return Collections.unmodifiableList((List<? extends Promotion>)this.zzPp);
    }
    
    public void zza(final Product product, String s) {
        if (product == null) {
            return;
        }
        if (s == null) {
            s = "";
        }
        if (!this.zzPo.containsKey(s)) {
            this.zzPo.put(s, new ArrayList<Product>());
        }
        this.zzPo.get(s).add(product);
    }
    
    @Override
    public void zza(final zzpt zzpt) {
        zzpt.zzPq.addAll(this.zzPq);
        zzpt.zzPp.addAll(this.zzPp);
        for (final Map.Entry<String, List<Product>> entry : this.zzPo.entrySet()) {
            final String s = entry.getKey();
            final Iterator<Product> iterator2 = entry.getValue().iterator();
            while (iterator2.hasNext()) {
                zzpt.zza(iterator2.next(), s);
            }
        }
        if (this.zzPn != null) {
            zzpt.zzPn = this.zzPn;
        }
    }
}
