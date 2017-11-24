package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import java.util.*;
import android.text.*;

public final class zzpu extends zze<zzpu>
{
    private String mCategory;
    private String zzSU;
    private long zzaDV;
    private String zzaUO;
    
    public String getAction() {
        return this.zzSU;
    }
    
    public String getLabel() {
        return this.zzaUO;
    }
    
    public long getValue() {
        return this.zzaDV;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("category", this.mCategory);
        hashMap.put("action", this.zzSU);
        hashMap.put("label", this.zzaUO);
        hashMap.put("value", (String)this.zzaDV);
        return zze.zzF(hashMap);
    }
    
    public String zzAZ() {
        return this.mCategory;
    }
    
    public void zzN(final long zzaDV) {
        this.zzaDV = zzaDV;
    }
    
    @Override
    public void zza(final zzpu zzpu) {
        if (!TextUtils.isEmpty((CharSequence)this.mCategory)) {
            zzpu.zzeE(this.mCategory);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzSU)) {
            zzpu.zzeF(this.zzSU);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUO)) {
            zzpu.zzeG(this.zzaUO);
        }
        if (this.zzaDV != 0L) {
            zzpu.zzN(this.zzaDV);
        }
    }
    
    public void zzeE(final String mCategory) {
        this.mCategory = mCategory;
    }
    
    public void zzeF(final String zzSU) {
        this.zzSU = zzSU;
    }
    
    public void zzeG(final String zzaUO) {
        this.zzaUO = zzaUO;
    }
}
