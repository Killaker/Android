package com.google.android.gms.common.api;

import android.support.annotation.*;
import com.google.android.gms.common.api.internal.*;

public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R>
{
    public abstract void onFailure(@NonNull final Status p0);
    
    @Override
    public final void onResult(@NonNull final R r) {
        final Status status = r.getStatus();
        if (status.isSuccess()) {
            this.onSuccess(r);
            return;
        }
        this.onFailure(status);
        zzb.zzc(r);
    }
    
    public abstract void onSuccess(@NonNull final R p0);
}
