package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.*;
import android.content.*;
import android.support.annotation.*;
import android.util.*;

public final class zzc
{
    private final long zzTl;
    final String zzaXC;
    private final String zzaXD;
    private final String zzaXE;
    
    private zzc(final String s, final long zzTl) {
        zzx.zzcM(s);
        zzx.zzac(zzTl > 0L);
        this.zzaXC = s + ":start";
        this.zzaXD = s + ":count";
        this.zzaXE = s + ":value";
        this.zzTl = zzTl;
    }
    
    @WorkerThread
    private void zzlL() {
        zzt.this.zzjk();
        final long currentTimeMillis = zzt.this.zzjl().currentTimeMillis();
        final SharedPreferences$Editor edit = zzt.zza(zzt.this).edit();
        edit.remove(this.zzaXD);
        edit.remove(this.zzaXE);
        edit.putLong(this.zzaXC, currentTimeMillis);
        edit.apply();
    }
    
    @WorkerThread
    private long zzlM() {
        zzt.this.zzjk();
        final long zzlO = this.zzlO();
        if (zzlO == 0L) {
            this.zzlL();
            return 0L;
        }
        return Math.abs(zzlO - zzt.this.zzjl().currentTimeMillis());
    }
    
    @WorkerThread
    private long zzlO() {
        return zzt.zzc(zzt.this).getLong(this.zzaXC, 0L);
    }
    
    @WorkerThread
    public void zzbq(final String s) {
        this.zzf(s, 1L);
    }
    
    @WorkerThread
    public void zzf(String s, final long n) {
        zzt.this.zzjk();
        if (this.zzlO() == 0L) {
            this.zzlL();
        }
        if (s == null) {
            s = "";
        }
        final long long1 = zzt.zza(zzt.this).getLong(this.zzaXD, 0L);
        if (long1 <= 0L) {
            final SharedPreferences$Editor edit = zzt.zza(zzt.this).edit();
            edit.putString(this.zzaXE, s);
            edit.putLong(this.zzaXD, n);
            edit.apply();
            return;
        }
        int n2;
        if ((Long.MAX_VALUE & zzt.zzb(zzt.this).nextLong()) < n * (Long.MAX_VALUE / (long1 + n))) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        final SharedPreferences$Editor edit2 = zzt.zza(zzt.this).edit();
        if (n2 != 0) {
            edit2.putString(this.zzaXE, s);
        }
        edit2.putLong(this.zzaXD, long1 + n);
        edit2.apply();
    }
    
    @WorkerThread
    public Pair<String, Long> zzlN() {
        zzt.this.zzjk();
        final long zzlM = this.zzlM();
        if (zzlM < this.zzTl) {
            return null;
        }
        if (zzlM > 2L * this.zzTl) {
            this.zzlL();
            return null;
        }
        final String string = zzt.zzc(zzt.this).getString(this.zzaXE, (String)null);
        final long long1 = zzt.zzc(zzt.this).getLong(this.zzaXD, 0L);
        this.zzlL();
        if (string == null || long1 <= 0L) {
            return zzt.zzaXh;
        }
        return (Pair<String, Long>)new Pair((Object)string, (Object)long1);
    }
}
