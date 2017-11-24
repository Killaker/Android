package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.*;
import com.google.android.gms.internal.*;

class zzf extends zzak
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = zzad.zzbt.toString();
    }
    
    public zzf(final Context mContext) {
        super(zzf.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        return zzdf.zzR(this.mContext.getPackageName());
    }
}
