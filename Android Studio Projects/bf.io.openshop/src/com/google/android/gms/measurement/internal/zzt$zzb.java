package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.*;
import android.support.annotation.*;
import android.content.*;

public final class zzb
{
    private long zzaDV;
    private final long zzaXB;
    private boolean zzaXz;
    private final String zzvs;
    
    public zzb(final String zzvs, final long zzaXB) {
        zzx.zzcM(zzvs);
        this.zzvs = zzvs;
        this.zzaXB = zzaXB;
    }
    
    @WorkerThread
    private void zzCR() {
        if (this.zzaXz) {
            return;
        }
        this.zzaXz = true;
        this.zzaDV = zzt.zza(zzt.this).getLong(this.zzvs, this.zzaXB);
    }
    
    @WorkerThread
    public long get() {
        this.zzCR();
        return this.zzaDV;
    }
    
    @WorkerThread
    public void set(final long zzaDV) {
        final SharedPreferences$Editor edit = zzt.zza(zzt.this).edit();
        edit.putLong(this.zzvs, zzaDV);
        edit.apply();
        this.zzaDV = zzaDV;
    }
}
