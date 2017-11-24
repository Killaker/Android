package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.*;
import android.util.*;
import android.text.*;
import android.content.*;
import com.google.android.gms.analytics.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.measurement.*;

public class zzc
{
    private final zzf zzQj;
    
    protected zzc(final zzf zzQj) {
        zzx.zzz(zzQj);
        this.zzQj = zzQj;
    }
    
    private void zza(final int n, final String s, final Object o, final Object o2, final Object o3) {
        final zzf zzQj = this.zzQj;
        zzaf zzjy = null;
        if (zzQj != null) {
            zzjy = this.zzQj.zzjy();
        }
        if (zzjy != null) {
            zzjy.zza(n, s, o, o2, o3);
        }
        else {
            final String s2 = zzy.zzRL.get();
            if (Log.isLoggable(s2, n)) {
                Log.println(n, s2, zzc(s, o, o2, o3));
            }
        }
    }
    
    protected static String zzc(String s, final Object o, final Object o2, final Object o3) {
        if (s == null) {
            s = "";
        }
        final String zzj = zzj(o);
        final String zzj2 = zzj(o2);
        final String zzj3 = zzj(o3);
        final StringBuilder sb = new StringBuilder();
        String s2 = "";
        if (!TextUtils.isEmpty((CharSequence)s)) {
            sb.append(s);
            s2 = ": ";
        }
        if (!TextUtils.isEmpty((CharSequence)zzj)) {
            sb.append(s2);
            sb.append(zzj);
            s2 = ", ";
        }
        if (!TextUtils.isEmpty((CharSequence)zzj2)) {
            sb.append(s2);
            sb.append(zzj2);
            s2 = ", ";
        }
        if (!TextUtils.isEmpty((CharSequence)zzj3)) {
            sb.append(s2);
            sb.append(zzj3);
        }
        return sb.toString();
    }
    
    private static String zzj(final Object o) {
        if (o == null) {
            return "";
        }
        if (o instanceof String) {
            return (String)o;
        }
        if (o instanceof Boolean) {
            String s;
            if (o == Boolean.TRUE) {
                s = "true";
            }
            else {
                s = "false";
            }
            return s;
        }
        if (o instanceof Throwable) {
            return ((Throwable)o).toString();
        }
        return o.toString();
    }
    
    protected Context getContext() {
        return this.zzQj.getContext();
    }
    
    public void zza(final String s, final Object o) {
        this.zza(2, s, o, null, null);
    }
    
    public void zza(final String s, final Object o, final Object o2) {
        this.zza(2, s, o, o2, null);
    }
    
    public void zza(final String s, final Object o, final Object o2, final Object o3) {
        this.zza(3, s, o, o2, o3);
    }
    
    public void zzb(final String s, final Object o) {
        this.zza(3, s, o, null, null);
    }
    
    public void zzb(final String s, final Object o, final Object o2) {
        this.zza(3, s, o, o2, null);
    }
    
    public void zzb(final String s, final Object o, final Object o2, final Object o3) {
        this.zza(5, s, o, o2, o3);
    }
    
    public void zzbd(final String s) {
        this.zza(2, s, null, null, null);
    }
    
    public void zzbe(final String s) {
        this.zza(3, s, null, null, null);
    }
    
    public void zzbf(final String s) {
        this.zza(4, s, null, null, null);
    }
    
    public void zzbg(final String s) {
        this.zza(5, s, null, null, null);
    }
    
    public void zzbh(final String s) {
        this.zza(6, s, null, null, null);
    }
    
    public void zzc(final String s, final Object o) {
        this.zza(4, s, o, null, null);
    }
    
    public void zzc(final String s, final Object o, final Object o2) {
        this.zza(5, s, o, o2, null);
    }
    
    public void zzd(final String s, final Object o) {
        this.zza(5, s, o, null, null);
    }
    
    public void zzd(final String s, final Object o, final Object o2) {
        this.zza(6, s, o, o2, null);
    }
    
    public void zze(final String s, final Object o) {
        this.zza(6, s, o, null, null);
    }
    
    public boolean zzhp() {
        return Log.isLoggable((String)zzy.zzRL.get(), 2);
    }
    
    public GoogleAnalytics zziC() {
        return this.zzQj.zzjz();
    }
    
    protected zzb zziH() {
        return this.zzQj.zziH();
    }
    
    protected zzan zziI() {
        return this.zzQj.zziI();
    }
    
    public zzf zzji() {
        return this.zzQj;
    }
    
    protected void zzjj() {
        if (this.zzjn().zzkr()) {
            throw new IllegalStateException("Call only supported on the client side");
        }
    }
    
    protected void zzjk() {
        this.zzQj.zzjk();
    }
    
    protected zzmq zzjl() {
        return this.zzQj.zzjl();
    }
    
    protected zzaf zzjm() {
        return this.zzQj.zzjm();
    }
    
    protected zzr zzjn() {
        return this.zzQj.zzjn();
    }
    
    protected zzg zzjo() {
        return this.zzQj.zzjo();
    }
    
    protected zzv zzjp() {
        return this.zzQj.zzjp();
    }
    
    protected zzai zzjq() {
        return this.zzQj.zzjq();
    }
    
    protected zzn zzjr() {
        return this.zzQj.zzjC();
    }
    
    protected zza zzjs() {
        return this.zzQj.zzjB();
    }
    
    protected zzk zzjt() {
        return this.zzQj.zzjt();
    }
    
    protected zzu zzju() {
        return this.zzQj.zzju();
    }
}
