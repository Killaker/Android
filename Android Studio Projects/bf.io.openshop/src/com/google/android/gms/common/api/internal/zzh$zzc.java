package com.google.android.gms.common.api.internal;

import android.support.annotation.*;
import java.util.*;
import com.google.android.gms.common.api.*;

private class zzc extends zzf
{
    private final ArrayList<Api.zzb> zzahH;
    
    public zzc(final ArrayList<Api.zzb> zzahH) {
        this.zzahH = zzahH;
    }
    
    @WorkerThread
    public void zzpt() {
        zzh.zzd(zzh.this).zzagW.zzahU = (Set<Scope>)zzh.zzg(zzh.this);
        final Iterator<Api.zzb> iterator = this.zzahH.iterator();
        while (iterator.hasNext()) {
            iterator.next().zza(zzh.zzh(zzh.this), zzh.zzd(zzh.this).zzagW.zzahU);
        }
    }
}
