package com.google.android.gms.common.api;

import android.os.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.api.internal.*;

public final class PendingResults
{
    public static PendingResult<Status> canceledPendingResult() {
        final zzv zzv = new zzv(Looper.getMainLooper());
        zzv.cancel();
        return zzv;
    }
    
    public static <R extends Result> PendingResult<R> canceledPendingResult(final R r) {
        zzx.zzb(r, "Result must not be null");
        zzx.zzb(r.getStatus().getStatusCode() == 16, (Object)"Status code must be CommonStatusCodes.CANCELED");
        final zza zza = new zza(r);
        zza.cancel();
        return (PendingResult<R>)zza;
    }
    
    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(final R r) {
        zzx.zzb(r, "Result must not be null");
        final zzc<Result> zzc = new zzc<Result>((GoogleApiClient)null);
        zzc.zza(r);
        return new zzr<R>((PendingResult<R>)zzc);
    }
    
    public static PendingResult<Status> immediatePendingResult(final Status status) {
        zzx.zzb(status, "Result must not be null");
        final zzv zzv = new zzv(Looper.getMainLooper());
        zzv.zza(status);
        return zzv;
    }
    
    public static <R extends Result> PendingResult<R> zza(final R r, final GoogleApiClient googleApiClient) {
        zzx.zzb(r, "Result must not be null");
        zzx.zzb(!r.getStatus().isSuccess(), (Object)"Status code must not be SUCCESS");
        final zzb<R> zzb = new zzb<R>(googleApiClient, r);
        zzb.zza(r);
        return zzb;
    }
    
    public static PendingResult<Status> zza(final Status status, final GoogleApiClient googleApiClient) {
        zzx.zzb(status, "Result must not be null");
        final zzv zzv = new zzv(googleApiClient);
        zzv.zza(status);
        return zzv;
    }
    
    public static <R extends Result> OptionalPendingResult<R> zzb(final R r, final GoogleApiClient googleApiClient) {
        zzx.zzb(r, "Result must not be null");
        final zzc<Result> zzc = new zzc<Result>(googleApiClient);
        zzc.zza(r);
        return new zzr<R>((PendingResult<R>)zzc);
    }
    
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
}
