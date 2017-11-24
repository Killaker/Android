package com.google.android.gms.common.api.internal;

import java.util.*;
import android.os.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.common.*;

public class zzi implements zzk
{
    private final zzl zzahj;
    
    public zzi(final zzl zzahj) {
        this.zzahj = zzahj;
    }
    
    @Override
    public void begin() {
        this.zzahj.zzpM();
        this.zzahj.zzagW.zzahU = Collections.emptySet();
    }
    
    @Override
    public void connect() {
        this.zzahj.zzpK();
    }
    
    @Override
    public boolean disconnect() {
        return true;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends zza.zza<R, A>> T zza(final T t) {
        this.zzahj.zzagW.zzahN.add(t);
        return t;
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
    }
    
    @Override
    public <A extends Api.zzb, T extends zza.zza<? extends Result, A>> T zzb(final T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
