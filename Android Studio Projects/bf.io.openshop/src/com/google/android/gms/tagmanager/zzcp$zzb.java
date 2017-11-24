package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;

private static class zzb
{
    private zzbw<zzag.zza> zzbkH;
    private zzag.zza zzbkI;
    
    public zzb(final zzbw<zzag.zza> zzbkH, final zzag.zza zzbkI) {
        this.zzbkH = zzbkH;
        this.zzbkI = zzbkI;
    }
    
    public int getSize() {
        final int cachedSize = this.zzbkH.getObject().getCachedSize();
        int cachedSize2;
        if (this.zzbkI == null) {
            cachedSize2 = 0;
        }
        else {
            cachedSize2 = this.zzbkI.getCachedSize();
        }
        return cachedSize2 + cachedSize;
    }
    
    public zzbw<zzag.zza> zzHg() {
        return this.zzbkH;
    }
    
    public zzag.zza zzHh() {
        return this.zzbkI;
    }
}
