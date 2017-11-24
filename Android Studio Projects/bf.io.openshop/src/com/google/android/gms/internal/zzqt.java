package com.google.android.gms.internal;

import android.content.*;
import android.util.*;
import android.app.*;

@Deprecated
public class zzqt implements zza
{
    private final zzqu zzbdw;
    private boolean zzbdx;
    
    public zzqt(final Context context, final int n) {
        this(context, n, null);
    }
    
    public zzqt(final Context context, final int n, final String s) {
        this(context, n, s, null, true);
    }
    
    public zzqt(final Context context, final int n, final String s, final String s2, final boolean b) {
        String name;
        if (context != context.getApplicationContext()) {
            name = context.getClass().getName();
        }
        else {
            name = "OneTimePlayLogger";
        }
        this.zzbdw = new zzqu(context, n, s, s2, (zzqu.zza)this, b, name);
        this.zzbdx = true;
    }
    
    private void zzER() {
        if (!this.zzbdx) {
            throw new IllegalStateException("Cannot reuse one-time logger after sending.");
        }
    }
    
    public void send() {
        this.zzER();
        this.zzbdw.start();
        this.zzbdx = false;
    }
    
    @Override
    public void zzES() {
        this.zzbdw.stop();
    }
    
    @Override
    public void zzET() {
        Log.w("OneTimePlayLogger", "logger connection failed");
    }
    
    public void zza(final String s, final byte[] array, final String... array2) {
        this.zzER();
        this.zzbdw.zzb(s, array, array2);
    }
    
    @Override
    public void zzc(final PendingIntent pendingIntent) {
        Log.w("OneTimePlayLogger", "logger connection failed: " + pendingIntent);
    }
}
