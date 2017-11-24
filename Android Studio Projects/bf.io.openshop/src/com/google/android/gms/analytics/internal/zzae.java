package com.google.android.gms.analytics.internal;

import com.google.android.gms.analytics.*;
import android.util.*;

@Deprecated
public class zzae
{
    private static volatile Logger zzSV;
    
    static {
        setLogger(new zzs());
    }
    
    public static Logger getLogger() {
        return zzae.zzSV;
    }
    
    public static void setLogger(final Logger zzSV) {
        zzae.zzSV = zzSV;
    }
    
    public static void v(final String s) {
        final zzaf zzlx = zzaf.zzlx();
        if (zzlx != null) {
            zzlx.zzbd(s);
        }
        else if (zzQ(0)) {
            Log.v((String)zzy.zzRL.get(), s);
        }
        final Logger zzSV = zzae.zzSV;
        if (zzSV != null) {
            zzSV.verbose(s);
        }
    }
    
    public static boolean zzQ(final int n) {
        final Logger logger = getLogger();
        boolean b = false;
        if (logger != null) {
            final int logLevel = getLogger().getLogLevel();
            b = false;
            if (logLevel <= n) {
                b = true;
            }
        }
        return b;
    }
    
    public static void zzaJ(final String s) {
        final zzaf zzlx = zzaf.zzlx();
        if (zzlx != null) {
            zzlx.zzbf(s);
        }
        else if (zzQ(1)) {
            Log.i((String)zzy.zzRL.get(), s);
        }
        final Logger zzSV = zzae.zzSV;
        if (zzSV != null) {
            zzSV.info(s);
        }
    }
    
    public static void zzaK(final String s) {
        final zzaf zzlx = zzaf.zzlx();
        if (zzlx != null) {
            zzlx.zzbg(s);
        }
        else if (zzQ(2)) {
            Log.w((String)zzy.zzRL.get(), s);
        }
        final Logger zzSV = zzae.zzSV;
        if (zzSV != null) {
            zzSV.warn(s);
        }
    }
    
    public static void zzf(final String s, final Object o) {
        final zzaf zzlx = zzaf.zzlx();
        if (zzlx != null) {
            zzlx.zze(s, o);
        }
        else if (zzQ(3)) {
            String string;
            if (o != null) {
                string = s + ":" + o;
            }
            else {
                string = s;
            }
            Log.e((String)zzy.zzRL.get(), string);
        }
        final Logger zzSV = zzae.zzSV;
        if (zzSV != null) {
            zzSV.error(s);
        }
    }
}
