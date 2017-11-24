package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;

class zzv extends zzak
{
    private static final String ID;
    private static final String NAME;
    private static final String zzbiA;
    private final DataLayer zzbhN;
    
    static {
        ID = zzad.zzbz.toString();
        NAME = zzae.zzgo.toString();
        zzbiA = zzae.zzeY.toString();
    }
    
    public zzv(final DataLayer zzbhN) {
        super(zzv.ID, new String[] { zzv.NAME });
        this.zzbhN = zzbhN;
    }
    
    @Override
    public boolean zzFW() {
        return false;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final Object value = this.zzbhN.get(zzdf.zzg(map.get(zzv.NAME)));
        if (value != null) {
            return zzdf.zzR(value);
        }
        final zzag.zza zza = map.get(zzv.zzbiA);
        if (zza != null) {
            return zza;
        }
        return zzdf.zzHF();
    }
}
