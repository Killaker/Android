package com.google.android.gms.common.api;

import android.support.annotation.*;

public interface ResultCallback<R extends Result>
{
    void onResult(@NonNull final R p0);
}
