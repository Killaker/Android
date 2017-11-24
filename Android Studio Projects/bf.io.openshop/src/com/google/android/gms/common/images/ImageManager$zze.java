package com.google.android.gms.common.images;

import android.content.*;
import android.annotation.*;
import android.content.res.*;

@TargetApi(14)
private static final class zze implements ComponentCallbacks2
{
    private final zzb zzajE;
    
    public zze(final zzb zzajE) {
        this.zzajE = zzajE;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
    }
    
    public void onLowMemory() {
        this.zzajE.evictAll();
    }
    
    public void onTrimMemory(final int n) {
        if (n >= 60) {
            this.zzajE.evictAll();
        }
        else if (n >= 20) {
            this.zzajE.trimToSize(this.zzajE.size() / 2);
        }
    }
}
