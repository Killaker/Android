package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.*;
import android.os.*;

private static final class zza<R extends Result> extends zzb<R>
{
    private final R zzagx;
    
    public zza(final R zzagx) {
        super(Looper.getMainLooper());
        this.zzagx = zzagx;
    }
    
    @Override
    protected R zzc(final Status status) {
        if (status.getStatusCode() != this.zzagx.getStatus().getStatusCode()) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
        return this.zzagx;
    }
}
