package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.*;

private static final class zzc<R extends Result> extends zzb<R>
{
    public zzc(final GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }
    
    @Override
    protected R zzc(final Status status) {
        throw new UnsupportedOperationException("Creating failed results is not supported");
    }
}
