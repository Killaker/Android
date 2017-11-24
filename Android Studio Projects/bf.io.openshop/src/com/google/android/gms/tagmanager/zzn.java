package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;

class zzn extends zzak
{
    private static final String ID;
    private static final String VALUE;
    
    static {
        ID = zzad.zzbx.toString();
        VALUE = zzae.zzih.toString();
    }
    
    public zzn() {
        super(zzn.ID, new String[] { zzn.VALUE });
    }
    
    public static String zzFZ() {
        return zzn.ID;
    }
    
    public static String zzGa() {
        return zzn.VALUE;
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        return map.get(zzn.VALUE);
    }
}
