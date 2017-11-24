package com.google.android.gms.common.api;

import android.app.*;
import android.support.annotation.*;
import com.google.android.gms.common.internal.*;
import android.util.*;
import android.content.*;

public abstract class ResolvingResultCallbacks<R extends Result> extends ResultCallbacks<R>
{
    private final Activity mActivity;
    private final int zzagz;
    
    protected ResolvingResultCallbacks(@NonNull final Activity activity, final int zzagz) {
        this.mActivity = zzx.zzb(activity, "Activity must not be null");
        this.zzagz = zzagz;
    }
    
    @Override
    public final void onFailure(@NonNull final Status status) {
        if (status.hasResolution()) {
            try {
                status.startResolutionForResult(this.mActivity, this.zzagz);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                Log.e("ResolvingResultCallback", "Failed to start resolution", (Throwable)ex);
                this.onUnresolvableFailure(new Status(8));
                return;
            }
        }
        this.onUnresolvableFailure(status);
    }
    
    @Override
    public abstract void onSuccess(@NonNull final R p0);
    
    public abstract void onUnresolvableFailure(@NonNull final Status p0);
}
