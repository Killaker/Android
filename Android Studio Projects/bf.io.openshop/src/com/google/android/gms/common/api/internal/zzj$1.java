package com.google.android.gms.common.api.internal;

class zzj$1 implements zzd {
    @Override
    public void zzc(final zze<?> zze) {
        zzj.this.zzahW.remove(zze);
        if (zze.zzpa() != null && zzj.zza(zzj.this) != null) {
            zzj.zza(zzj.this).remove(zze.zzpa());
        }
    }
}