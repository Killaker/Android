package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.*;
import com.google.android.gms.internal.*;
import android.content.pm.*;

class zzg extends zzak
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = zzad.zzbu.toString();
    }
    
    public zzg(final Context mContext) {
        super(zzg.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        try {
            final PackageManager packageManager = this.mContext.getPackageManager();
            return zzdf.zzR(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mContext.getPackageName(), 0)).toString());
        }
        catch (PackageManager$NameNotFoundException ex) {
            zzbg.zzb("App name is not found.", (Throwable)ex);
            return zzdf.zzHF();
        }
    }
}
