package com.google.android.gms.internal;

import android.content.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import android.util.*;
import com.google.android.gms.common.stats.*;
import android.text.*;

public class zzrp
{
    private static boolean DEBUG;
    private static String TAG;
    private static String zzbhl;
    private final Context mContext;
    private final String zzanQ;
    private final PowerManager$WakeLock zzbhm;
    private WorkSource zzbhn;
    private final int zzbho;
    private final String zzbhp;
    private boolean zzbhq;
    private int zzbhr;
    private int zzbhs;
    
    static {
        zzrp.TAG = "WakeLock";
        zzrp.zzbhl = "*gcore*:";
        zzrp.DEBUG = false;
    }
    
    public zzrp(final Context context, final int n, final String s) {
        String packageName;
        if (context == null) {
            packageName = null;
        }
        else {
            packageName = context.getPackageName();
        }
        this(context, n, s, null, packageName);
    }
    
    public zzrp(final Context context, final int zzbho, final String zzanQ, final String zzbhp, String packageName) {
        this.zzbhq = true;
        zzx.zzh(zzanQ, "Wake lock name can NOT be empty");
        this.zzbho = zzbho;
        this.zzbhp = zzbhp;
        this.mContext = context.getApplicationContext();
        if (!zzni.zzcV(packageName) && "com.google.android.gms" != packageName) {
            this.zzanQ = zzrp.zzbhl + zzanQ;
        }
        else {
            this.zzanQ = zzanQ;
        }
        this.zzbhm = ((PowerManager)context.getSystemService("power")).newWakeLock(zzbho, zzanQ);
        if (zznj.zzaA(this.mContext)) {
            if (zzni.zzcV(packageName)) {
                if (zzd.zzakE && zzlz.isInitialized()) {
                    Log.e(zzrp.TAG, "callingPackage is not supposed to be empty for wakelock " + this.zzanQ + "!", (Throwable)new IllegalArgumentException());
                    packageName = "com.google.android.gms";
                }
                else {
                    packageName = context.getPackageName();
                }
            }
            this.zzc(this.zzbhn = zznj.zzl(context, packageName));
        }
    }
    
    private void zzfJ(final String s) {
        final boolean zzfK = this.zzfK(s);
        final String zzn = this.zzn(s, zzfK);
        if (zzrp.DEBUG) {
            Log.d(zzrp.TAG, "Release:\n mWakeLockName: " + this.zzanQ + "\n mSecondaryName: " + this.zzbhp + "\nmReferenceCounted: " + this.zzbhq + "\nreason: " + s + "\n mOpenEventCount" + this.zzbhs + "\nuseWithReason: " + zzfK + "\ntrackingName: " + zzn);
        }
        synchronized (this) {
            Label_0158: {
                if (this.zzbhq) {
                    final int zzbhr = -1 + this.zzbhr;
                    this.zzbhr = zzbhr;
                    if (zzbhr == 0 || zzfK) {
                        break Label_0158;
                    }
                }
                if (this.zzbhq || this.zzbhs != 1) {
                    return;
                }
            }
            zzi.zzrZ().zza(this.mContext, zzg.zza(this.zzbhm, zzn), 8, this.zzanQ, zzn, this.zzbho, zznj.zzb(this.zzbhn));
            --this.zzbhs;
        }
    }
    
    private boolean zzfK(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && !s.equals(this.zzbhp);
    }
    
    private void zzj(final String s, final long n) {
        final boolean zzfK = this.zzfK(s);
        final String zzn = this.zzn(s, zzfK);
        if (zzrp.DEBUG) {
            Log.d(zzrp.TAG, "Acquire:\n mWakeLockName: " + this.zzanQ + "\n mSecondaryName: " + this.zzbhp + "\nmReferenceCounted: " + this.zzbhq + "\nreason: " + s + "\nmOpenEventCount" + this.zzbhs + "\nuseWithReason: " + zzfK + "\ntrackingName: " + zzn + "\ntimeout: " + n);
        }
        synchronized (this) {
            if ((this.zzbhq && (this.zzbhr++ == 0 || zzfK)) || (!this.zzbhq && this.zzbhs == 0)) {
                zzi.zzrZ().zza(this.mContext, zzg.zza(this.zzbhm, zzn), 7, this.zzanQ, zzn, this.zzbho, zznj.zzb(this.zzbhn), n);
                ++this.zzbhs;
            }
        }
    }
    
    private String zzn(final String s, final boolean b) {
        if (!this.zzbhq) {
            return this.zzbhp;
        }
        if (b) {
            return s;
        }
        return this.zzbhp;
    }
    
    public void acquire(final long n) {
        if (!zzne.zzsg() && this.zzbhq) {
            Log.wtf(zzrp.TAG, "Do not acquire with timeout on reference counted WakeLocks before ICS. wakelock: " + this.zzanQ);
        }
        this.zzj(null, n);
        this.zzbhm.acquire(n);
    }
    
    public boolean isHeld() {
        return this.zzbhm.isHeld();
    }
    
    public void release() {
        this.zzfJ(null);
        this.zzbhm.release();
    }
    
    public void setReferenceCounted(final boolean b) {
        this.zzbhm.setReferenceCounted(b);
        this.zzbhq = b;
    }
    
    public void zzc(final WorkSource zzbhn) {
        if (zznj.zzaA(this.mContext) && zzbhn != null) {
            if (this.zzbhn != null) {
                this.zzbhn.add(zzbhn);
            }
            else {
                this.zzbhn = zzbhn;
            }
            this.zzbhm.setWorkSource(this.zzbhn);
        }
    }
}
