package com.google.android.gms.common.api.internal;

import android.os.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.common.*;

public interface zzk
{
    void begin();
    
    void connect();
    
    boolean disconnect();
    
    void onConnected(final Bundle p0);
    
    void onConnectionSuspended(final int p0);
    
     <A extends Api.zzb, R extends Result, T extends zza.zza<R, A>> T zza(final T p0);
    
    void zza(final ConnectionResult p0, final Api<?> p1, final int p2);
    
     <A extends Api.zzb, T extends zza.zza<? extends Result, A>> T zzb(final T p0);
}
