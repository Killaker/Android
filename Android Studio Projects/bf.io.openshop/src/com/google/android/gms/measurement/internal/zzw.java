package com.google.android.gms.measurement.internal;

import android.content.*;
import com.google.android.gms.common.internal.*;
import android.app.*;
import android.support.annotation.*;
import android.text.*;
import android.os.*;
import com.google.android.gms.measurement.*;
import android.util.*;
import java.net.*;
import android.support.v4.util.*;
import com.google.android.gms.internal.*;
import android.content.pm.*;
import java.io.*;
import java.util.*;

public class zzw
{
    private static zzaa zzaXV;
    private static volatile zzw zzaXW;
    private final Context mContext;
    private final boolean zzQk;
    private final zzd zzaXX;
    private final zzt zzaXY;
    private final zzp zzaXZ;
    private final zzv zzaYa;
    private final zzad zzaYb;
    private final zzu zzaYc;
    private final AppMeasurement zzaYd;
    private final zzaj zzaYe;
    private final zze zzaYf;
    private final zzq zzaYg;
    private final zzac zzaYh;
    private final zzg zzaYi;
    private final zzab zzaYj;
    private final zzn zzaYk;
    private final zzr zzaYl;
    private final zzag zzaYm;
    private final zzc zzaYn;
    private Boolean zzaYo;
    private List<Long> zzaYp;
    private int zzaYq;
    private int zzaYr;
    private final zzmq zzqW;
    
    zzw(final zzaa zzaa) {
        zzx.zzz(zzaa);
        this.mContext = zzaa.mContext;
        this.zzqW = zzaa.zzl(this);
        this.zzaXX = zzaa.zza(this);
        final zzt zzb = zzaa.zzb(this);
        zzb.zza();
        this.zzaXY = zzb;
        final zzp zzc = zzaa.zzc(this);
        zzc.zza();
        this.zzaXZ = zzc;
        this.zzAo().zzCI().zzj("App measurement is starting up, version", this.zzCp().zzBp());
        this.zzAo().zzCI().zzfg("To enable debug logging run: adb shell setprop log.tag.GMPM VERBOSE");
        this.zzAo().zzCJ().zzfg("Debug logging enabled");
        this.zzaYe = zzaa.zzi(this);
        final zzg zzn = zzaa.zzn(this);
        zzn.zza();
        this.zzaYi = zzn;
        final zzn zzo = zzaa.zzo(this);
        zzo.zza();
        this.zzaYk = zzo;
        final zze zzj = zzaa.zzj(this);
        zzj.zza();
        this.zzaYf = zzj;
        final zzc zzr = zzaa.zzr(this);
        zzr.zza();
        this.zzaYn = zzr;
        final zzq zzk = zzaa.zzk(this);
        zzk.zza();
        this.zzaYg = zzk;
        final zzac zzm = zzaa.zzm(this);
        zzm.zza();
        this.zzaYh = zzm;
        final zzab zzh = zzaa.zzh(this);
        zzh.zza();
        this.zzaYj = zzh;
        final zzag zzq = zzaa.zzq(this);
        zzq.zza();
        this.zzaYm = zzq;
        this.zzaYl = zzaa.zzp(this);
        this.zzaYd = zzaa.zzg(this);
        final zzad zze = zzaa.zze(this);
        zze.zza();
        this.zzaYb = zze;
        final zzu zzf = zzaa.zzf(this);
        zzf.zza();
        this.zzaYc = zzf;
        final zzv zzd = zzaa.zzd(this);
        zzd.zza();
        this.zzaYa = zzd;
        if (this.zzaYq != this.zzaYr) {
            this.zzAo().zzCE().zze("Not all components initialized", this.zzaYq, this.zzaYr);
        }
        this.zzQk = true;
        if (!this.zzaXX.zzkr() && !this.zzCZ()) {
            if (this.mContext.getApplicationContext() instanceof Application) {
                if (Build$VERSION.SDK_INT >= 14) {
                    this.zzCf().zzDk();
                }
                else {
                    this.zzAo().zzCJ().zzfg("Not tracking deep linking pre-ICS");
                }
            }
            else {
                this.zzAo().zzCF().zzfg("Application context is not an Application");
            }
        }
        this.zzaYa.zzg(new Runnable() {
            @Override
            public void run() {
                zzw.this.start();
            }
        });
    }
    
    private void zzA(final List<Long> list) {
        zzx.zzac(!list.isEmpty());
        if (this.zzaYp != null) {
            this.zzAo().zzCE().zzfg("Set uploading progress before finishing the previous upload");
            return;
        }
        this.zzaYp = new ArrayList<Long>(list);
    }
    
    @WorkerThread
    private boolean zzDb() {
        this.zzjk();
        return this.zzaYp != null;
    }
    
    private boolean zzDd() {
        this.zzjk();
        this.zzjv();
        return this.zzCj().zzCv() || !TextUtils.isEmpty((CharSequence)this.zzCj().zzCq());
    }
    
    @WorkerThread
    private void zzDe() {
        this.zzjk();
        this.zzjv();
        if (!this.zzCS() || !this.zzDd()) {
            this.zzCX().unregister();
            this.zzCY().cancel();
            return;
        }
        long n = this.zzDf();
        if (n == 0L) {
            this.zzCX().unregister();
            this.zzCY().cancel();
            return;
        }
        if (!this.zzCW().zzlB()) {
            this.zzCX().zzly();
            this.zzCY().cancel();
            return;
        }
        final long value = this.zzCo().zzaXl.get();
        final long zzBX = this.zzCp().zzBX();
        if (!this.zzCk().zzc(value, zzBX)) {
            n = Math.max(n, value + zzBX);
        }
        this.zzCX().unregister();
        final long n2 = n - this.zzjl().currentTimeMillis();
        if (n2 <= 0L) {
            this.zzCY().zzt(1L);
            return;
        }
        this.zzAo().zzCK().zzj("Upload scheduled in approximately ms", n2);
        this.zzCY().zzt(n2);
    }
    
    private long zzDf() {
        final long currentTimeMillis = this.zzjl().currentTimeMillis();
        final long zzCa = this.zzCp().zzCa();
        final long zzBY = this.zzCp().zzBY();
        final long value = this.zzCo().zzaXj.get();
        final long value2 = this.zzCo().zzaXk.get();
        final long max = Math.max(this.zzCj().zzCt(), this.zzCj().zzCu());
        long n;
        if (max == 0L) {
            n = 0L;
        }
        else {
            final long n2 = currentTimeMillis - Math.abs(max - currentTimeMillis);
            final long n3 = currentTimeMillis - Math.abs(value - currentTimeMillis);
            final long n4 = currentTimeMillis - Math.abs(value2 - currentTimeMillis);
            final long max2 = Math.max(n3, n4);
            n = zzCa + n2;
            if (!this.zzCk().zzc(max2, zzBY)) {
                n = max2 + zzBY;
            }
            if (n4 != 0L && n4 >= n2) {
                for (int i = 0; i < this.zzCp().zzCc(); ++i) {
                    n += (1 << i) * this.zzCp().zzCb();
                    if (n > n4) {
                        return n;
                    }
                }
                return 0L;
            }
        }
        return n;
    }
    
    @WorkerThread
    private void zza(final int n, final Throwable t, byte[] array) {
        this.zzjk();
        this.zzjv();
        if (array == null) {
            array = new byte[0];
        }
        final List<Long> zzaYp = this.zzaYp;
        this.zzaYp = null;
        if ((n != 200 && n != 204) || t != null) {
            this.zzAo().zzCK().zze("Network upload failed. Will retry later. code, error", n, t);
            this.zzCo().zzaXk.set(this.zzjl().currentTimeMillis());
            boolean b = false;
            Label_0264: {
                if (n != 503) {
                    b = false;
                    if (n != 429) {
                        break Label_0264;
                    }
                }
                b = true;
            }
            if (b) {
                this.zzCo().zzaXl.set(this.zzjl().currentTimeMillis());
            }
            this.zzDe();
            return;
        }
        this.zzCo().zzaXj.set(this.zzjl().currentTimeMillis());
        this.zzCo().zzaXk.set(0L);
        this.zzDe();
        this.zzAo().zzCK().zze("Successful upload. Got network response. code, size", n, array.length);
        this.zzCj().beginTransaction();
        try {
            final Iterator<Long> iterator = zzaYp.iterator();
            while (iterator.hasNext()) {
                this.zzCj().zzZ(iterator.next());
            }
        }
        finally {
            this.zzCj().endTransaction();
        }
        this.zzCj().setTransactionSuccessful();
        this.zzCj().endTransaction();
        if (this.zzCW().zzlB() && this.zzDd()) {
            this.zzDc();
            return;
        }
        this.zzDe();
    }
    
    private void zza(final zzy zzy) {
        if (zzy == null) {
            throw new IllegalStateException("Component not created");
        }
    }
    
    private void zza(final zzz zzz) {
        if (zzz == null) {
            throw new IllegalStateException("Component not created");
        }
        if (!zzz.isInitialized()) {
            throw new IllegalStateException("Component not initialized");
        }
    }
    
    private zzqb.zza[] zza(final String s, final zzqb.zzg[] array, final zzqb.zzb[] array2) {
        zzx.zzcM(s);
        return this.zzCe().zza(s, array2, array);
    }
    
    public static zzw zzaT(final Context context) {
        zzx.zzz(context);
        zzx.zzz(context.getApplicationContext());
        Label_0050: {
            if (zzw.zzaXW != null) {
                break Label_0050;
            }
            synchronized (zzw.class) {
                if (zzw.zzaXW == null) {
                    zzaa zzaXV;
                    if (zzw.zzaXV != null) {
                        zzaXV = zzw.zzaXV;
                    }
                    else {
                        zzaXV = new zzaa(context);
                    }
                    zzw.zzaXW = zzaXV.zzDj();
                }
                return zzw.zzaXW;
            }
        }
    }
    
    private void zzb(final Bundle bundle, final int n) {
        if (bundle.getLong("_err") == 0L) {
            bundle.putLong("_err", (long)n);
        }
    }
    
    @WorkerThread
    private void zzb(final String p0, final int p1, final Throwable p2, final byte[] p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzjk:()V
        //     4: aload_0        
        //     5: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzjv:()V
        //     8: aload_1        
        //     9: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    12: pop            
        //    13: aload           4
        //    15: ifnonnull       23
        //    18: iconst_0       
        //    19: newarray        B
        //    21: astore          4
        //    23: aload_0        
        //    24: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCj:()Lcom/google/android/gms/measurement/internal/zze;
        //    27: invokevirtual   com/google/android/gms/measurement/internal/zze.beginTransaction:()V
        //    30: aload_0        
        //    31: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCj:()Lcom/google/android/gms/measurement/internal/zze;
        //    34: aload_1        
        //    35: invokevirtual   com/google/android/gms/measurement/internal/zze.zzeY:(Ljava/lang/String;)Lcom/google/android/gms/measurement/internal/zza;
        //    38: astore          7
        //    40: iload_2        
        //    41: sipush          200
        //    44: if_icmpeq       365
        //    47: iload_2        
        //    48: sipush          204
        //    51: if_icmpeq       365
        //    54: iload_2        
        //    55: sipush          304
        //    58: if_icmpne       99
        //    61: goto            365
        //    64: aload_0        
        //    65: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCl:()Lcom/google/android/gms/measurement/internal/zzu;
        //    68: aload_1        
        //    69: invokevirtual   com/google/android/gms/measurement/internal/zzu.zzfk:(Ljava/lang/String;)Lcom/google/android/gms/internal/zzqa$zzb;
        //    72: ifnonnull       130
        //    75: aload_0        
        //    76: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCl:()Lcom/google/android/gms/measurement/internal/zzu;
        //    79: aload_1        
        //    80: aconst_null    
        //    81: invokevirtual   com/google/android/gms/measurement/internal/zzu.zze:(Ljava/lang/String;[B)Z
        //    84: istore          9
        //    86: iload           9
        //    88: ifne            130
        //    91: aload_0        
        //    92: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCj:()Lcom/google/android/gms/measurement/internal/zze;
        //    95: invokevirtual   com/google/android/gms/measurement/internal/zze.endTransaction:()V
        //    98: return         
        //    99: iconst_0       
        //   100: istore          8
        //   102: goto            372
        //   105: aload_0        
        //   106: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCl:()Lcom/google/android/gms/measurement/internal/zzu;
        //   109: aload_1        
        //   110: aload           4
        //   112: invokevirtual   com/google/android/gms/measurement/internal/zzu.zze:(Ljava/lang/String;[B)Z
        //   115: istore          10
        //   117: iload           10
        //   119: ifne            130
        //   122: aload_0        
        //   123: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCj:()Lcom/google/android/gms/measurement/internal/zze;
        //   126: invokevirtual   com/google/android/gms/measurement/internal/zze.endTransaction:()V
        //   129: return         
        //   130: aload           7
        //   132: aload_0        
        //   133: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzjl:()Lcom/google/android/gms/internal/zzmq;
        //   136: invokeinterface com/google/android/gms/internal/zzmq.currentTimeMillis:()J
        //   141: invokevirtual   com/google/android/gms/measurement/internal/zza.zzT:(J)V
        //   144: aload_0        
        //   145: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCj:()Lcom/google/android/gms/measurement/internal/zze;
        //   148: aload           7
        //   150: invokevirtual   com/google/android/gms/measurement/internal/zze.zza:(Lcom/google/android/gms/measurement/internal/zza;)V
        //   153: iload_2        
        //   154: sipush          404
        //   157: if_icmpne       209
        //   160: aload_0        
        //   161: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   164: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCF:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   167: ldc_w           "Config not found. Using empty config"
        //   170: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   173: aload_0        
        //   174: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCW:()Lcom/google/android/gms/measurement/internal/zzq;
        //   177: invokevirtual   com/google/android/gms/measurement/internal/zzq.zzlB:()Z
        //   180: ifeq            247
        //   183: aload_0        
        //   184: invokespecial   com/google/android/gms/measurement/internal/zzw.zzDd:()Z
        //   187: ifeq            247
        //   190: aload_0        
        //   191: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzDc:()V
        //   194: aload_0        
        //   195: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCj:()Lcom/google/android/gms/measurement/internal/zze;
        //   198: invokevirtual   com/google/android/gms/measurement/internal/zze.setTransactionSuccessful:()V
        //   201: aload_0        
        //   202: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCj:()Lcom/google/android/gms/measurement/internal/zze;
        //   205: invokevirtual   com/google/android/gms/measurement/internal/zze.endTransaction:()V
        //   208: return         
        //   209: aload_0        
        //   210: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   213: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCK:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   216: ldc_w           "Successfully fetched config. Got network response. code, size"
        //   219: iload_2        
        //   220: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   223: aload           4
        //   225: arraylength    
        //   226: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   229: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   232: goto            173
        //   235: astore          6
        //   237: aload_0        
        //   238: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCj:()Lcom/google/android/gms/measurement/internal/zze;
        //   241: invokevirtual   com/google/android/gms/measurement/internal/zze.endTransaction:()V
        //   244: aload           6
        //   246: athrow         
        //   247: aload_0        
        //   248: invokespecial   com/google/android/gms/measurement/internal/zzw.zzDe:()V
        //   251: goto            194
        //   254: aload           7
        //   256: aload_0        
        //   257: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzjl:()Lcom/google/android/gms/internal/zzmq;
        //   260: invokeinterface com/google/android/gms/internal/zzmq.currentTimeMillis:()J
        //   265: invokevirtual   com/google/android/gms/measurement/internal/zza.zzU:(J)V
        //   268: aload_0        
        //   269: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCj:()Lcom/google/android/gms/measurement/internal/zze;
        //   272: aload           7
        //   274: invokevirtual   com/google/android/gms/measurement/internal/zze.zza:(Lcom/google/android/gms/measurement/internal/zza;)V
        //   277: aload_0        
        //   278: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   281: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCK:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   284: ldc_w           "Fetching config failed. code, error"
        //   287: iload_2        
        //   288: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   291: aload_3        
        //   292: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   295: aload_0        
        //   296: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCo:()Lcom/google/android/gms/measurement/internal/zzt;
        //   299: getfield        com/google/android/gms/measurement/internal/zzt.zzaXk:Lcom/google/android/gms/measurement/internal/zzt$zzb;
        //   302: aload_0        
        //   303: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzjl:()Lcom/google/android/gms/internal/zzmq;
        //   306: invokeinterface com/google/android/gms/internal/zzmq.currentTimeMillis:()J
        //   311: invokevirtual   com/google/android/gms/measurement/internal/zzt$zzb.set:(J)V
        //   314: iload_2        
        //   315: sipush          503
        //   318: if_icmpeq       401
        //   321: iconst_0       
        //   322: istore          11
        //   324: iload_2        
        //   325: sipush          429
        //   328: if_icmpne       334
        //   331: goto            401
        //   334: iload           11
        //   336: ifeq            358
        //   339: aload_0        
        //   340: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCo:()Lcom/google/android/gms/measurement/internal/zzt;
        //   343: getfield        com/google/android/gms/measurement/internal/zzt.zzaXl:Lcom/google/android/gms/measurement/internal/zzt$zzb;
        //   346: aload_0        
        //   347: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzjl:()Lcom/google/android/gms/internal/zzmq;
        //   350: invokeinterface com/google/android/gms/internal/zzmq.currentTimeMillis:()J
        //   355: invokevirtual   com/google/android/gms/measurement/internal/zzt$zzb.set:(J)V
        //   358: aload_0        
        //   359: invokespecial   com/google/android/gms/measurement/internal/zzw.zzDe:()V
        //   362: goto            194
        //   365: aload_3        
        //   366: ifnonnull       99
        //   369: iconst_1       
        //   370: istore          8
        //   372: iload           8
        //   374: ifne            384
        //   377: iload_2        
        //   378: sipush          404
        //   381: if_icmpne       254
        //   384: iload_2        
        //   385: sipush          404
        //   388: if_icmpeq       64
        //   391: iload_2        
        //   392: sipush          304
        //   395: if_icmpne       105
        //   398: goto            64
        //   401: iconst_1       
        //   402: istore          11
        //   404: goto            334
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     40     235    247    Any
        //  64     86     235    247    Any
        //  105    117    235    247    Any
        //  130    153    235    247    Any
        //  160    173    235    247    Any
        //  173    194    235    247    Any
        //  194    201    235    247    Any
        //  209    232    235    247    Any
        //  247    251    235    247    Any
        //  254    314    235    247    Any
        //  339    358    235    247    Any
        //  358    362    235    247    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @WorkerThread
    private void zze(final AppMetadata appMetadata) {
        int n = 1;
        this.zzjk();
        this.zzjv();
        zzx.zzz(appMetadata);
        zzx.zzcM(appMetadata.packageName);
        com.google.android.gms.measurement.internal.zza zzeY = this.zzCj().zzeY(appMetadata.packageName);
        final String zzfi = this.zzCo().zzfi(appMetadata.packageName);
        int n2;
        if (zzeY == null) {
            final com.google.android.gms.measurement.internal.zza zza = new com.google.android.gms.measurement.internal.zza(this, appMetadata.packageName);
            zza.zzeM(this.zzCo().zzCM());
            zza.zzeO(zzfi);
            zzeY = zza;
            n2 = n;
        }
        else {
            final boolean equals = zzfi.equals(zzeY.zzBl());
            n2 = 0;
            if (!equals) {
                zzeY.zzeO(zzfi);
                zzeY.zzeM(this.zzCo().zzCM());
                n2 = n;
            }
        }
        if (!TextUtils.isEmpty((CharSequence)appMetadata.zzaVt) && !appMetadata.zzaVt.equals(zzeY.zzBk())) {
            zzeY.zzeN(appMetadata.zzaVt);
            n2 = n;
        }
        if (appMetadata.zzaVv != 0L && appMetadata.zzaVv != zzeY.zzBp()) {
            zzeY.zzQ(appMetadata.zzaVv);
            n2 = n;
        }
        if (!TextUtils.isEmpty((CharSequence)appMetadata.zzaMV) && !appMetadata.zzaMV.equals(zzeY.zzli())) {
            zzeY.setAppVersion(appMetadata.zzaMV);
            n2 = n;
        }
        if (!TextUtils.isEmpty((CharSequence)appMetadata.zzaVu) && !appMetadata.zzaVu.equals(zzeY.zzBo())) {
            zzeY.zzeP(appMetadata.zzaVu);
            n2 = n;
        }
        if (appMetadata.zzaVw != zzeY.zzBq()) {
            zzeY.zzR(appMetadata.zzaVw);
            n2 = n;
        }
        if (appMetadata.zzaVy != zzeY.zzAr()) {
            zzeY.setMeasurementEnabled(appMetadata.zzaVy);
        }
        else {
            n = n2;
        }
        if (n != 0) {
            this.zzCj().zza(zzeY);
        }
    }
    
    private boolean zzg(final String s, final long n) {
    Label_0293_Outer:
        while (true) {
            this.zzCj().beginTransaction();
            while (true) {
                int n6 = 0;
            Label_0516_Outer:
                while (true) {
                    int n3 = 0;
                    int n4 = 0;
                    Label_0641: {
                        while (true) {
                            long zzBm = 0L;
                            Label_0634: {
                                while (true) {
                                Label_0612:
                                    while (true) {
                                        Label_0606: {
                                            try {
                                                final zza zza = new zza();
                                                this.zzCj().zza(s, n, (zze.zzb)zza);
                                                if (zza.isEmpty()) {
                                                    break;
                                                }
                                                final zzqb.zze zzaYt = zza.zzaYt;
                                                zzaYt.zzbam = new zzqb.zzb[zza.zzpH.size()];
                                                final int n2 = 0;
                                                n3 = 0;
                                                if (n3 < zza.zzpH.size()) {
                                                    if (this.zzCl().zzP(zza.zzaYt.appId, zza.zzpH.get(n3).name)) {
                                                        this.zzAo().zzCK().zzj("Dropping blacklisted raw event", zza.zzpH.get(n3).name);
                                                        n4 = n2;
                                                        break Label_0641;
                                                    }
                                                    final zzqb.zzb[] zzbam = zzaYt.zzbam;
                                                    final int n5 = n2 + 1;
                                                    zzbam[n2] = zza.zzpH.get(n3);
                                                    n4 = n5;
                                                    break Label_0641;
                                                }
                                                else {
                                                    if (n2 < zza.zzpH.size()) {
                                                        zzaYt.zzbam = Arrays.copyOf(zzaYt.zzbam, n2);
                                                    }
                                                    zzaYt.zzbaF = this.zza(zza.zzaYt.appId, zza.zzaYt.zzban, zzaYt.zzbam);
                                                    zzaYt.zzbap = zzaYt.zzbam[0].zzbaf;
                                                    zzaYt.zzbaq = zzaYt.zzbam[0].zzbaf;
                                                    n6 = 1;
                                                    if (n6 >= zzaYt.zzbam.length) {
                                                        final String appId = zza.zzaYt.appId;
                                                        final com.google.android.gms.measurement.internal.zza zzeY = this.zzCj().zzeY(appId);
                                                        if (zzeY == null) {
                                                            this.zzAo().zzCE().zzfg("Bundling raw events w/o app info");
                                                        }
                                                        else {
                                                            final long zzBn = zzeY.zzBn();
                                                            if (zzBn == 0L) {
                                                                break Label_0606;
                                                            }
                                                            final Long value = zzBn;
                                                            zzaYt.zzbas = value;
                                                            zzBm = zzeY.zzBm();
                                                            if (zzBm != 0L) {
                                                                break Label_0634;
                                                            }
                                                            if (zzBn == 0L) {
                                                                break Label_0612;
                                                            }
                                                            final Long value2 = zzBn;
                                                            zzaYt.zzbar = value2;
                                                            zzeY.zzBu();
                                                            zzaYt.zzbaD = (int)zzeY.zzBr();
                                                            zzeY.zzO(zzaYt.zzbap);
                                                            zzeY.zzP(zzaYt.zzbaq);
                                                            this.zzCj().zza(zzeY);
                                                        }
                                                        zzaYt.zzaVx = this.zzAo().zzCL();
                                                        this.zzCj().zza(zzaYt);
                                                        this.zzCj().zzz(zza.zzaYu);
                                                        this.zzCj().zzfc(appId);
                                                        this.zzCj().setTransactionSuccessful();
                                                        return true;
                                                    }
                                                    final zzqb.zzb zzb = zzaYt.zzbam[n6];
                                                    if (zzb.zzbaf < zzaYt.zzbap) {
                                                        zzaYt.zzbap = zzb.zzbaf;
                                                    }
                                                    if (zzb.zzbaf > zzaYt.zzbaq) {
                                                        zzaYt.zzbaq = zzb.zzbaf;
                                                    }
                                                    break Label_0516_Outer;
                                                }
                                            }
                                            finally {
                                                this.zzCj().endTransaction();
                                            }
                                        }
                                        final Long value = null;
                                        continue Label_0516_Outer;
                                    }
                                    final Long value2 = null;
                                    continue;
                                }
                            }
                            final long zzBn = zzBm;
                            continue;
                        }
                    }
                    ++n3;
                    final int n2 = n4;
                    continue Label_0293_Outer;
                }
                ++n6;
                continue;
            }
        }
        this.zzCj().setTransactionSuccessful();
        this.zzCj().endTransaction();
        return false;
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    @WorkerThread
    protected void start() {
        this.zzjk();
        if (this.zzCZ() && (!this.zzaYa.isInitialized() || this.zzaYa.zzDi())) {
            this.zzAo().zzCE().zzfg("Scheduler shutting down before Scion.start() called");
            return;
        }
        this.zzCj().zzCr();
        if (!this.zzCS()) {
            if (this.zzCo().zzAr()) {
                if (!this.zzCk().zzbk("android.permission.INTERNET")) {
                    this.zzAo().zzCE().zzfg("App is missing INTERNET permission");
                }
                if (!this.zzCk().zzbk("android.permission.ACCESS_NETWORK_STATE")) {
                    this.zzAo().zzCE().zzfg("App is missing ACCESS_NETWORK_STATE permission");
                }
                if (!AppMeasurementReceiver.zzY(this.getContext())) {
                    this.zzAo().zzCE().zzfg("AppMeasurementReceiver not registered/enabled");
                }
                if (!AppMeasurementService.zzZ(this.getContext())) {
                    this.zzAo().zzCE().zzfg("AppMeasurementService not registered/enabled");
                }
                this.zzAo().zzCE().zzfg("Uploading is not possible. App measurement disabled");
            }
        }
        else if (!this.zzCp().zzkr() && !this.zzCZ() && !TextUtils.isEmpty((CharSequence)this.zzCg().zzBk())) {
            this.zzCf().zzDl();
        }
        this.zzDe();
    }
    
    public zzp zzAo() {
        this.zza(this.zzaXZ);
        return this.zzaXZ;
    }
    
    @WorkerThread
    protected boolean zzCS() {
        boolean b = true;
        this.zzjv();
        this.zzjk();
        if (this.zzaYo == null) {
            this.zzaYo = (this.zzCk().zzbk("android.permission.INTERNET") && this.zzCk().zzbk("android.permission.ACCESS_NETWORK_STATE") && AppMeasurementReceiver.zzY(this.getContext()) && AppMeasurementService.zzZ(this.getContext()) && b);
            if (this.zzaYo && !this.zzCp().zzkr()) {
                if (TextUtils.isEmpty((CharSequence)this.zzCg().zzBk())) {
                    b = false;
                }
                this.zzaYo = b;
            }
        }
        return this.zzaYo;
    }
    
    public zzp zzCT() {
        if (this.zzaXZ != null && this.zzaXZ.isInitialized()) {
            return this.zzaXZ;
        }
        return null;
    }
    
    zzv zzCU() {
        return this.zzaYa;
    }
    
    public AppMeasurement zzCV() {
        return this.zzaYd;
    }
    
    public zzq zzCW() {
        this.zza(this.zzaYg);
        return this.zzaYg;
    }
    
    public zzr zzCX() {
        if (this.zzaYl == null) {
            throw new IllegalStateException("Network broadcast receiver not created");
        }
        return this.zzaYl;
    }
    
    public zzag zzCY() {
        this.zza(this.zzaYm);
        return this.zzaYm;
    }
    
    protected boolean zzCZ() {
        return false;
    }
    
    public zzc zzCe() {
        this.zza(this.zzaYn);
        return this.zzaYn;
    }
    
    public zzab zzCf() {
        this.zza(this.zzaYj);
        return this.zzaYj;
    }
    
    public zzn zzCg() {
        this.zza(this.zzaYk);
        return this.zzaYk;
    }
    
    public zzg zzCh() {
        this.zza(this.zzaYi);
        return this.zzaYi;
    }
    
    public zzac zzCi() {
        this.zza(this.zzaYh);
        return this.zzaYh;
    }
    
    public zze zzCj() {
        this.zza(this.zzaYf);
        return this.zzaYf;
    }
    
    public zzaj zzCk() {
        this.zza(this.zzaYe);
        return this.zzaYe;
    }
    
    public zzu zzCl() {
        this.zza(this.zzaYc);
        return this.zzaYc;
    }
    
    public zzad zzCm() {
        this.zza(this.zzaYb);
        return this.zzaYb;
    }
    
    public zzv zzCn() {
        this.zza(this.zzaYa);
        return this.zzaYa;
    }
    
    public zzt zzCo() {
        this.zza((zzy)this.zzaXY);
        return this.zzaXY;
    }
    
    public zzd zzCp() {
        return this.zzaXX;
    }
    
    long zzDa() {
        return (this.zzjl().currentTimeMillis() + this.zzCo().zzCN()) / 1000L / 60L / 60L / 24L;
    }
    
    @WorkerThread
    public void zzDc() {
        int i = 0;
        this.zzjk();
        this.zzjv();
        Label_0070: {
            if (this.zzCp().zzkr()) {
                break Label_0070;
            }
            final Boolean zzCP = this.zzCo().zzCP();
            if (zzCP == null) {
                this.zzAo().zzCF().zzfg("Upload data called on the client side before use of service was decided");
            }
            else {
                if (zzCP) {
                    this.zzAo().zzCE().zzfg("Upload called in the client side when service should be used");
                    return;
                }
                break Label_0070;
            }
            return;
        }
        if (this.zzDb()) {
            this.zzAo().zzCF().zzfg("Uploading requested multiple times");
            return;
        }
        if (!this.zzCW().zzlB()) {
            this.zzAo().zzCF().zzfg("Network not connected, ignoring upload request");
            this.zzDe();
            return;
        }
        final long currentTimeMillis = this.zzjl().currentTimeMillis();
        this.zzad(currentTimeMillis - this.zzCp().zzBW());
        final long value = this.zzCo().zzaXj.get();
        if (value != 0L) {
            this.zzAo().zzCJ().zzj("Uploading events. Elapsed time since last upload attempt (ms)", Math.abs(currentTimeMillis - value));
        }
        final String zzCq = this.zzCj().zzCq();
    Label_0560:
        while (true) {
            Label_0893: {
                Label_0698: {
                    if (TextUtils.isEmpty((CharSequence)zzCq)) {
                        break Label_0698;
                    }
                    final List<Pair<zzqb.zze, Long>> zzn = this.zzCj().zzn(zzCq, this.zzCp().zzeU(zzCq), this.zzCp().zzeV(zzCq));
                    if (!zzn.isEmpty()) {
                        final Iterator<Pair<zzqb.zze, Long>> iterator = zzn.iterator();
                        while (true) {
                            while (iterator.hasNext()) {
                                final zzqb.zze zze = (zzqb.zze)iterator.next().first;
                                if (!TextUtils.isEmpty((CharSequence)zze.zzbaz)) {
                                    final String zzbaz = zze.zzbaz;
                                Label_0386:
                                    while (true) {
                                        if (zzbaz != null) {
                                            for (int j = 0; j < zzn.size(); ++j) {
                                                final zzqb.zze zze2 = (zzqb.zze)zzn.get(j).first;
                                                if (!TextUtils.isEmpty((CharSequence)zze2.zzbaz) && !zze2.zzbaz.equals(zzbaz)) {
                                                    final List<Pair<zzqb.zze, Long>> subList = zzn.subList(0, j);
                                                    break Label_0386;
                                                }
                                            }
                                        }
                                        Label_0899: {
                                            break Label_0899;
                                            final zzqb.zzd zzd = new zzqb.zzd();
                                            final List<Pair<zzqb.zze, Long>> subList;
                                            zzd.zzbaj = new zzqb.zze[subList.size()];
                                            final ArrayList list = new ArrayList<Object>(subList.size());
                                            while (i < zzd.zzbaj.length) {
                                                zzd.zzbaj[i] = (zzqb.zze)subList.get(i).first;
                                                list.add(subList.get(i).second);
                                                zzd.zzbaj[i].zzbay = this.zzCp().zzBp();
                                                zzd.zzbaj[i].zzbao = currentTimeMillis;
                                                zzd.zzbaj[i].zzbaE = this.zzCp().zzkr();
                                                ++i;
                                            }
                                            if (this.zzAo().zzQ(2)) {
                                                final String zzb = zzaj.zzb(zzd);
                                                break Label_0560;
                                            }
                                            break Label_0893;
                                        }
                                        final List<Pair<zzqb.zze, Long>> subList = zzn;
                                        continue Label_0386;
                                    }
                                }
                            }
                            final String zzbaz = null;
                            continue;
                        }
                    }
                    return;
                    zzqb.zzd zzd = null;
                    final byte[] zza = this.zzCk().zza(zzd);
                    final String zzBV = this.zzCp().zzBV();
                    try {
                        final URL url = new URL(zzBV);
                        final ArrayList list;
                        this.zzA((List<Long>)list);
                        this.zzCo().zzaXk.set(currentTimeMillis);
                        String appId = "?";
                        if (zzd.zzbaj.length > 0) {
                            appId = zzd.zzbaj[0].appId;
                        }
                        final String zzb;
                        this.zzAo().zzCK().zzd("Uploading data. app, uncompressed size, data", appId, zza.length, zzb);
                        this.zzCW().zza(zzCq, url, zza, null, (zzq.zza)new zzq.zza() {
                            @Override
                            public void zza(final String s, final int n, final Throwable t, final byte[] array) {
                                zzw.this.zza(n, t, array);
                            }
                        });
                        return;
                    }
                    catch (MalformedURLException ex) {
                        this.zzAo().zzCE().zzj("Failed to parse upload URL. Not uploading", zzBV);
                        return;
                    }
                }
                final String zzaa = this.zzCj().zzaa(currentTimeMillis - this.zzCp().zzBW());
                if (TextUtils.isEmpty((CharSequence)zzaa)) {
                    return;
                }
                final com.google.android.gms.measurement.internal.zza zzeY = this.zzCj().zzeY(zzaa);
                if (zzeY == null) {
                    return;
                }
                final String zzH = this.zzCp().zzH(zzeY.zzBk(), zzeY.zzBj());
                try {
                    final URL url2 = new URL(zzH);
                    this.zzAo().zzCK().zzj("Fetching remote configuration", zzeY.zzwK());
                    final zzqa.zzb zzfk = this.zzCl().zzfk(zzeY.zzwK());
                    Map<String, String> map = null;
                    if (zzfk != null) {
                        final Long zzaZT = zzfk.zzaZT;
                        map = null;
                        if (zzaZT != null) {
                            map = new ArrayMap<String, String>();
                            map.put("Config-Version", String.valueOf(zzfk.zzaZT));
                        }
                    }
                    this.zzCW().zza(zzaa, url2, map, (zzq.zza)new zzq.zza() {
                        @Override
                        public void zza(final String s, final int n, final Throwable t, final byte[] array) {
                            zzw.this.zzb(s, n, t, array);
                        }
                    });
                    return;
                }
                catch (MalformedURLException ex2) {
                    this.zzAo().zzCE().zzj("Failed to parse config URL. Not fetching", zzH);
                    return;
                }
            }
            final String zzb = null;
            continue Label_0560;
        }
    }
    
    void zzDg() {
        ++this.zzaYr;
    }
    
    void zzE(final String s, final int n) {
    }
    
    public void zzJ(final boolean b) {
        this.zzDe();
    }
    
    void zza(final EventParcel eventParcel, final String s) {
        final com.google.android.gms.measurement.internal.zza zzeY = this.zzCj().zzeY(s);
        if (zzeY == null || TextUtils.isEmpty((CharSequence)zzeY.zzli())) {
            this.zzAo().zzCJ().zzj("No app data available; dropping event", s);
            return;
        }
        try {
            final String versionName = this.getContext().getPackageManager().getPackageInfo(s, 0).versionName;
            if (zzeY.zzli() != null && !zzeY.zzli().equals(versionName)) {
                this.zzAo().zzCF().zzj("App version does not match; dropping event", s);
                return;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            this.zzAo().zzCF().zzj("Could not find package", s);
        }
        this.zzb(eventParcel, new AppMetadata(s, zzeY.zzBk(), zzeY.zzli(), zzeY.zzBo(), zzeY.zzBp(), zzeY.zzBq(), null, zzeY.zzAr(), false));
    }
    
    void zza(final zzh zzh, final AppMetadata appMetadata) {
        this.zzjk();
        this.zzjv();
        zzx.zzz(zzh);
        zzx.zzz(appMetadata);
        zzx.zzcM(zzh.zzaUa);
        zzx.zzac(zzh.zzaUa.equals(appMetadata.packageName));
        final zzqb.zze zze = new zzqb.zze();
        zze.zzbal = 1;
        zze.zzbat = "android";
        zze.appId = appMetadata.packageName;
        zze.zzaVu = appMetadata.zzaVu;
        zze.zzaMV = appMetadata.zzaMV;
        zze.zzbax = appMetadata.zzaVv;
        zze.zzaVt = appMetadata.zzaVt;
        Long value;
        if (appMetadata.zzaVw == 0L) {
            value = null;
        }
        else {
            value = appMetadata.zzaVw;
        }
        zze.zzbaC = value;
        final Pair<String, Boolean> zzfh = this.zzCo().zzfh(appMetadata.packageName);
        if (zzfh != null && zzfh.first != null && zzfh.second != null) {
            zze.zzbaz = (String)zzfh.first;
            zze.zzbaA = (Boolean)zzfh.second;
        }
        zze.zzbau = this.zzCh().zzht();
        zze.osVersion = this.zzCh().zzCy();
        zze.zzbaw = (int)this.zzCh().zzCz();
        zze.zzbav = this.zzCh().zzCA();
        zze.zzbay = null;
        zze.zzbao = null;
        zze.zzbap = null;
        zze.zzbaq = null;
        com.google.android.gms.measurement.internal.zza zzeY = this.zzCj().zzeY(appMetadata.packageName);
        if (zzeY == null) {
            zzeY = new com.google.android.gms.measurement.internal.zza(this, appMetadata.packageName);
            zzeY.zzeM(this.zzCo().zzCM());
            zzeY.zzeN(appMetadata.zzaVt);
            zzeY.zzeO(this.zzCo().zzfi(appMetadata.packageName));
            zzeY.zzS(0L);
            zzeY.zzO(0L);
            zzeY.zzP(0L);
            zzeY.setAppVersion(appMetadata.zzaMV);
            zzeY.zzeP(appMetadata.zzaVu);
            zzeY.zzQ(appMetadata.zzaVv);
            zzeY.zzR(appMetadata.zzaVw);
            zzeY.setMeasurementEnabled(appMetadata.zzaVy);
            this.zzCj().zza(zzeY);
        }
        zze.zzbaB = zzeY.zzBj();
        final List<zzai> zzeX = this.zzCj().zzeX(appMetadata.packageName);
        zze.zzban = new zzqb.zzg[zzeX.size()];
        for (int i = 0; i < zzeX.size(); ++i) {
            final zzqb.zzg zzg = new zzqb.zzg();
            zze.zzban[i] = zzg;
            zzg.name = zzeX.get(i).mName;
            zzg.zzbaJ = zzeX.get(i).zzaZp;
            this.zzCk().zza(zzg, zzeX.get(i).zzNc);
        }
        try {
            this.zzCj().zza(zzh, this.zzCj().zzb(zze));
        }
        catch (IOException ex) {
            this.zzAo().zzCE().zzj("Data loss. Failed to insert raw event metadata", ex);
        }
    }
    
    boolean zzad(final long n) {
        return this.zzg(null, n);
    }
    
    void zzb(final EventParcel eventParcel, final AppMetadata appMetadata) {
        final long nanoTime = System.nanoTime();
        this.zzjk();
        this.zzjv();
        final String packageName = appMetadata.packageName;
        zzx.zzcM(packageName);
        if (TextUtils.isEmpty((CharSequence)appMetadata.zzaVt)) {
            return;
        }
        if (!appMetadata.zzaVy) {
            this.zze(appMetadata);
            return;
        }
        if (this.zzCl().zzP(packageName, eventParcel.name)) {
            this.zzAo().zzCK().zzj("Dropping blacklisted event", eventParcel.name);
            return;
        }
        if (this.zzAo().zzQ(2)) {
            this.zzAo().zzCK().zzj("Logging event", eventParcel);
        }
        Bundle zzCC;
        boolean zzfq;
        boolean zzI;
        zze.zza zza;
        while (true) {
            this.zzCj().beginTransaction();
            while (true) {
                try {
                    zzCC = eventParcel.zzaVV.zzCC();
                    this.zze(appMetadata);
                    if ("_iap".equals(eventParcel.name) || "ecommerce_purchase".equals(eventParcel.name)) {
                        final String string = zzCC.getString("currency");
                        final long long1 = zzCC.getLong("value");
                        if (!TextUtils.isEmpty((CharSequence)string) && long1 > 0L) {
                            final String upperCase = string.toUpperCase(Locale.US);
                            if (upperCase.matches("[A-Z]{3}")) {
                                final String string2 = "_ltv_" + upperCase;
                                final zzai zzK = this.zzCj().zzK(packageName, string2);
                                zzai zzai;
                                if (zzK == null || !(zzK.zzNc instanceof Long)) {
                                    this.zzCj().zzA(packageName, -1 + this.zzCp().zzeT(packageName));
                                    zzai = new zzai(packageName, string2, this.zzjl().currentTimeMillis(), long1);
                                }
                                else {
                                    zzai = new zzai(packageName, string2, this.zzjl().currentTimeMillis(), long1 + (long)zzK.zzNc);
                                }
                                this.zzCj().zza(zzai);
                            }
                        }
                    }
                    zzfq = zzaj.zzfq(eventParcel.name);
                    zzI = zzaj.zzI(zzCC);
                    final zze zzCj = this.zzCj();
                    final long zzDa = this.zzDa();
                    if (zzfq && zzI) {
                        final boolean b = true;
                        zza = zzCj.zza(zzDa, packageName, zzfq, b);
                        final long n = zza.zzaVF - this.zzCp().zzBI();
                        if (n > 0L) {
                            if (n % 1000L == 1L) {
                                this.zzAo().zzCF().zzj("Data loss. Too many events logged. count", zza.zzaVF);
                            }
                            this.zzCj().setTransactionSuccessful();
                            return;
                        }
                        break;
                    }
                }
                finally {
                    this.zzCj().endTransaction();
                }
                final boolean b = false;
                continue;
            }
        }
        if (zzfq) {
            final long n2 = zza.zzaVE - this.zzCp().zzBJ();
            if (n2 > 0L) {
                this.zzE(packageName, 2);
                if (n2 % 1000L == 1L) {
                    this.zzAo().zzCF().zzj("Data loss. Too many public events logged. count", zza.zzaVE);
                }
                this.zzCj().setTransactionSuccessful();
                this.zzCj().endTransaction();
                return;
            }
        }
        if (zzfq && zzI && zza.zzaVG - this.zzCp().zzBK() > 0L) {
            zzCC.remove("_c");
            this.zzb(zzCC, 4);
        }
        final long zzeZ = this.zzCj().zzeZ(packageName);
        if (zzeZ > 0L) {
            this.zzAo().zzCF().zzj("Data lost. Too many events stored on disk, deleted", zzeZ);
        }
        zzh zza2 = new zzh(this, eventParcel.zzaVW, packageName, eventParcel.name, eventParcel.zzaVX, 0L, zzCC);
        final zzi zzI2 = this.zzCj().zzI(packageName, zza2.mName);
        zzi zzab;
        if (zzI2 == null) {
            if (this.zzCj().zzfd(packageName) >= this.zzCp().zzBH()) {
                this.zzAo().zzCF().zze("Too many event names used, ignoring event. name, supported count", zza2.mName, this.zzCp().zzBH());
                this.zzE(packageName, 1);
                this.zzCj().endTransaction();
                return;
            }
            zzab = new zzi(packageName, zza2.mName, 0L, 0L, zza2.zzaez);
        }
        else {
            zza2 = zza2.zza(this, zzI2.zzaVR);
            zzab = zzI2.zzab(zza2.zzaez);
        }
        this.zzCj().zza(zzab);
        this.zza(zza2, appMetadata);
        this.zzCj().setTransactionSuccessful();
        if (this.zzAo().zzQ(2)) {
            this.zzAo().zzCK().zzj("Event recorded", zza2);
        }
        this.zzCj().endTransaction();
        this.zzDe();
        this.zzAo().zzCK().zzj("Background event processing time, ms", (500000L + (System.nanoTime() - nanoTime)) / 1000000L);
    }
    
    @WorkerThread
    void zzb(final UserAttributeParcel userAttributeParcel, final AppMetadata appMetadata) {
        this.zzjk();
        this.zzjv();
        if (!TextUtils.isEmpty((CharSequence)appMetadata.zzaVt)) {
            if (!appMetadata.zzaVy) {
                this.zze(appMetadata);
                return;
            }
            this.zzCk().zzfs(userAttributeParcel.name);
            final Object zzm = this.zzCk().zzm(userAttributeParcel.name, userAttributeParcel.getValue());
            if (zzm != null) {
                final zzai zzai = new zzai(appMetadata.packageName, userAttributeParcel.name, userAttributeParcel.zzaZm, zzm);
                this.zzAo().zzCJ().zze("Setting user property", zzai.mName, zzm);
                this.zzCj().beginTransaction();
                try {
                    this.zze(appMetadata);
                    final boolean zza = this.zzCj().zza(zzai);
                    this.zzCj().setTransactionSuccessful();
                    if (zza) {
                        this.zzAo().zzCJ().zze("User property set", zzai.mName, zzai.zzNc);
                    }
                    else {
                        this.zzAo().zzCH().zze("Ignoring user property. Value too long", zzai.mName, zzai.zzNc);
                    }
                }
                finally {
                    this.zzCj().endTransaction();
                }
            }
        }
    }
    
    void zzb(final zzz zzz) {
        ++this.zzaYq;
    }
    
    void zzc(final AppMetadata appMetadata) {
        this.zzjk();
        this.zzjv();
        zzx.zzcM(appMetadata.packageName);
        this.zze(appMetadata);
    }
    
    @WorkerThread
    void zzc(final UserAttributeParcel userAttributeParcel, final AppMetadata appMetadata) {
        this.zzjk();
        this.zzjv();
        if (TextUtils.isEmpty((CharSequence)appMetadata.zzaVt)) {
            return;
        }
        if (!appMetadata.zzaVy) {
            this.zze(appMetadata);
            return;
        }
        this.zzAo().zzCJ().zzj("Removing user property", userAttributeParcel.name);
        this.zzCj().beginTransaction();
        try {
            this.zze(appMetadata);
            this.zzCj().zzJ(appMetadata.packageName, userAttributeParcel.name);
            this.zzCj().setTransactionSuccessful();
            this.zzAo().zzCJ().zzj("User property removed", userAttributeParcel.name);
        }
        finally {
            this.zzCj().endTransaction();
        }
    }
    
    @WorkerThread
    public void zzd(final AppMetadata appMetadata) {
        this.zzjk();
        this.zzjv();
        zzx.zzz(appMetadata);
        zzx.zzcM(appMetadata.packageName);
        if (TextUtils.isEmpty((CharSequence)appMetadata.zzaVt)) {
            return;
        }
        if (!appMetadata.zzaVy) {
            this.zze(appMetadata);
            return;
        }
        final long currentTimeMillis = this.zzjl().currentTimeMillis();
        this.zzCj().beginTransaction();
        try {
            final com.google.android.gms.measurement.internal.zza zzeY = this.zzCj().zzeY(appMetadata.packageName);
            if (zzeY != null && zzeY.zzli() != null && !zzeY.zzli().equals(appMetadata.zzaMV)) {
                final Bundle bundle = new Bundle();
                bundle.putString("_pv", zzeY.zzli());
                this.zzb(new EventParcel("_au", new EventParams(bundle), "auto", currentTimeMillis), appMetadata);
            }
            this.zze(appMetadata);
            if (this.zzCj().zzI(appMetadata.packageName, "_f") == null) {
                this.zzb(new UserAttributeParcel("_fot", currentTimeMillis, 3600000L * (1L + currentTimeMillis / 3600000L), "auto"), appMetadata);
                final Bundle bundle2 = new Bundle();
                bundle2.putLong("_c", 1L);
                this.zzb(new EventParcel("_f", new EventParams(bundle2), "auto", currentTimeMillis), appMetadata);
            }
            else if (appMetadata.zzaVz) {
                this.zzb(new EventParcel("_cd", new EventParams(new Bundle()), "auto", currentTimeMillis), appMetadata);
            }
            this.zzCj().setTransactionSuccessful();
        }
        finally {
            this.zzCj().endTransaction();
        }
    }
    
    void zzjj() {
        if (this.zzCp().zzkr()) {
            throw new IllegalStateException("Unexpected call on package side");
        }
    }
    
    @WorkerThread
    public void zzjk() {
        this.zzCn().zzjk();
    }
    
    public zzmq zzjl() {
        return this.zzqW;
    }
    
    void zzjv() {
        if (!this.zzQk) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }
    
    private class zza implements zzb
    {
        zzqb.zze zzaYt;
        List<Long> zzaYu;
        long zzaYv;
        List<zzqb.zzb> zzpH;
        
        private long zza(final zzqb.zzb zzb) {
            return zzb.zzbaf / 1000L / 60L / 60L;
        }
        
        boolean isEmpty() {
            return this.zzpH == null || this.zzpH.isEmpty();
        }
        
        @Override
        public boolean zza(final long n, final zzqb.zzb zzb) {
            zzx.zzz(zzb);
            if (this.zzpH == null) {
                this.zzpH = new ArrayList<zzqb.zzb>();
            }
            if (this.zzaYu == null) {
                this.zzaYu = new ArrayList<Long>();
            }
            if (this.zzpH.size() > 0 && this.zza(this.zzpH.get(0)) != this.zza(zzb)) {
                return false;
            }
            final long zzaYv = this.zzaYv + zzb.getSerializedSize();
            if (zzaYv >= zzw.this.zzCp().zzBT()) {
                return false;
            }
            this.zzaYv = zzaYv;
            this.zzpH.add(zzb);
            this.zzaYu.add(n);
            return this.zzpH.size() < zzw.this.zzCp().zzBU();
        }
        
        @Override
        public void zzc(final zzqb.zze zzaYt) {
            zzx.zzz(zzaYt);
            this.zzaYt = zzaYt;
        }
    }
}
