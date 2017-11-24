package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.*;

public abstract class DowngradeableSafeParcel implements SafeParcelable
{
    private static final Object zzalh;
    private static ClassLoader zzali;
    private static Integer zzalj;
    private boolean zzalk;
    
    static {
        zzalh = new Object();
        DowngradeableSafeParcel.zzali = null;
        DowngradeableSafeParcel.zzalj = null;
    }
    
    public DowngradeableSafeParcel() {
        this.zzalk = false;
    }
    
    private static boolean zza(final Class<?> clazz) {
        try {
            return "SAFE_PARCELABLE_NULL_STRING".equals(clazz.getField("NULL").get(null));
        }
        catch (IllegalAccessException ex) {
            return false;
        }
        catch (NoSuchFieldException ex2) {
            return false;
        }
    }
    
    protected static boolean zzcF(final String s) {
        final ClassLoader zzqA = zzqA();
        if (zzqA == null) {
            return true;
        }
        try {
            return zza(zzqA.loadClass(s));
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    protected static ClassLoader zzqA() {
        synchronized (DowngradeableSafeParcel.zzalh) {
            return DowngradeableSafeParcel.zzali;
        }
    }
    
    protected static Integer zzqB() {
        synchronized (DowngradeableSafeParcel.zzalh) {
            return DowngradeableSafeParcel.zzalj;
        }
    }
    
    protected boolean zzqC() {
        return this.zzalk;
    }
}
