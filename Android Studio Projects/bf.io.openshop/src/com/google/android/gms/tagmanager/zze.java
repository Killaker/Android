package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.*;
import com.google.android.gms.internal.*;

class zze extends zzak
{
    private static final String ID;
    private static final String zzbhD;
    private static final String zzbhE;
    private final Context context;
    
    static {
        ID = zzad.zzbX.toString();
        zzbhD = zzae.zzex.toString();
        zzbhE = zzae.zzeA.toString();
    }
    
    public zze(final Context context) {
        super(zze.ID, new String[] { zze.zzbhE });
        this.context = context;
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final zzag.zza zza = map.get(zze.zzbhE);
        if (zza == null) {
            return zzdf.zzHF();
        }
        final String zzg = zzdf.zzg(zza);
        final zzag.zza zza2 = map.get(zze.zzbhD);
        String zzg2;
        if (zza2 != null) {
            zzg2 = zzdf.zzg(zza2);
        }
        else {
            zzg2 = null;
        }
        final String zzf = zzax.zzf(this.context, zzg, zzg2);
        if (zzf != null) {
            return zzdf.zzR(zzf);
        }
        return zzdf.zzHF();
    }
}
