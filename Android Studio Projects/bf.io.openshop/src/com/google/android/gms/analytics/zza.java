package com.google.android.gms.analytics;

import com.google.android.gms.internal.*;
import android.text.*;
import com.google.android.gms.common.internal.*;
import android.net.*;
import java.util.*;
import com.google.android.gms.measurement.*;

public class zza extends zzf<zza>
{
    private final com.google.android.gms.analytics.internal.zzf zzOK;
    private boolean zzOL;
    
    public zza(final com.google.android.gms.analytics.internal.zzf zzOK) {
        super(zzOK.zzjo(), zzOK.zzjl());
        this.zzOK = zzOK;
    }
    
    public void enableAdvertisingIdCollection(final boolean zzOL) {
        this.zzOL = zzOL;
    }
    
    @Override
    protected void zza(final zzc zzc) {
        final zzke zzke = zzc.zzf(zzke.class);
        if (TextUtils.isEmpty((CharSequence)zzke.getClientId())) {
            zzke.setClientId(this.zzOK.zzjC().zzkk());
        }
        if (this.zzOL && TextUtils.isEmpty((CharSequence)zzke.zziT())) {
            final com.google.android.gms.analytics.internal.zza zzjB = this.zzOK.zzjB();
            zzke.zzaY(zzjB.zziY());
            zzke.zzH(zzjB.zziU());
        }
    }
    
    public void zzaS(final String s) {
        zzx.zzcM(s);
        this.zzaT(s);
        this.zzAG().add(new zzb(this.zzOK, s));
    }
    
    public void zzaT(final String s) {
        final Uri zzaU = zzb.zzaU(s);
        final ListIterator<zzi> listIterator = this.zzAG().listIterator();
        while (listIterator.hasNext()) {
            if (zzaU.equals((Object)listIterator.next().zziA())) {
                listIterator.remove();
            }
        }
    }
    
    com.google.android.gms.analytics.internal.zzf zzix() {
        return this.zzOK;
    }
    
    @Override
    public zzc zziy() {
        final zzc zzAu = this.zzAF().zzAu();
        zzAu.zzb(this.zzOK.zzjt().zzjS());
        zzAu.zzb(this.zzOK.zzju().zzkZ());
        this.zzd(zzAu);
        return zzAu;
    }
}
