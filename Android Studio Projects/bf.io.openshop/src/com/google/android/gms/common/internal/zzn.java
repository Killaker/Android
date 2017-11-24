package com.google.android.gms.common.internal;

import android.content.*;
import android.support.annotation.*;
import android.text.*;
import android.net.*;

public class zzn
{
    private static final Uri zzamj;
    private static final Uri zzamk;
    
    static {
        zzamj = Uri.parse("http://plus.google.com/");
        zzamk = zzn.zzamj.buildUpon().appendPath("circles").appendPath("find").build();
    }
    
    public static Intent zzcJ(final String s) {
        final Uri fromParts = Uri.fromParts("package", s, (String)null);
        final Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }
    
    public static Intent zzqU() {
        final Intent intent = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
        intent.setPackage("com.google.android.wearable.app");
        return intent;
    }
    
    private static Uri zzw(final String s, @Nullable final String s2) {
        final Uri$Builder appendQueryParameter = Uri.parse("market://details").buildUpon().appendQueryParameter("id", s);
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            appendQueryParameter.appendQueryParameter("pcampaignid", s2);
        }
        return appendQueryParameter.build();
    }
    
    public static Intent zzx(final String s, @Nullable final String s2) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(zzw(s, s2));
        intent.setPackage("com.android.vending");
        intent.addFlags(524288);
        return intent;
    }
}
