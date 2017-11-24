package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.*;
import com.google.android.gms.internal.*;

class zzc extends zzak
{
    private static final String ID;
    private final zza zzbhC;
    
    static {
        ID = zzad.zzbs.toString();
    }
    
    public zzc(final Context context) {
        this(zza.zzaW(context));
    }
    
    zzc(final zza zzbhC) {
        super(zzc.ID, new String[0]);
        this.zzbhC = zzbhC;
    }
    
    @Override
    public boolean zzFW() {
        return false;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        return zzdf.zzR(!this.zzbhC.isLimitAdTrackingEnabled());
    }
}
