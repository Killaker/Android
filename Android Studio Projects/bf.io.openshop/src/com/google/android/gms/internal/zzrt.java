package com.google.android.gms.internal;

import android.content.*;
import java.util.concurrent.*;

public class zzrt
{
    public static final Integer zzbmw;
    public static final Integer zzbmx;
    private final Context mContext;
    private final ExecutorService zzbkn;
    
    static {
        zzbmw = 0;
        zzbmx = 1;
    }
    
    public zzrt(final Context context) {
        this(context, Executors.newSingleThreadExecutor());
    }
    
    zzrt(final Context mContext, final ExecutorService zzbkn) {
        this.mContext = mContext;
        this.zzbkn = zzbkn;
    }
}
