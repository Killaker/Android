package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;

private class zzb implements zzbf<zzrq.zza>
{
    @Override
    public void zzGk() {
    }
    
    public void zza(final zzrq.zza zza) {
        zzaf.zzj zzbme;
        if (zza.zzbme != null) {
            zzbme = zza.zzbme;
        }
        else {
            final zzaf.zzf zzju = zza.zzju;
            zzbme = new zzaf.zzj();
            zzbme.zzju = zzju;
            zzbme.zzjt = null;
            zzbme.zzjv = zzju.version;
        }
        zzp.zza(zzp.this, zzbme, zza.zzbmd, true);
    }
    
    @Override
    public void zza(final zzbf.zza zza) {
        if (!zzp.zzd(zzp.this)) {
            zzp.zza(zzp.this, 0L);
        }
    }
}
