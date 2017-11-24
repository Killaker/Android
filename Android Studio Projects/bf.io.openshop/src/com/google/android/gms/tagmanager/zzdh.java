package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;

class zzdh extends zzak
{
    private static final String ID;
    private static final String zzbiQ;
    
    static {
        ID = zzad.zzci.toString();
        zzbiQ = zzae.zzdV.toString();
    }
    
    public zzdh() {
        super(zzdh.ID, new String[] { zzdh.zzbiQ });
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        return zzdf.zzR(zzdf.zzg(map.get(zzdh.zzbiQ)).toUpperCase());
    }
}
