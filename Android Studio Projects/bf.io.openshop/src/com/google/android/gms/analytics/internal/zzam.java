package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.*;
import android.text.*;
import com.google.android.gms.internal.*;
import java.util.*;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import java.net.*;
import java.security.*;
import java.io.*;

public class zzam
{
    private static final char[] zzTu;
    
    static {
        zzTu = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
    
    public static String zzK(final boolean b) {
        if (b) {
            return "1";
        }
        return "0";
    }
    
    public static double zza(final String s, final double n) {
        if (s == null) {
            return n;
        }
        try {
            return Double.parseDouble(s);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static zzpr zza(final zzaf zzaf, final String s) {
        zzx.zzz(zzaf);
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        new HashMap();
        try {
            final Map<String, String> zza = zzmz.zza(new URI("?" + s), "UTF-8");
            final zzpr zzpr = new zzpr();
            zzpr.zzey(zza.get("utm_content"));
            zzpr.zzew(zza.get("utm_medium"));
            zzpr.setName(zza.get("utm_campaign"));
            zzpr.zzev(zza.get("utm_source"));
            zzpr.zzex(zza.get("utm_term"));
            zzpr.zzez(zza.get("utm_id"));
            zzpr.zzeA(zza.get("anid"));
            zzpr.zzeB(zza.get("gclid"));
            zzpr.zzeC(zza.get("dclid"));
            zzpr.zzeD(zza.get("aclid"));
            return zzpr;
        }
        catch (URISyntaxException ex) {
            zzaf.zzd("No valid campaign data found", ex);
            return null;
        }
    }
    
    public static String zza(final Locale locale) {
        if (locale != null) {
            final String language = locale.getLanguage();
            if (!TextUtils.isEmpty((CharSequence)language)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(language.toLowerCase());
                if (!TextUtils.isEmpty((CharSequence)locale.getCountry())) {
                    sb.append("-").append(locale.getCountry().toLowerCase());
                }
                return sb.toString();
            }
        }
        return null;
    }
    
    public static void zza(final Map<String, String> map, final String s, final Map<String, String> map2) {
        zzc(map, s, map2.get(s));
    }
    
    public static boolean zza(final double n, final String s) {
        return n > 0.0 && n < 100.0 && zzbw(s) % 10000 >= 100.0 * n;
    }
    
    public static boolean zza(final Context context, final Class<? extends Service> clazz) {
        try {
            final ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, (Class)clazz), 4);
            if (serviceInfo != null && serviceInfo.enabled) {
                return true;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
        return false;
    }
    
    public static boolean zza(final Context context, final Class<? extends BroadcastReceiver> clazz, final boolean b) {
        try {
            final ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, (Class)clazz), 2);
            if (receiverInfo != null && receiverInfo.enabled && (!b || receiverInfo.exported)) {
                return true;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
        return false;
    }
    
    public static void zzb(final Map<String, String> map, final String s, final boolean b) {
        if (!map.containsKey(s)) {
            String s2;
            if (b) {
                s2 = "1";
            }
            else {
                s2 = "0";
            }
            map.put(s, s2);
        }
    }
    
    public static Map<String, String> zzbs(final String s) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final String[] split = s.split("&");
        for (int length = split.length, i = 0; i < length; ++i) {
            final String[] split2 = split[i].split("=", 3);
            if (split2.length > 1) {
                final String s2 = split2[0];
                String s3;
                if (TextUtils.isEmpty((CharSequence)split2[1])) {
                    s3 = null;
                }
                else {
                    s3 = split2[1];
                }
                hashMap.put(s2, s3);
                if (split2.length == 3 && !TextUtils.isEmpty((CharSequence)split2[1]) && !hashMap.containsKey(split2[1])) {
                    final String s4 = split2[1];
                    String s5;
                    if (TextUtils.isEmpty((CharSequence)split2[2])) {
                        s5 = null;
                    }
                    else {
                        s5 = split2[2];
                    }
                    hashMap.put(s4, s5);
                }
            }
            else if (split2.length == 1 && split2[0].length() != 0) {
                hashMap.put(split2[0], null);
            }
        }
        return hashMap;
    }
    
    public static long zzbt(final String s) {
        if (s == null) {
            return 0L;
        }
        try {
            return Long.parseLong(s);
        }
        catch (NumberFormatException ex) {
            return 0L;
        }
    }
    
    public static String zzbu(String decode) {
        if (TextUtils.isEmpty((CharSequence)decode)) {
            return null;
        }
        if (decode.contains("?")) {
            final String[] split = decode.split("[\\?]");
            if (split.length > 1) {
                decode = split[1];
            }
        }
        while (true) {
            Label_0223: {
                if (!decode.contains("%3D")) {
                    break Label_0223;
                }
                try {
                    decode = URLDecoder.decode(decode, "UTF-8");
                    final Map<String, String> zzbs = zzbs(decode);
                    final String[] array = { "dclid", "utm_source", "gclid", "aclid", "utm_campaign", "utm_medium", "utm_term", "utm_content", "utm_id", "anid", "gmob_t" };
                    final StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < array.length; ++i) {
                        if (!TextUtils.isEmpty((CharSequence)zzbs.get(array[i]))) {
                            if (sb.length() > 0) {
                                sb.append("&");
                            }
                            sb.append(array[i]).append("=").append(zzbs.get(array[i]));
                        }
                    }
                    return sb.toString();
                }
                catch (UnsupportedEncodingException ex) {
                    return null;
                }
            }
            if (!decode.contains("=")) {
                return null;
            }
            continue;
        }
    }
    
    public static MessageDigest zzbv(final String s) {
        for (int i = 0; i < 2; ++i) {
            try {
                final MessageDigest instance = MessageDigest.getInstance(s);
                if (instance != null) {
                    return instance;
                }
            }
            catch (NoSuchAlgorithmException ex) {}
        }
        return null;
    }
    
    public static int zzbw(final String s) {
        int n = 1;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final int n2 = -1 + s.length();
            n = 0;
            for (int i = n2; i >= 0; --i) {
                final char char1 = s.charAt(i);
                n = char1 + (0xFFFFFFF & n << 6) + (char1 << 14);
                final int n3 = 0xFE00000 & n;
                if (n3 != 0) {
                    n ^= n3 >> 21;
                }
            }
        }
        return n;
    }
    
    public static boolean zzbx(final String s) {
        return TextUtils.isEmpty((CharSequence)s) || !s.startsWith("http:");
    }
    
    public static void zzc(final Map<String, String> map, final String s, final String s2) {
        if (s2 != null && !map.containsKey(s)) {
            map.put(s, s2);
        }
    }
    
    public static void zzd(final Map<String, String> map, final String s, final String s2) {
        if (s2 != null && TextUtils.isEmpty((CharSequence)map.get(s))) {
            map.put(s, s2);
        }
    }
    
    public static boolean zzh(final String s, boolean b) {
        if (s != null) {
            if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("1")) {
                b = true;
            }
            else if (s.equalsIgnoreCase("false") || s.equalsIgnoreCase("no") || s.equalsIgnoreCase("0")) {
                return false;
            }
        }
        return b;
    }
}
