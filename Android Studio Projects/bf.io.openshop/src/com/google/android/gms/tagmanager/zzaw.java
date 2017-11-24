package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.*;
import com.google.android.gms.internal.*;

class zzaw extends zzak
{
    private static final String ID;
    private static final String zzbhD;
    private final Context context;
    
    static {
        ID = zzad.zzcc.toString();
        zzbhD = zzae.zzex.toString();
    }
    
    public zzaw(final Context context) {
        super(zzaw.ID, new String[0]);
        this.context = context;
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        String zzg;
        if (map.get(zzaw.zzbhD) != null) {
            zzg = zzdf.zzg(map.get(zzaw.zzbhD));
        }
        else {
            zzg = null;
        }
        final String zzm = zzax.zzm(this.context, zzg);
        if (zzm != null) {
            return zzdf.zzR(zzm);
        }
        return zzdf.zzHF();
    }
}
