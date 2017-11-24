package com.facebook.share.widget;

import android.os.*;

public static final class Result
{
    private final Bundle data;
    
    private Result(final Bundle data) {
        this.data = data;
    }
    
    public Bundle getData() {
        return this.data;
    }
}
