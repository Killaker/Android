package com.google.android.gms.common.api.internal;

import java.util.concurrent.atomic.*;
import com.google.android.gms.common.internal.*;
import android.app.*;
import android.os.*;
import com.google.android.gms.common.api.*;

public abstract static class zza<R extends Result, A extends Api.zzb> extends zzb<R> implements com.google.android.gms.common.api.internal.zza.zzb<R>, zze<A>
{
    private final Api.zzc<A> zzaeE;
    private AtomicReference<zzd> zzagH;
    
    protected zza(final Api.zzc<A> zzc, final GoogleApiClient googleApiClient) {
        super(zzx.zzb(googleApiClient, "GoogleApiClient must not be null"));
        this.zzagH = new AtomicReference<zzd>();
        this.zzaeE = (Api.zzc<A>)zzx.zzz((Api.zzc)zzc);
    }
    
    private void zza(final RemoteException ex) {
        this.zzw(new Status(8, ex.getLocalizedMessage(), null));
    }
    
    protected abstract void zza(final A p0) throws RemoteException;
    
    @Override
    public void zza(final zzd zzd) {
        this.zzagH.set(zzd);
    }
    
    @Override
    public final void zzb(final A a) throws DeadObjectException {
        try {
            this.zza(a);
        }
        catch (DeadObjectException ex) {
            this.zza((RemoteException)ex);
            throw ex;
        }
        catch (RemoteException ex2) {
            this.zza(ex2);
        }
    }
    
    @Override
    public final Api.zzc<A> zzoR() {
        return this.zzaeE;
    }
    
    @Override
    public void zzpe() {
        this.setResultCallback(null);
    }
    
    @Override
    protected void zzpf() {
        final zzd zzd = this.zzagH.getAndSet(null);
        if (zzd != null) {
            zzd.zzc(this);
        }
    }
    
    @Override
    public final void zzw(final Status status) {
        zzx.zzb(!status.isSuccess(), (Object)"Failed result must not be success");
        this.zza(this.zzc(status));
    }
}
