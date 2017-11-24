package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.util.*;

abstract class zzcz extends zzca
{
    public zzcz(final String s) {
        super(s);
    }
    
    @Override
    protected boolean zza(final zzag.zza zza, final zzag.zza zza2, final Map<String, zzag.zza> map) {
        final String zzg = zzdf.zzg(zza);
        final String zzg2 = zzdf.zzg(zza2);
        return zzg != zzdf.zzHE() && zzg2 != zzdf.zzHE() && this.zza(zzg, zzg2, map);
    }
    
    protected abstract boolean zza(final String p0, final String p1, final Map<String, zzag.zza> p2);
}
