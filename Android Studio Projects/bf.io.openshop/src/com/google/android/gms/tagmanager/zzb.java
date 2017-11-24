package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.*;
import com.google.android.gms.internal.*;

class zzb extends zzak
{
    private static final String ID;
    private final zza zzbhC;
    
    static {
        ID = zzad.zzbr.toString();
    }
    
    public zzb(final Context context) {
        this(zza.zzaW(context));
    }
    
    zzb(final zza zzbhC) {
        super(zzb.ID, new String[0]);
        this.zzbhC = zzbhC;
    }
    
    @Override
    public boolean zzFW() {
        return false;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final String zzFQ = this.zzbhC.zzFQ();
        if (zzFQ == null) {
            return zzdf.zzHF();
        }
        return zzdf.zzR(zzFQ);
    }
}
