package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.*;
import com.google.android.gms.common.api.*;

abstract class zzmi<R extends Result> extends com.google.android.gms.common.api.internal.zza.zza<R, zzmj>
{
    public zzmi(final GoogleApiClient googleApiClient) {
        super(zzmf.zzUI, googleApiClient);
    }
    
    abstract static class zza extends zzmi<Status>
    {
        public zza(final GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }
        
        public Status zzb(final Status status) {
            return status;
        }
    }
}
