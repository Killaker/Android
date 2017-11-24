package com.google.android.gms.common.api.internal;

import android.os.*;
import com.google.android.gms.common.*;
import java.util.*;
import com.google.android.gms.common.api.*;

public class zzg implements zzk
{
    private final zzl zzahj;
    private boolean zzahk;
    
    public zzg(final zzl zzahj) {
        this.zzahk = false;
        this.zzahj = zzahj;
    }
    
    private <A extends Api.zzb> void zza(final zzj.zze<A> zze) throws DeadObjectException {
        this.zzahj.zzagW.zzb((zzj.zze<Api.zzb>)zze);
        final Api.zzb zza = this.zzahj.zzagW.zza((Api.zzc<A>)zze.zzoR());
        if (!zza.isConnected() && this.zzahj.zzaio.containsKey(zze.zzoR())) {
            zze.zzw(new Status(17));
            return;
        }
        zze.zzb(zza);
    }
    
    @Override
    public void begin() {
    }
    
    @Override
    public void connect() {
        if (this.zzahk) {
            this.zzahk = false;
            this.zzahj.zza((zzl.zza)new zzl.zza(this) {
                public void zzpt() {
                    zzg.this.zzahj.zzais.zzi(null);
                }
            });
        }
    }
    
    @Override
    public boolean disconnect() {
        if (this.zzahk) {
            return false;
        }
        if (this.zzahj.zzagW.zzpG()) {
            this.zzahk = true;
            final Iterator<zzx> iterator = this.zzahj.zzagW.zzaia.iterator();
            while (iterator.hasNext()) {
                iterator.next().zzpU();
            }
            return false;
        }
        this.zzahj.zzh(null);
        return true;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.zzahj.zzh(null);
        this.zzahj.zzais.zzc(n, this.zzahk);
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends zza.zza<R, A>> T zza(final T t) {
        return this.zzb(t);
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
    }
    
    @Override
    public <A extends Api.zzb, T extends zza.zza<? extends Result, A>> T zzb(final T t) {
        try {
            this.zza((zzj.zze<Api.zzb>)t);
            return t;
        }
        catch (DeadObjectException ex) {
            this.zzahj.zza((zzl.zza)new zzl.zza(this) {
                public void zzpt() {
                    zzg.this.onConnectionSuspended(1);
                }
            });
            return t;
        }
    }
    
    void zzps() {
        if (this.zzahk) {
            this.zzahk = false;
            this.zzahj.zzagW.zzaa(false);
            this.disconnect();
        }
    }
}
