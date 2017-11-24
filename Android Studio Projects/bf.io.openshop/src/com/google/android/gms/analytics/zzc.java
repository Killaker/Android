package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.*;

public final class zzc
{
    public static String zzT(final int n) {
        return zzb("&cd", n);
    }
    
    public static String zzU(final int n) {
        return zzb("cd", n);
    }
    
    public static String zzV(final int n) {
        return zzb("&cm", n);
    }
    
    public static String zzW(final int n) {
        return zzb("cm", n);
    }
    
    public static String zzX(final int n) {
        return zzb("&pr", n);
    }
    
    public static String zzY(final int n) {
        return zzb("pr", n);
    }
    
    public static String zzZ(final int n) {
        return zzb("&promo", n);
    }
    
    public static String zzaa(final int n) {
        return zzb("promo", n);
    }
    
    public static String zzab(final int n) {
        return zzb("pi", n);
    }
    
    public static String zzac(final int n) {
        return zzb("&il", n);
    }
    
    public static String zzad(final int n) {
        return zzb("il", n);
    }
    
    public static String zzae(final int n) {
        return zzb("cd", n);
    }
    
    public static String zzaf(final int n) {
        return zzb("cm", n);
    }
    
    private static String zzb(final String s, final int n) {
        if (n < 1) {
            zzae.zzf("index out of range for prefix", s);
            return "";
        }
        return s + n;
    }
}
