package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import java.util.*;
import android.text.*;

public final class zzpv extends zze<zzpv>
{
    public boolean zzaUP;
    public String zzaxl;
    
    public String getDescription() {
        return this.zzaxl;
    }
    
    public void setDescription(final String zzaxl) {
        this.zzaxl = zzaxl;
    }
    
    @Override
    public String toString() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("description", this.zzaxl);
        hashMap.put("fatal", (String)this.zzaUP);
        return zze.zzF(hashMap);
    }
    
    public boolean zzBa() {
        return this.zzaUP;
    }
    
    @Override
    public void zza(final zzpv zzpv) {
        if (!TextUtils.isEmpty((CharSequence)this.zzaxl)) {
            zzpv.setDescription(this.zzaxl);
        }
        if (this.zzaUP) {
            zzpv.zzao(this.zzaUP);
        }
    }
    
    public void zzao(final boolean zzaUP) {
        this.zzaUP = zzaUP;
    }
}
