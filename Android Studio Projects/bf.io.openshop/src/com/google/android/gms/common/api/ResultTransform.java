package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.*;
import android.support.annotation.*;

public abstract class ResultTransform<R extends Result, S extends Result>
{
    @NonNull
    public final PendingResult<S> createFailedResult(@NonNull final Status status) {
        return new zzt<S>(status);
    }
    
    @NonNull
    public Status onFailure(@NonNull final Status status) {
        return status;
    }
    
    @Nullable
    @WorkerThread
    public abstract PendingResult<S> onSuccess(@NonNull final R p0);
}
