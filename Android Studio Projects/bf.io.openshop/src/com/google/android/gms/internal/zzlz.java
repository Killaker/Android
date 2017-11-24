package com.google.android.gms.internal;

import android.os.*;

public abstract class zzlz<T>
{
    private static zza zzaiV;
    private static int zzaiW;
    private static String zzaiX;
    private static final Object zzqy;
    private T zzSC;
    protected final String zzvs;
    protected final T zzvt;
    
    static {
        zzqy = new Object();
        zzlz.zzaiV = null;
        zzlz.zzaiW = 0;
        zzlz.zzaiX = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    }
    
    protected zzlz(final String zzvs, final T zzvt) {
        this.zzSC = null;
        this.zzvs = zzvs;
        this.zzvt = zzvt;
    }
    
    public static boolean isInitialized() {
        return zzlz.zzaiV != null;
    }
    
    public static zzlz<Float> zza(final String s, final Float n) {
        return new zzlz<Float>(s, n) {
            protected Float zzcx(final String s) {
                return zzlz.zzaiV.zzb(this.zzvs, (Float)this.zzvt);
            }
        };
    }
    
    public static zzlz<Integer> zza(final String s, final Integer n) {
        return new zzlz<Integer>(s, n) {
            protected Integer zzcw(final String s) {
                return zzlz.zzaiV.zzb(this.zzvs, (Integer)this.zzvt);
            }
        };
    }
    
    public static zzlz<Long> zza(final String s, final Long n) {
        return new zzlz<Long>(s, n) {
            protected Long zzcv(final String s) {
                return zzlz.zzaiV.getLong(this.zzvs, (Long)this.zzvt);
            }
        };
    }
    
    public static zzlz<Boolean> zzk(final String s, final boolean b) {
        return new zzlz<Boolean>(s, Boolean.valueOf(b)) {
            protected Boolean zzcu(final String s) {
                return zzlz.zzaiV.zza(this.zzvs, (Boolean)this.zzvt);
            }
        };
    }
    
    public static int zzpW() {
        return zzlz.zzaiW;
    }
    
    public static zzlz<String> zzv(final String s, final String s2) {
        return new zzlz<String>(s, s2) {
            protected String zzcy(final String s) {
                return zzlz.zzaiV.getString(this.zzvs, (String)this.zzvt);
            }
        };
    }
    
    public final T get() {
        if (this.zzSC != null) {
            return this.zzSC;
        }
        return this.zzct(this.zzvs);
    }
    
    protected abstract T zzct(final String p0);
    
    public final T zzpX() {
        final long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return this.get();
        }
        finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }
    
    private interface zza
    {
        Long getLong(final String p0, final Long p1);
        
        String getString(final String p0, final String p1);
        
        Boolean zza(final String p0, final Boolean p1);
        
        Float zzb(final String p0, final Float p1);
        
        Integer zzb(final String p0, final Integer p1);
    }
}
