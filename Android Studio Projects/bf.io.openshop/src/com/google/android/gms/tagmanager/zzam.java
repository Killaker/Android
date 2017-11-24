package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;

class zzam extends zzbv
{
    private static final String ID;
    
    static {
        ID = zzad.zzcG.toString();
    }
    
    public zzam() {
        super(zzam.ID);
    }
    
    @Override
    protected boolean zza(final zzde zzde, final zzde zzde2, final Map<String, zzag.zza> map) {
        return zzde.zza(zzde2) >= 0;
    }
}
