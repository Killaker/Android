package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.*;
import android.support.annotation.*;
import android.content.*;

public final class zza
{
    private final boolean zzaXy;
    private boolean zzaXz;
    private boolean zzagf;
    private final String zzvs;
    
    public zza(final String zzvs, final boolean zzaXy) {
        zzx.zzcM(zzvs);
        this.zzvs = zzvs;
        this.zzaXy = zzaXy;
    }
    
    @WorkerThread
    private void zzCR() {
        if (this.zzaXz) {
            return;
        }
        this.zzaXz = true;
        this.zzagf = zzt.zza(zzt.this).getBoolean(this.zzvs, this.zzaXy);
    }
    
    @WorkerThread
    public boolean get() {
        this.zzCR();
        return this.zzagf;
    }
    
    @WorkerThread
    public void set(final boolean zzagf) {
        final SharedPreferences$Editor edit = zzt.zza(zzt.this).edit();
        edit.putBoolean(this.zzvs, zzagf);
        edit.apply();
        this.zzagf = zzagf;
    }
}
