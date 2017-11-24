package com.google.android.gms.common.api;

public final class BatchResultToken<R extends Result>
{
    protected final int mId;
    
    BatchResultToken(final int mId) {
        this.mId = mId;
    }
}
