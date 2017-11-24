package com.facebook.share.internal;

import android.os.*;

public static final class Result
{
    private final Bundle bundle;
    
    public Result(final Bundle bundle) {
        this.bundle = bundle;
    }
    
    public Bundle getData() {
        return this.bundle;
    }
}
