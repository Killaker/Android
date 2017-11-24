package com.google.android.gms.tagmanager;

import java.util.*;
import android.net.*;
import android.content.*;

public class zzax
{
    private static String zzbjg;
    static Map<String, String> zzbjh;
    
    static {
        zzax.zzbjh = new HashMap<String, String>();
    }
    
    public static String zzU(final String s, final String s2) {
        if (s2 != null) {
            return Uri.parse("http://hostname/?" + s).getQueryParameter(s2);
        }
        if (s.length() > 0) {
            return s;
        }
        return null;
    }
    
    public static String zzf(final Context context, final String s, final String s2) {
        String string = zzax.zzbjh.get(s);
        if (string == null) {
            final SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            if (sharedPreferences != null) {
                string = sharedPreferences.getString(s, "");
            }
            else {
                string = "";
            }
            zzax.zzbjh.put(s, string);
        }
        return zzU(string, s2);
    }
    
    public static void zzgh(final String zzbjg) {
        synchronized (zzax.class) {
            zzax.zzbjg = zzbjg;
        }
    }
    
    public static String zzm(final Context context, final String s) {
        Label_0043: {
            if (zzax.zzbjg != null) {
                break Label_0043;
            }
            synchronized (zzax.class) {
                if (zzax.zzbjg == null) {
                    final SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        zzax.zzbjg = sharedPreferences.getString("referrer", "");
                    }
                    else {
                        zzax.zzbjg = "";
                    }
                }
                // monitorexit(zzax.class)
                return zzU(zzax.zzbjg, s);
            }
        }
    }
    
    public static void zzn(final Context context, final String s) {
        final String zzU = zzU(s, "conv");
        if (zzU != null && zzU.length() > 0) {
            zzax.zzbjh.put(zzU, s);
            zzcv.zzb(context, "gtm_click_referrers", zzU, s);
        }
    }
}
