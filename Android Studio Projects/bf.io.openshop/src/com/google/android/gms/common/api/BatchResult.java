package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.*;
import java.util.concurrent.*;

public final class BatchResult implements Result
{
    private final Status zzUX;
    private final PendingResult<?>[] zzagc;
    
    BatchResult(final Status zzUX, final PendingResult<?>[] zzagc) {
        this.zzUX = zzUX;
        this.zzagc = zzagc;
    }
    
    @Override
    public Status getStatus() {
        return this.zzUX;
    }
    
    public <R extends Result> R take(final BatchResultToken<R> batchResultToken) {
        zzx.zzb(batchResultToken.mId < this.zzagc.length, (Object)"The result token does not belong to this batch");
        return (R)this.zzagc[batchResultToken.mId].await(0L, TimeUnit.MILLISECONDS);
    }
}
