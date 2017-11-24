package com.google.android.gms.internal;

import android.content.*;
import android.annotation.*;
import android.content.res.*;

public final class zzmu
{
    @TargetApi(20)
    public static boolean zzaw(final Context context) {
        return zzne.zzsl() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }
    
    public static boolean zzb(final Resources resources) {
        if (resources != null) {
            boolean b;
            if ((0xF & resources.getConfiguration().screenLayout) > 3) {
                b = true;
            }
            else {
                b = false;
            }
            if ((zzne.zzsd() && b) || zzc(resources)) {
                return true;
            }
        }
        return false;
    }
    
    @TargetApi(13)
    private static boolean zzc(final Resources resources) {
        final Configuration configuration = resources.getConfiguration();
        final boolean zzsf = zzne.zzsf();
        boolean b = false;
        if (zzsf) {
            final int n = 0xF & configuration.screenLayout;
            b = false;
            if (n <= 3) {
                final int smallestScreenWidthDp = configuration.smallestScreenWidthDp;
                b = false;
                if (smallestScreenWidthDp >= 600) {
                    b = true;
                }
            }
        }
        return b;
    }
}
