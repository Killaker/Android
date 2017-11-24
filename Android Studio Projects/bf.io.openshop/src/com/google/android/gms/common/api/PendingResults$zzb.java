package com.google.android.gms.common.api;

private static final class zzb<R extends Result> extends com.google.android.gms.common.api.internal.zzb<R>
{
    private final R zzagy;
    
    public zzb(final GoogleApiClient googleApiClient, final R zzagy) {
        super(googleApiClient);
        this.zzagy = zzagy;
    }
    
    @Override
    protected R zzc(final Status status) {
        return this.zzagy;
    }
}
