package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.*;
import com.google.android.gms.internal.*;
import android.content.pm.*;

class zzh extends zzak
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = zzad.zzbv.toString();
    }
    
    public zzh(final Context mContext) {
        super(zzh.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        try {
            return zzdf.zzR(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode);
        }
        catch (PackageManager$NameNotFoundException ex) {
            zzbg.e("Package name " + this.mContext.getPackageName() + " not found. " + ex.getMessage());
            return zzdf.zzHF();
        }
    }
}
