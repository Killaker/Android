package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;
import android.os.*;

class zzab extends zzak
{
    private static final String ID;
    
    static {
        ID = zzad.zzbC.toString();
    }
    
    public zzab() {
        super(zzab.ID, new String[0]);
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final String manufacturer = Build.MANUFACTURER;
        String s = Build.MODEL;
        if (!s.startsWith(manufacturer) && !manufacturer.equals("unknown")) {
            s = manufacturer + " " + s;
        }
        return zzdf.zzR(s);
    }
}
