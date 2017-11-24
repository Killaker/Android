package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;

class zzai extends zzak
{
    private static final String ID;
    private final zzcp zzbhO;
    
    static {
        ID = zzad.zzbI.toString();
    }
    
    public zzai(final zzcp zzbhO) {
        super(zzai.ID, new String[0]);
        this.zzbhO = zzbhO;
    }
    
    @Override
    public boolean zzFW() {
        return false;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final String zzHe = this.zzbhO.zzHe();
        if (zzHe == null) {
            return zzdf.zzHF();
        }
        return zzdf.zzR(zzHe);
    }
}
