package com.google.android.gms.analytics.internal;

import android.util.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import java.io.*;

public class zzaf extends zzd
{
    private static String zzSW;
    private static String zzSX;
    private static zzaf zzSY;
    
    static {
        zzaf.zzSW = "3";
        zzaf.zzSX = "01VDIWEA?";
    }
    
    public zzaf(final zzf zzf) {
        super(zzf);
    }
    
    public static zzaf zzlx() {
        return zzaf.zzSY;
    }
    
    public void zza(final int n, final String s, final Object o, final Object o2, final Object o3) {
        final String s2 = zzy.zzRL.get();
        if (Log.isLoggable(s2, n)) {
            Log.println(n, s2, zzc.zzc(s, o, o2, o3));
        }
        if (n >= 5) {
            this.zzb(n, s, o, o2, o3);
        }
    }
    
    public void zza(final zzab zzab, String s) {
        if (s == null) {
            s = "no reason provided";
        }
        String string;
        if (zzab != null) {
            string = zzab.toString();
        }
        else {
            string = "no hit data";
        }
        this.zzd("Discarding hit. " + s, string);
    }
    
    public void zzb(final int n, final String s, final Object o, final Object o2, final Object o3) {
        while (true) {
            while (true) {
                Label_0226: {
                    while (true) {
                        final int n2;
                        synchronized (this) {
                            zzx.zzz(s);
                            n2 = 0;
                            if (n >= 0) {
                                break Label_0226;
                            }
                            if (n2 >= zzaf.zzSX.length()) {
                                final int n3 = -1 + zzaf.zzSX.length();
                                char c;
                                if (this.zzjn().zzks()) {
                                    if (this.zzjn().zzkr()) {
                                        c = 'P';
                                    }
                                    else {
                                        c = 'C';
                                    }
                                }
                                else if (this.zzjn().zzkr()) {
                                    c = 'p';
                                }
                                else {
                                    c = 'c';
                                }
                                String s2 = zzaf.zzSW + zzaf.zzSX.charAt(n3) + c + zze.VERSION + ":" + zzc.zzc(s, this.zzl(o), this.zzl(o2), this.zzl(o3));
                                if (s2.length() > 1024) {
                                    s2 = s2.substring(0, 1024);
                                }
                                final zzai zzjA = this.zzji().zzjA();
                                if (zzjA != null) {
                                    zzjA.zzlK().zzbq(s2);
                                }
                                return;
                            }
                        }
                        final int n3 = n2;
                        continue;
                    }
                }
                int n2 = n;
                continue;
            }
        }
    }
    
    public void zzh(final Map<String, String> map, String s) {
        if (s == null) {
            s = "no reason provided";
        }
        String string;
        if (map != null) {
            final StringBuilder sb = new StringBuilder();
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
            }
            string = sb.toString();
        }
        else {
            string = "no hit data";
        }
        this.zzd("Discarding hit. " + s, string);
    }
    
    @Override
    protected void zziJ() {
        synchronized (zzaf.class) {
            zzaf.zzSY = this;
        }
    }
    
    protected String zzl(final Object o) {
        if (o == null) {
            return null;
        }
        Object o2;
        if (o instanceof Integer) {
            o2 = new Long((int)o);
        }
        else {
            o2 = o;
        }
        if (o2 instanceof Long) {
            if (Math.abs((long)o2) < 100L) {
                return String.valueOf(o2);
            }
            String s;
            if (String.valueOf(o2).charAt(0) == '-') {
                s = "-";
            }
            else {
                s = "";
            }
            final String value = String.valueOf(Math.abs((long)o2));
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append(Math.round(Math.pow(10.0, -1 + value.length())));
            sb.append("...");
            sb.append(s);
            sb.append(Math.round(Math.pow(10.0, value.length()) - 1.0));
            return sb.toString();
        }
        else {
            if (o2 instanceof Boolean) {
                return String.valueOf(o2);
            }
            if (o2 instanceof Throwable) {
                return ((Long)o2).getClass().getCanonicalName();
            }
            return "-";
        }
    }
}
