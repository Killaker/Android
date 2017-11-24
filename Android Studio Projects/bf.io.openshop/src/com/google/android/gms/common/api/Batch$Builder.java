package com.google.android.gms.common.api;

import java.util.*;

public static final class Builder
{
    private GoogleApiClient zzaaj;
    private List<PendingResult<?>> zzage;
    
    public Builder(final GoogleApiClient zzaaj) {
        this.zzage = new ArrayList<PendingResult<?>>();
        this.zzaaj = zzaaj;
    }
    
    public <R extends Result> BatchResultToken<R> add(final PendingResult<R> pendingResult) {
        final BatchResultToken<R> batchResultToken = new BatchResultToken<R>(this.zzage.size());
        this.zzage.add(pendingResult);
        return batchResultToken;
    }
    
    public Batch build() {
        return new Batch(this.zzage, this.zzaaj, null);
    }
}
