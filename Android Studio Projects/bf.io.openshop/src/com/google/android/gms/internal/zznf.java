package com.google.android.gms.internal;

import android.content.*;
import android.os.*;
import android.app.*;
import java.util.*;

public class zznf
{
    private static String zza(final StackTraceElement[] array, final int n) {
        if (n + 4 >= array.length) {
            return "<bottom of call stack>";
        }
        final StackTraceElement stackTraceElement = array[n + 4];
        return stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber();
    }
    
    public static String zzaz(final Context context) {
        return zzi(context, Binder.getCallingPid());
    }
    
    public static String zzi(final Context context, final int n) {
        final List runningAppProcesses = ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : runningAppProcesses) {
                if (activityManager$RunningAppProcessInfo.pid == n) {
                    return activityManager$RunningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }
    
    public static String zzn(int i, final int n) {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        final StringBuffer sb = new StringBuffer();
        while (i < n + i) {
            sb.append(zza(stackTrace, i)).append(" ");
            ++i;
        }
        return sb.toString();
    }
}
