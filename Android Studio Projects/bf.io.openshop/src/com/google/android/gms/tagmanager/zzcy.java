package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;

class zzcy extends zzcz
{
    private static final String ID;
    
    static {
        ID = zzad.zzcz.toString();
    }
    
    public zzcy() {
        super(zzcy.ID);
    }
    
    @Override
    protected boolean zza(final String s, final String s2, final Map<String, zzag.zza> map) {
        return s.startsWith(s2);
    }
}
