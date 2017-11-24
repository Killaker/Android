package com.google.android.gms.common.internal;

import android.util.*;
import android.content.*;
import com.google.android.gms.internal.*;

public final class zzo
{
    public static final int zzaml;
    private static final String zzamm;
    private final String zzamn;
    private final String zzamo;
    
    static {
        zzaml = 23 - " PII_LOG".length();
        zzamm = null;
    }
    
    public zzo(final String s) {
        this(s, zzo.zzamm);
    }
    
    public zzo(final String zzamn, final String zzamo) {
        zzx.zzb(zzamn, "log tag cannot be null");
        zzx.zzb(zzamn.length() <= 23, "tag \"%s\" is longer than the %d character maximum", zzamn, 23);
        this.zzamn = zzamn;
        if (zzamo == null || zzamo.length() <= 0) {
            this.zzamo = zzo.zzamm;
            return;
        }
        this.zzamo = zzamo;
    }
    
    private String zzcK(final String s) {
        if (this.zzamo == null) {
            return s;
        }
        return this.zzamo.concat(s);
    }
    
    public void zzA(final String s, final String s2) {
        if (this.zzbU(6)) {
            Log.e(s, this.zzcK(s2));
        }
    }
    
    public void zza(final Context context, final String s, final String s2, final Throwable t) {
        final StackTraceElement[] stackTrace = t.getStackTrace();
        final StringBuilder sb = new StringBuilder();
        for (int n = 0; n < stackTrace.length && n < 2; ++n) {
            sb.append(stackTrace[n].toString());
            sb.append("\n");
        }
        final zzqt zzqt = new zzqt(context, 10);
        zzqt.zza("GMS_WTF", null, "GMS_WTF", sb.toString());
        zzqt.send();
        if (this.zzbU(7)) {
            Log.e(s, this.zzcK(s2), t);
            Log.wtf(s, this.zzcK(s2), t);
        }
    }
    
    public void zza(final String s, final String s2, final Throwable t) {
        if (this.zzbU(4)) {
            Log.i(s, this.zzcK(s2), t);
        }
    }
    
    public void zzb(final String s, final String s2, final Throwable t) {
        if (this.zzbU(5)) {
            Log.w(s, this.zzcK(s2), t);
        }
    }
    
    public boolean zzbU(final int n) {
        return Log.isLoggable(this.zzamn, n);
    }
    
    public void zzc(final String s, final String s2, final Throwable t) {
        if (this.zzbU(6)) {
            Log.e(s, this.zzcK(s2), t);
        }
    }
    
    public void zzy(final String s, final String s2) {
        if (this.zzbU(3)) {
            Log.d(s, this.zzcK(s2));
        }
    }
    
    public void zzz(final String s, final String s2) {
        if (this.zzbU(5)) {
            Log.w(s, this.zzcK(s2));
        }
    }
}
