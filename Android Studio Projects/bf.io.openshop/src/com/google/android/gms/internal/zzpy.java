package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import java.util.*;
import android.text.*;

public final class zzpy extends zze<zzpy>
{
    public String mCategory;
    public String zzaUO;
    public String zzaVa;
    public long zzaVb;
    
    public String getLabel() {
        return this.zzaUO;
    }
    
    public long getTimeInMillis() {
        return this.zzaVb;
    }
    
    public void setTimeInMillis(final long zzaVb) {
        this.zzaVb = zzaVb;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("variableName", this.zzaVa);
        hashMap.put("timeInMillis", (String)this.zzaVb);
        hashMap.put("category", this.mCategory);
        hashMap.put("label", this.zzaUO);
        return zze.zzF(hashMap);
    }
    
    public String zzAZ() {
        return this.mCategory;
    }
    
    public String zzBh() {
        return this.zzaVa;
    }
    
    @Override
    public void zza(final zzpy zzpy) {
        if (!TextUtils.isEmpty((CharSequence)this.zzaVa)) {
            zzpy.zzeL(this.zzaVa);
        }
        if (this.zzaVb != 0L) {
            zzpy.setTimeInMillis(this.zzaVb);
        }
        if (!TextUtils.isEmpty((CharSequence)this.mCategory)) {
            zzpy.zzeE(this.mCategory);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUO)) {
            zzpy.zzeG(this.zzaUO);
        }
    }
    
    public void zzeE(final String mCategory) {
        this.mCategory = mCategory;
    }
    
    public void zzeG(final String zzaUO) {
        this.zzaUO = zzaUO;
    }
    
    public void zzeL(final String zzaVa) {
        this.zzaVa = zzaVa;
    }
}
