package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;

class zzae extends zzcz
{
    private static final String ID;
    
    static {
        ID = zzad.zzcA.toString();
    }
    
    public zzae() {
        super(zzae.ID);
    }
    
    @Override
    protected boolean zza(final String s, final String s2, final Map<String, zzag.zza> map) {
        return s.endsWith(s2);
    }
}
