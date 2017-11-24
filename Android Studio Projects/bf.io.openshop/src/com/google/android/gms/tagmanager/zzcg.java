package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.*;
import com.google.android.gms.internal.*;
import android.util.*;
import android.view.*;

class zzcg extends zzak
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = zzad.zzbR.toString();
    }
    
    public zzcg(final Context mContext) {
        super(zzcg.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return zzdf.zzR(displayMetrics.widthPixels + "x" + displayMetrics.heightPixels);
    }
}
