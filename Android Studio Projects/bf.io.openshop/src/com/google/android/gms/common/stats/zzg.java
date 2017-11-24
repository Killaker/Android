package com.google.android.gms.common.stats;

import android.os.*;
import android.text.*;

public class zzg
{
    public static String zza(final PowerManager$WakeLock powerManager$WakeLock, String s) {
        final StringBuilder append = new StringBuilder().append(String.valueOf(Process.myPid() << 32 | System.identityHashCode(powerManager$WakeLock)));
        if (TextUtils.isEmpty((CharSequence)s)) {
            s = "";
        }
        return append.append(s).toString();
    }
}
