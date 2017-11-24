package com.google.android.gms.flags.impl;

import com.google.android.gms.internal.*;
import android.content.*;
import com.google.android.gms.dynamic.*;
import android.content.pm.*;

public class FlagProviderImpl extends zzpk.zza
{
    private boolean zzqA;
    private SharedPreferences zzvx;
    
    public FlagProviderImpl() {
        this.zzqA = false;
    }
    
    public boolean getBooleanFlagValue(final String s, final boolean b, final int n) {
        return zza.zza.zza(this.zzvx, s, b);
    }
    
    public int getIntFlagValue(final String s, final int n, final int n2) {
        return com.google.android.gms.flags.impl.zza.zzb.zza(this.zzvx, s, n);
    }
    
    public long getLongFlagValue(final String s, final long n, final int n2) {
        return zza.zzc.zza(this.zzvx, s, n);
    }
    
    public String getStringFlagValue(final String s, final String s2, final int n) {
        return com.google.android.gms.flags.impl.zza.zzd.zza(this.zzvx, s, s2);
    }
    
    public void init(final zzd zzd) {
        final Context context = zze.zzp(zzd);
        if (this.zzqA) {
            return;
        }
        try {
            this.zzvx = zzb.zzw(context.createPackageContext("com.google.android.gms", 0));
            this.zzqA = true;
        }
        catch (PackageManager$NameNotFoundException ex) {}
    }
}
