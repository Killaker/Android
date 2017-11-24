package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;

private static final class zza extends zzb.zza
{
    private final CancelableCallback zzaRO;
    
    zza(final CancelableCallback zzaRO) {
        this.zzaRO = zzaRO;
    }
    
    public void onCancel() {
        this.zzaRO.onCancel();
    }
    
    public void onFinish() {
        this.zzaRO.onFinish();
    }
}
