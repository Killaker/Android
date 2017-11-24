package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;

class zzq extends zzak
{
    private static final String ID;
    private final String zzadc;
    
    static {
        ID = zzad.zzbA.toString();
    }
    
    public zzq(final String zzadc) {
        super(zzq.ID, new String[0]);
        this.zzadc = zzadc;
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        if (this.zzadc == null) {
            return zzdf.zzHF();
        }
        return zzdf.zzR(this.zzadc);
    }
}
