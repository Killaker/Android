package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.common.api.internal.*;

private class zzc implements zzbf<zzaf.zzj>
{
    @Override
    public void zzGk() {
    }
    
    @Override
    public void zza(final zzbf.zza zza) {
        synchronized (zzp.this) {
            if (!zzp.this.isReady()) {
                if (zzp.zzb(zzp.this) != null) {
                    ((com.google.android.gms.common.api.internal.zzb<zzo>)zzp.this).zza(zzp.zzb(zzp.this));
                }
                else {
                    zzp.this.zza(zzp.this.zzbn(Status.zzagF));
                }
            }
            // monitorexit(this.zzbim)
            zzp.zza(zzp.this, 3600000L);
        }
    }
    
    public void zzb(final zzaf.zzj zzj) {
        synchronized (zzp.this) {
            if (zzj.zzju == null) {
                if (zzp.zze(zzp.this).zzju == null) {
                    zzbg.e("Current resource is null; network resource is also null");
                    zzp.zza(zzp.this, 3600000L);
                    return;
                }
                zzj.zzju = zzp.zze(zzp.this).zzju;
            }
            zzp.zza(zzp.this, zzj, zzp.zzc(zzp.this).currentTimeMillis(), false);
            zzbg.v("setting refresh time to current time: " + zzp.zzf(zzp.this));
            if (!zzp.zzg(zzp.this)) {
                zzp.zza(zzp.this, zzj);
            }
        }
    }
}
