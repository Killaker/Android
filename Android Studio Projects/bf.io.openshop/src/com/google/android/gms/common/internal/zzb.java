package com.google.android.gms.common.internal;

import android.os.*;
import android.util.*;

public final class zzb
{
    public static void zza(final boolean b, final Object o) {
        if (!b) {
            throw new IllegalStateException(String.valueOf(o));
        }
    }
    
    public static void zzab(final boolean b) {
        if (!b) {
            throw new IllegalStateException();
        }
    }
    
    public static void zzcD(final String s) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            Log.e("Asserts", "checkMainThread: current thread " + Thread.currentThread() + " IS NOT the main thread " + Looper.getMainLooper().getThread() + "!");
            throw new IllegalStateException(s);
        }
    }
    
    public static void zzcE(final String s) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            Log.e("Asserts", "checkNotMainThread: current thread " + Thread.currentThread() + " IS the main thread " + Looper.getMainLooper().getThread() + "!");
            throw new IllegalStateException(s);
        }
    }
    
    public static void zzv(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("null reference");
        }
    }
}
