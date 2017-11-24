package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;

class zzcc extends zzak
{
    private static final String ID;
    private static final String zzbjY;
    private static final String zzbjZ;
    
    static {
        ID = zzad.zzbP.toString();
        zzbjY = zzae.zzgm.toString();
        zzbjZ = zzae.zzgk.toString();
    }
    
    public zzcc() {
        super(zzcc.ID, new String[0]);
    }
    
    @Override
    public boolean zzFW() {
        return false;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final zzag.zza zza = map.get(zzcc.zzbjY);
        final zzag.zza zza2 = map.get(zzcc.zzbjZ);
        Label_0124: {
            if (zza == null || zza == zzdf.zzHF() || zza2 == null || zza2 == zzdf.zzHF()) {
                break Label_0124;
            }
            final zzde zzh = zzdf.zzh(zza);
            final zzde zzh2 = zzdf.zzh(zza2);
            if (zzh == zzdf.zzHD() || zzh2 == zzdf.zzHD()) {
                break Label_0124;
            }
            final double doubleValue = zzh.doubleValue();
            final double doubleValue2 = zzh2.doubleValue();
            if (doubleValue > doubleValue2) {
                break Label_0124;
            }
            final double n = doubleValue;
            return zzdf.zzR(Math.round(n + Math.random() * (doubleValue2 - n)));
        }
        final double doubleValue2 = 2.147483647E9;
        final double n = 0.0;
        return zzdf.zzR(Math.round(n + Math.random() * (doubleValue2 - n)));
    }
}
