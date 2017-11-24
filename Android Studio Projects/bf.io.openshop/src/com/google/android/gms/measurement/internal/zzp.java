package com.google.android.gms.measurement.internal;

import android.text.*;
import com.google.android.gms.measurement.*;
import android.content.*;
import android.util.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.internal.*;

public class zzp extends zzz
{
    private final long zzaVj;
    private final char zzaWB;
    private final zza zzaWC;
    private final zza zzaWD;
    private final zza zzaWE;
    private final zza zzaWF;
    private final zza zzaWG;
    private final zza zzaWH;
    private final zza zzaWI;
    private final zza zzaWJ;
    private final zza zzaWK;
    private final String zzamn;
    
    zzp(final zzw zzw) {
        super(zzw);
        this.zzamn = this.zzCp().zzBz();
        this.zzaVj = this.zzCp().zzBp();
        if (this.zzCp().zzks()) {
            char zzaWB;
            if (this.zzCp().zzkr()) {
                zzaWB = 'P';
            }
            else {
                zzaWB = 'C';
            }
            this.zzaWB = zzaWB;
        }
        else {
            char zzaWB2;
            if (this.zzCp().zzkr()) {
                zzaWB2 = 'p';
            }
            else {
                zzaWB2 = 'c';
            }
            this.zzaWB = zzaWB2;
        }
        this.zzaWC = new zza(6, false, false);
        this.zzaWD = new zza(6, true, false);
        this.zzaWE = new zza(6, false, true);
        this.zzaWF = new zza(5, false, false);
        this.zzaWG = new zza(5, true, false);
        this.zzaWH = new zza(5, false, true);
        this.zzaWI = new zza(4, false, false);
        this.zzaWJ = new zza(3, false, false);
        this.zzaWK = new zza(2, false, false);
    }
    
    static String zza(final boolean b, String s, final Object o, final Object o2, final Object o3) {
        if (s == null) {
            s = "";
        }
        final String zzc = zzc(b, o);
        final String zzc2 = zzc(b, o2);
        final String zzc3 = zzc(b, o3);
        final StringBuilder sb = new StringBuilder();
        String s2 = "";
        if (!TextUtils.isEmpty((CharSequence)s)) {
            sb.append(s);
            s2 = ": ";
        }
        if (!TextUtils.isEmpty((CharSequence)zzc)) {
            sb.append(s2);
            sb.append(zzc);
            s2 = ", ";
        }
        if (!TextUtils.isEmpty((CharSequence)zzc2)) {
            sb.append(s2);
            sb.append(zzc2);
            s2 = ", ";
        }
        if (!TextUtils.isEmpty((CharSequence)zzc3)) {
            sb.append(s2);
            sb.append(zzc3);
        }
        return sb.toString();
    }
    
    static String zzc(final boolean b, final Object o) {
        if (o == null) {
            return "";
        }
        Object value;
        if (o instanceof Integer) {
            value = o;
        }
        else {
            value = o;
        }
        if (value instanceof Long) {
            if (!b) {
                return String.valueOf(value);
            }
            if (Math.abs((long)value) < 100L) {
                return String.valueOf(value);
            }
            String s;
            if (String.valueOf(value).charAt(0) == '-') {
                s = "-";
            }
            else {
                s = "";
            }
            final String value2 = String.valueOf(Math.abs((long)value));
            return s + Math.round(Math.pow(10.0, -1 + value2.length())) + "..." + s + Math.round(Math.pow(10.0, value2.length()) - 1.0);
        }
        else {
            if (value instanceof Boolean) {
                return String.valueOf(value);
            }
            if (value instanceof Throwable) {
                final Throwable t = (Throwable)value;
                final StringBuilder sb = new StringBuilder(t.toString());
                final String zzff = zzff(AppMeasurement.class.getCanonicalName());
                final String zzff2 = zzff(zzw.class.getCanonicalName());
                for (final StackTraceElement stackTraceElement : t.getStackTrace()) {
                    if (!stackTraceElement.isNativeMethod()) {
                        final String className = stackTraceElement.getClassName();
                        if (className != null) {
                            final String zzff3 = zzff(className);
                            if (zzff3.equals(zzff) || zzff3.equals(zzff2)) {
                                sb.append(": ");
                                sb.append(stackTraceElement);
                                break;
                            }
                        }
                    }
                }
                return sb.toString();
            }
            if (b) {
                return "-";
            }
            return String.valueOf(value);
        }
    }
    
    private static String zzff(String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            s = "";
        }
        else {
            final int lastIndex = s.lastIndexOf(46);
            if (lastIndex != -1) {
                return s.substring(0, lastIndex);
            }
        }
        return s;
    }
    
    public zza zzCE() {
        return this.zzaWC;
    }
    
    public zza zzCF() {
        return this.zzaWF;
    }
    
    public zza zzCG() {
        return this.zzaWG;
    }
    
    public zza zzCH() {
        return this.zzaWH;
    }
    
    public zza zzCI() {
        return this.zzaWI;
    }
    
    public zza zzCJ() {
        return this.zzaWJ;
    }
    
    public zza zzCK() {
        return this.zzaWK;
    }
    
    public String zzCL() {
        final Pair<String, Long> zzlN = this.zzCo().zzaXi.zzlN();
        if (zzlN == null) {
            return null;
        }
        return String.valueOf(zzlN.second) + ":" + (String)zzlN.first;
    }
    
    protected boolean zzQ(final int n) {
        return Log.isLoggable(this.zzamn, n);
    }
    
    protected void zza(final int n, final boolean b, final boolean b2, final String s, final Object o, final Object o2, final Object o3) {
        if (!b && this.zzQ(n)) {
            this.zzl(n, zza(false, s, o, o2, o3));
        }
        if (!b2 && n >= 5) {
            this.zzb(n, s, o, o2, o3);
        }
    }
    
    public void zzb(int n, final String s, final Object o, final Object o2, final Object o3) {
        zzx.zzz(s);
        final zzv zzCU = this.zzaTV.zzCU();
        if (zzCU == null) {
            this.zzl(6, "Scheduler not set. Not logging error/warn.");
            return;
        }
        if (!zzCU.isInitialized()) {
            this.zzl(6, "Scheduler not initialized. Not logging error/warn.");
            return;
        }
        if (zzCU.zzDi()) {
            this.zzl(6, "Scheduler shutdown. Not logging error/warn.");
            return;
        }
        if (n < 0) {
            n = 0;
        }
        if (n >= "01VDIWEA?".length()) {
            n = -1 + "01VDIWEA?".length();
        }
        final String string = "1" + "01VDIWEA?".charAt(n) + this.zzaWB + this.zzaVj + ":" + zza(true, s, o, o2, o3);
        String substring;
        if (string.length() > 1024) {
            substring = s.substring(0, 1024);
        }
        else {
            substring = string;
        }
        zzCU.zzg(new Runnable() {
            @Override
            public void run() {
                final zzt zzCo = zzp.this.zzaTV.zzCo();
                if (!zzCo.isInitialized() || zzCo.zzDi()) {
                    zzp.this.zzl(6, "Persisted config not initialized . Not logging error/warn.");
                    return;
                }
                zzCo.zzaXi.zzbq(substring);
            }
        });
    }
    
    @Override
    protected void zziJ() {
    }
    
    protected void zzl(final int n, final String s) {
        Log.println(n, this.zzamn, s);
    }
    
    public class zza
    {
        private final int mPriority;
        private final boolean zzaWN;
        private final boolean zzaWO;
        
        zza(final int mPriority, final boolean zzaWN, final boolean zzaWO) {
            this.mPriority = mPriority;
            this.zzaWN = zzaWN;
            this.zzaWO = zzaWO;
        }
        
        public void zzd(final String s, final Object o, final Object o2, final Object o3) {
            zzp.this.zza(this.mPriority, this.zzaWN, this.zzaWO, s, o, o2, o3);
        }
        
        public void zze(final String s, final Object o, final Object o2) {
            zzp.this.zza(this.mPriority, this.zzaWN, this.zzaWO, s, o, o2, null);
        }
        
        public void zzfg(final String s) {
            zzp.this.zza(this.mPriority, this.zzaWN, this.zzaWO, s, null, null, null);
        }
        
        public void zzj(final String s, final Object o) {
            zzp.this.zza(this.mPriority, this.zzaWN, this.zzaWO, s, o, null, null);
        }
    }
}
