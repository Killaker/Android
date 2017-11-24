package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import java.util.*;
import android.text.*;

public final class zzpx extends zze<zzpx>
{
    public String zzSU;
    public String zzaUY;
    public String zzaUZ;
    
    public String getAction() {
        return this.zzSU;
    }
    
    public String getTarget() {
        return this.zzaUZ;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("network", this.zzaUY);
        hashMap.put("action", this.zzSU);
        hashMap.put("target", this.zzaUZ);
        return zze.zzF(hashMap);
    }
    
    public String zzBg() {
        return this.zzaUY;
    }
    
    @Override
    public void zza(final zzpx zzpx) {
        if (!TextUtils.isEmpty((CharSequence)this.zzaUY)) {
            zzpx.zzeJ(this.zzaUY);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzSU)) {
            zzpx.zzeF(this.zzSU);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzaUZ)) {
            zzpx.zzeK(this.zzaUZ);
        }
    }
    
    public void zzeF(final String zzSU) {
        this.zzSU = zzSU;
    }
    
    public void zzeJ(final String zzaUY) {
        this.zzaUY = zzaUY;
    }
    
    public void zzeK(final String zzaUZ) {
        this.zzaUZ = zzaUZ;
    }
}
