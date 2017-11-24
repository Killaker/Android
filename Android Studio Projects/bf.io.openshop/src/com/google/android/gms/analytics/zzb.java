package com.google.android.gms.analytics;

import com.google.android.gms.measurement.*;
import java.text.*;
import com.google.android.gms.common.internal.*;
import android.net.*;
import android.text.*;
import java.util.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.analytics.ecommerce.*;
import com.google.android.gms.analytics.internal.*;
import java.io.*;

public class zzb extends zzc implements zzi
{
    private static DecimalFormat zzOU;
    private final zzf zzOK;
    private final String zzOV;
    private final Uri zzOW;
    private final boolean zzOX;
    private final boolean zzOY;
    
    public zzb(final zzf zzf, final String s) {
        this(zzf, s, true, false);
    }
    
    public zzb(final zzf zzOK, final String zzOV, final boolean zzOX, final boolean zzOY) {
        super(zzOK);
        zzx.zzcM(zzOV);
        this.zzOK = zzOK;
        this.zzOV = zzOV;
        this.zzOX = zzOX;
        this.zzOY = zzOY;
        this.zzOW = zzaU(this.zzOV);
    }
    
    private static String zzH(final Map<String, String> map) {
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }
        return sb.toString();
    }
    
    private static void zza(final Map<String, String> map, final String s, final double n) {
        if (n != 0.0) {
            map.put(s, zzb(n));
        }
    }
    
    private static void zza(final Map<String, String> map, final String s, final int n, final int n2) {
        if (n > 0 && n2 > 0) {
            map.put(s, n + "x" + n2);
        }
    }
    
    private static void zza(final Map<String, String> map, final String s, final boolean b) {
        if (b) {
            map.put(s, "1");
        }
    }
    
    static Uri zzaU(final String s) {
        zzx.zzcM(s);
        final Uri$Builder uri$Builder = new Uri$Builder();
        uri$Builder.scheme("uri");
        uri$Builder.authority("google-analytics.com");
        uri$Builder.path(s);
        return uri$Builder.build();
    }
    
    static String zzb(final double n) {
        if (zzb.zzOU == null) {
            zzb.zzOU = new DecimalFormat("0.######");
        }
        return zzb.zzOU.format(n);
    }
    
    private static void zzb(final Map<String, String> map, final String s, final String s2) {
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            map.put(s, s2);
        }
    }
    
    public static Map<String, String> zzc(final com.google.android.gms.measurement.zzc zzc) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final zzkd zzkd = zzc.zze(zzkd.class);
        if (zzkd != null) {
            for (final Map.Entry<String, Object> entry : zzkd.zziR().entrySet()) {
                final String zzi = zzi(entry.getValue());
                if (zzi != null) {
                    hashMap.put(entry.getKey(), zzi);
                }
            }
        }
        final zzke zzke = zzc.zze(zzke.class);
        if (zzke != null) {
            zzb(hashMap, "t", zzke.zziS());
            zzb(hashMap, "cid", zzke.getClientId());
            zzb(hashMap, "uid", zzke.getUserId());
            zzb(hashMap, "sc", zzke.zziV());
            zza(hashMap, "sf", zzke.zziX());
            zza(hashMap, "ni", zzke.zziW());
            zzb(hashMap, "adid", zzke.zziT());
            zza(hashMap, "ate", zzke.zziU());
        }
        final zzpw zzpw = zzc.zze(zzpw.class);
        if (zzpw != null) {
            zzb(hashMap, "cd", zzpw.zzBc());
            zza(hashMap, "a", zzpw.zzBd());
            zzb(hashMap, "dr", zzpw.zzBe());
        }
        final zzpu zzpu = zzc.zze(zzpu.class);
        if (zzpu != null) {
            zzb(hashMap, "ec", zzpu.zzAZ());
            zzb(hashMap, "ea", zzpu.getAction());
            zzb(hashMap, "el", zzpu.getLabel());
            zza(hashMap, "ev", zzpu.getValue());
        }
        final zzpr zzpr = zzc.zze(zzpr.class);
        if (zzpr != null) {
            zzb(hashMap, "cn", zzpr.getName());
            zzb(hashMap, "cs", zzpr.getSource());
            zzb(hashMap, "cm", zzpr.zzAK());
            zzb(hashMap, "ck", zzpr.zzAL());
            zzb(hashMap, "cc", zzpr.getContent());
            zzb(hashMap, "ci", zzpr.getId());
            zzb(hashMap, "anid", zzpr.zzAM());
            zzb(hashMap, "gclid", zzpr.zzAN());
            zzb(hashMap, "dclid", zzpr.zzAO());
            zzb(hashMap, "aclid", zzpr.zzAP());
        }
        final zzpv zzpv = zzc.zze(zzpv.class);
        if (zzpv != null) {
            zzb(hashMap, "exd", zzpv.getDescription());
            zza(hashMap, "exf", zzpv.zzBa());
        }
        final zzpx zzpx = zzc.zze(zzpx.class);
        if (zzpx != null) {
            zzb(hashMap, "sn", zzpx.zzBg());
            zzb(hashMap, "sa", zzpx.getAction());
            zzb(hashMap, "st", zzpx.getTarget());
        }
        final zzpy zzpy = zzc.zze(zzpy.class);
        if (zzpy != null) {
            zzb(hashMap, "utv", zzpy.zzBh());
            zza(hashMap, "utt", zzpy.getTimeInMillis());
            zzb(hashMap, "utc", zzpy.zzAZ());
            zzb(hashMap, "utl", zzpy.getLabel());
        }
        final zzkb zzkb = zzc.zze(zzkb.class);
        if (zzkb != null) {
            for (final Map.Entry<Integer, String> entry2 : zzkb.zziP().entrySet()) {
                final String zzU = zzc.zzU(entry2.getKey());
                if (!TextUtils.isEmpty((CharSequence)zzU)) {
                    hashMap.put(zzU, entry2.getValue());
                }
            }
        }
        final zzkc zzkc = zzc.zze(zzkc.class);
        if (zzkc != null) {
            for (final Map.Entry<Integer, Double> entry3 : zzkc.zziQ().entrySet()) {
                final String zzW = zzc.zzW(entry3.getKey());
                if (!TextUtils.isEmpty((CharSequence)zzW)) {
                    hashMap.put(zzW, zzb(entry3.getValue()));
                }
            }
        }
        final zzpt zzpt = zzc.zze(zzpt.class);
        if (zzpt != null) {
            final ProductAction zzAV = zzpt.zzAV();
            if (zzAV != null) {
                for (final Map.Entry<String, String> entry4 : zzAV.build().entrySet()) {
                    if (entry4.getKey().startsWith("&")) {
                        hashMap.put(entry4.getKey().substring(1), entry4.getValue());
                    }
                    else {
                        hashMap.put(entry4.getKey(), entry4.getValue());
                    }
                }
            }
            final Iterator<Promotion> iterator5 = zzpt.zzAY().iterator();
            int n = 1;
            while (iterator5.hasNext()) {
                hashMap.putAll((Map<?, ?>)iterator5.next().zzba(zzc.zzaa(n)));
                ++n;
            }
            final Iterator<Product> iterator6 = zzpt.zzAW().iterator();
            int n2 = 1;
            while (iterator6.hasNext()) {
                hashMap.putAll((Map<?, ?>)iterator6.next().zzba(zzc.zzY(n2)));
                ++n2;
            }
            final Iterator<Map.Entry<String, List<Product>>> iterator7 = zzpt.zzAX().entrySet().iterator();
            int n3 = 1;
            while (iterator7.hasNext()) {
                final Map.Entry<String, List<Product>> entry5 = iterator7.next();
                final List<Product> list = entry5.getValue();
                final String zzad = zzc.zzad(n3);
                final Iterator<Product> iterator8 = list.iterator();
                int n4 = 1;
                while (iterator8.hasNext()) {
                    hashMap.putAll((Map<?, ?>)iterator8.next().zzba(zzad + zzc.zzab(n4)));
                    ++n4;
                }
                if (!TextUtils.isEmpty((CharSequence)entry5.getKey())) {
                    hashMap.put(zzad + "nm", entry5.getKey());
                }
                ++n3;
            }
        }
        final zzps zzps = zzc.zze(zzps.class);
        if (zzps != null) {
            zzb(hashMap, "ul", zzps.getLanguage());
            zza(hashMap, "sd", zzps.zzAQ());
            zza(hashMap, "sr", zzps.zzAR(), zzps.zzAS());
            zza(hashMap, "vp", zzps.zzAT(), zzps.zzAU());
        }
        final zzpq zzpq = zzc.zze(zzpq.class);
        if (zzpq != null) {
            zzb(hashMap, "an", zzpq.zzlg());
            zzb(hashMap, "aid", zzpq.zzwK());
            zzb(hashMap, "aiid", zzpq.zzAJ());
            zzb(hashMap, "av", zzpq.zzli());
        }
        return hashMap;
    }
    
    private static String zzi(final Object o) {
        CharSequence charSequence;
        if (o == null) {
            charSequence = null;
        }
        else if (o instanceof String) {
            charSequence = (String)o;
            if (TextUtils.isEmpty(charSequence)) {
                return null;
            }
        }
        else if (o instanceof Double) {
            final Double n = (Double)o;
            if (n != 0.0) {
                return zzb(n);
            }
            return null;
        }
        else {
            if (!(o instanceof Boolean)) {
                return String.valueOf(o);
            }
            if (o != Boolean.FALSE) {
                return "1";
            }
            return null;
        }
        return (String)charSequence;
    }
    
    @Override
    public void zzb(final com.google.android.gms.measurement.zzc zzc) {
        zzx.zzz(zzc);
        zzx.zzb(zzc.zzAz(), (Object)"Can't deliver not submitted measurement");
        zzx.zzcE("deliver should be called on worker thread");
        final com.google.android.gms.measurement.zzc zzAu = zzc.zzAu();
        final zzke zzke = zzAu.zzf(zzke.class);
        if (TextUtils.isEmpty((CharSequence)zzke.zziS())) {
            this.zzjm().zzh(zzc(zzAu), "Ignoring measurement without type");
        }
        else {
            if (TextUtils.isEmpty((CharSequence)zzke.getClientId())) {
                this.zzjm().zzh(zzc(zzAu), "Ignoring measurement without client id");
                return;
            }
            if (!this.zzOK.zzjz().getAppOptOut()) {
                final double zziX = zzke.zziX();
                if (zzam.zza(zziX, zzke.getClientId())) {
                    this.zzb("Sampling enabled. Hit sampled out. sampling rate", zziX);
                    return;
                }
                final Map<String, String> zzc2 = zzc(zzAu);
                zzc2.put("v", "1");
                zzc2.put("_v", zze.zzQm);
                zzc2.put("tid", this.zzOV);
                if (this.zzOK.zzjz().isDryRunEnabled()) {
                    this.zzc("Dry run is enabled. GoogleAnalytics would have sent", zzH(zzc2));
                    return;
                }
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                zzam.zzc(hashMap, "uid", zzke.getUserId());
                final zzpq zzpq = zzc.zze(zzpq.class);
                if (zzpq != null) {
                    zzam.zzc(hashMap, "an", zzpq.zzlg());
                    zzam.zzc(hashMap, "aid", zzpq.zzwK());
                    zzam.zzc(hashMap, "av", zzpq.zzli());
                    zzam.zzc(hashMap, "aiid", zzpq.zzAJ());
                }
                zzc2.put("_s", String.valueOf(this.zziH().zza(new zzh(0L, zzke.getClientId(), this.zzOV, !TextUtils.isEmpty((CharSequence)zzke.zziT()), 0L, hashMap))));
                this.zziH().zza(new zzab(this.zzjm(), zzc2, zzc.zzAx(), true));
            }
        }
    }
    
    @Override
    public Uri zziA() {
        return this.zzOW;
    }
}
