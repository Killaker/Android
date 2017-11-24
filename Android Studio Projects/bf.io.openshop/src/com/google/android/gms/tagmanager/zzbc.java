package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;

class zzbc extends zzbv
{
    private static final String ID;
    
    static {
        ID = zzad.zzcE.toString();
    }
    
    public zzbc() {
        super(zzbc.ID);
    }
    
    @Override
    protected boolean zza(final zzde zzde, final zzde zzde2, final Map<String, zzag.zza> map) {
        return zzde.zza(zzde2) <= 0;
    }
}
