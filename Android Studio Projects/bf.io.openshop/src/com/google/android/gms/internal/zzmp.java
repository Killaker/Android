package com.google.android.gms.internal;

import android.content.*;
import android.content.pm.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class zzmp
{
    public static boolean zzk(final Context context, final String s) {
        final PackageManager packageManager = context.getPackageManager();
        try {
            final int n = packageManager.getApplicationInfo(s, 0).flags & 0x200000;
            boolean b = false;
            if (n != 0) {
                b = true;
            }
            return b;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    public static boolean zzkr() {
        return zzd.zzakE && zzlz.isInitialized() && zzlz.zzpW() == Process.myUid();
    }
}
