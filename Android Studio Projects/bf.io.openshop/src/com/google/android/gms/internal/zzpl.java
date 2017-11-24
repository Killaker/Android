package com.google.android.gms.internal;

import java.util.concurrent.*;
import android.os.*;

public class zzpl
{
    public static <T> T zzb(final Callable<T> callable) {
        final StrictMode$ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(StrictMode$ThreadPolicy.LAX);
            return callable.call();
        }
        catch (Throwable t) {
            return null;
        }
        finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }
}
