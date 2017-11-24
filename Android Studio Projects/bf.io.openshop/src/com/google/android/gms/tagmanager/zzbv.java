package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.util.*;

abstract class zzbv extends zzca
{
    public zzbv(final String s) {
        super(s);
    }
    
    @Override
    protected boolean zza(final zzag.zza zza, final zzag.zza zza2, final Map<String, zzag.zza> map) {
        final zzde zzh = zzdf.zzh(zza);
        final zzde zzh2 = zzdf.zzh(zza2);
        return zzh != zzdf.zzHD() && zzh2 != zzdf.zzHD() && this.zza(zzh, zzh2, map);
    }
    
    protected abstract boolean zza(final zzde p0, final zzde p1, final Map<String, zzag.zza> p2);
}
