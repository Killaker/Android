package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.util.*;

public abstract class zzca extends zzak
{
    private static final String zzbiQ;
    private static final String zzbjO;
    
    static {
        zzbiQ = zzae.zzdV.toString();
        zzbjO = zzae.zzdW.toString();
    }
    
    public zzca(final String s) {
        super(s, new String[] { zzca.zzbiQ, zzca.zzbjO });
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final Iterator<zzag.zza> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == zzdf.zzHF()) {
                return zzdf.zzR(false);
            }
        }
        final zzag.zza zza = map.get(zzca.zzbiQ);
        final zzag.zza zza2 = map.get(zzca.zzbjO);
        return zzdf.zzR(zza != null && zza2 != null && this.zza(zza, zza2, map));
    }
    
    protected abstract boolean zza(final zzag.zza p0, final zzag.zza p1, final Map<String, zzag.zza> p2);
}
