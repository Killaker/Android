package com.google.android.gms.auth.api.signin;

import com.google.android.gms.*;
import android.util.*;
import android.content.*;

public enum zzd
{
    zzXh("google.com", R.string.auth_google_play_services_client_google_display_name, "https://accounts.google.com"), 
    zzXi("facebook.com", R.string.auth_google_play_services_client_facebook_display_name, "https://www.facebook.com");
    
    private final String zzVY;
    private final String zzXj;
    private final int zzXk;
    
    private zzd(final String zzXj, final int zzXk, final String zzVY) {
        this.zzXj = zzXj;
        this.zzXk = zzXk;
        this.zzVY = zzVY;
    }
    
    public static zzd zzbL(final String s) {
        if (s != null) {
            for (final zzd zzd : values()) {
                if (zzd.zzmT().equals(s)) {
                    return zzd;
                }
            }
            Log.w("IdProvider", "Unrecognized providerId: " + s);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.zzXj;
    }
    
    public CharSequence zzae(final Context context) {
        return context.getResources().getString(this.zzXk);
    }
    
    public String zzmT() {
        return this.zzXj;
    }
}
