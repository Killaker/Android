package com.google.android.gms.common.api.internal;

import java.lang.ref.*;
import com.google.android.gms.common.api.*;
import android.os.*;

private static class zzb implements IBinder$DeathRecipient, zzd
{
    private final WeakReference<zze<?>> zzaii;
    private final WeakReference<com.google.android.gms.common.api.zza> zzaij;
    private final WeakReference<IBinder> zzaik;
    
    private zzb(final zze zze, final com.google.android.gms.common.api.zza zza, final IBinder binder) {
        this.zzaij = new WeakReference<com.google.android.gms.common.api.zza>(zza);
        this.zzaii = new WeakReference<zze<?>>(zze);
        this.zzaik = new WeakReference<IBinder>(binder);
    }
    
    private void zzpI() {
        final zze zze = this.zzaii.get();
        final com.google.android.gms.common.api.zza zza = this.zzaij.get();
        if (zza != null && zze != null) {
            zza.remove(zze.zzpa());
        }
        final IBinder binder = this.zzaik.get();
        if (this.zzaik != null) {
            binder.unlinkToDeath((IBinder$DeathRecipient)this, 0);
        }
    }
    
    public void binderDied() {
        this.zzpI();
    }
    
    public void zzc(final zze<?> zze) {
        this.zzpI();
    }
}
