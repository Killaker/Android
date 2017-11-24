package com.google.android.gms.analytics.internal;

import android.content.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.analytics.*;

public class zzg
{
    private final Context zzQC;
    private final Context zzsa;
    
    public zzg(final Context context) {
        zzx.zzz(context);
        final Context applicationContext = context.getApplicationContext();
        zzx.zzb(applicationContext, "Application context can't be null");
        this.zzsa = applicationContext;
        this.zzQC = applicationContext;
    }
    
    public Context getApplicationContext() {
        return this.zzsa;
    }
    
    protected zzu zza(final zzf zzf) {
        return new zzu(zzf);
    }
    
    protected com.google.android.gms.measurement.zzg zzab(final Context context) {
        return com.google.android.gms.measurement.zzg.zzaS(context);
    }
    
    protected zzk zzb(final zzf zzf) {
        return new zzk(zzf);
    }
    
    protected zza zzc(final zzf zzf) {
        return new zza(zzf);
    }
    
    protected zzn zzd(final zzf zzf) {
        return new zzn(zzf);
    }
    
    protected zzan zze(final zzf zzf) {
        return new zzan(zzf);
    }
    
    protected zzaf zzf(final zzf zzf) {
        return new zzaf(zzf);
    }
    
    protected zzr zzg(final zzf zzf) {
        return new zzr(zzf);
    }
    
    protected zzmq zzh(final zzf zzf) {
        return zzmt.zzsc();
    }
    
    protected GoogleAnalytics zzi(final zzf zzf) {
        return new GoogleAnalytics(zzf);
    }
    
    zzl zzj(final zzf zzf) {
        return new zzl(zzf, this);
    }
    
    public Context zzjx() {
        return this.zzQC;
    }
    
    zzag zzk(final zzf zzf) {
        return new zzag(zzf);
    }
    
    protected zzb zzl(final zzf zzf) {
        return new zzb(zzf, this);
    }
    
    public zzj zzm(final zzf zzf) {
        return new zzj(zzf);
    }
    
    public zzah zzn(final zzf zzf) {
        return new zzah(zzf);
    }
    
    public zzi zzo(final zzf zzf) {
        return new zzi(zzf);
    }
    
    public zzv zzp(final zzf zzf) {
        return new zzv(zzf);
    }
    
    public zzai zzq(final zzf zzf) {
        return new zzai(zzf);
    }
}
