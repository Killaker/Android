package com.google.android.gms.gcm;

import android.os.*;

public class TaskParams
{
    private final Bundle extras;
    private final String tag;
    
    public TaskParams(final String s) {
        this(s, null);
    }
    
    public TaskParams(final String tag, final Bundle extras) {
        this.tag = tag;
        this.extras = extras;
    }
    
    public Bundle getExtras() {
        return this.extras;
    }
    
    public String getTag() {
        return this.tag;
    }
}
