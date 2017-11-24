package com.google.android.gms.common.api.internal;

import android.os.*;
import com.google.android.gms.common.api.*;

interface zze<A extends Api.zzb>
{
    void cancel();
    
    boolean isReady();
    
    void zza(final zzd p0);
    
    void zzb(final A p0) throws DeadObjectException;
    
    Api.zzc<A> zzoR();
    
    Integer zzpa();
    
    void zzpe();
    
    void zzpg();
    
    void zzw(final Status p0);
    
    void zzx(final Status p0);
}
