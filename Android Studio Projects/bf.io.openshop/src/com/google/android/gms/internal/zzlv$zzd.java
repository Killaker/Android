package com.google.android.gms.internal;

import com.google.android.gms.clearcut.*;
import android.os.*;
import android.util.*;
import com.google.android.gms.common.api.*;

final class zzd extends zzc<Status>
{
    private final LogEventParcelable zzafx;
    
    zzd(final LogEventParcelable zzafx, final GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzafx = zzafx;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof zzd && this.zzafx.equals(((zzd)o).zzafx);
    }
    
    @Override
    public String toString() {
        return "MethodImpl(" + this.zzafx + ")";
    }
    
    protected void zza(final zzlw zzlw) throws RemoteException {
        final zzlx.zza zza = new zzlx.zza() {
            public void zzv(final Status status) {
                zzd.this.zza((R)status);
            }
        };
        try {
            zzlv.zzb(this.zzafx);
            zzlw.zza(zza, this.zzafx);
        }
        catch (Throwable t) {
            Log.e("ClearcutLoggerApiImpl", "MessageNanoProducer " + this.zzafx.zzafl.toString() + " threw: " + t.toString());
        }
    }
    
    protected Status zzb(final Status status) {
        return status;
    }
}
