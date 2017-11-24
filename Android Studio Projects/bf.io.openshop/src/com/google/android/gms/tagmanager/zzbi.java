package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;

class zzbi extends zzak
{
    private static final String ID;
    private static final String zzbiQ;
    
    static {
        ID = zzad.zzch.toString();
        zzbiQ = zzae.zzdV.toString();
    }
    
    public zzbi() {
        super(zzbi.ID, new String[] { zzbi.zzbiQ });
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        return zzdf.zzR(zzdf.zzg(map.get(zzbi.zzbiQ)).toLowerCase());
    }
}
