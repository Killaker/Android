package com.google.android.gms.tagmanager;

import android.content.*;
import com.google.android.gms.internal.*;
import android.app.*;
import android.os.*;
import java.util.*;

class zzdb extends zzak
{
    private static final String ID;
    private static final String NAME;
    private static final String zzblp;
    private static final String zzblq;
    private static final String zzblr;
    private final Context mContext;
    private Handler mHandler;
    private DataLayer zzbhN;
    private boolean zzbls;
    private boolean zzblt;
    private final HandlerThread zzblu;
    private final Set<String> zzblv;
    
    static {
        ID = zzad.zzdl.toString();
        NAME = zzae.zzgo.toString();
        zzblp = zzae.zzfT.toString();
        zzblq = zzae.zzgb.toString();
        zzblr = zzae.zzhW.toString();
    }
    
    public zzdb(final Context mContext, final DataLayer zzbhN) {
        super(zzdb.ID, new String[] { zzdb.zzblp, zzdb.NAME });
        this.zzblv = new HashSet<String>();
        this.mContext = mContext;
        this.zzbhN = zzbhN;
        (this.zzblu = new HandlerThread("Google GTM SDK Timer", 10)).start();
        this.mHandler = new Handler(this.zzblu.getLooper());
    }
    
    @Override
    public boolean zzFW() {
        return false;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getstatic       com/google/android/gms/tagmanager/zzdb.NAME:Ljava/lang/String;
        //     4: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //     9: checkcast       Lcom/google/android/gms/internal/zzag$zza;
        //    12: invokestatic    com/google/android/gms/tagmanager/zzdf.zzg:(Lcom/google/android/gms/internal/zzag$zza;)Ljava/lang/String;
        //    15: astore_2       
        //    16: aload_1        
        //    17: getstatic       com/google/android/gms/tagmanager/zzdb.zzblr:Ljava/lang/String;
        //    20: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    25: checkcast       Lcom/google/android/gms/internal/zzag$zza;
        //    28: invokestatic    com/google/android/gms/tagmanager/zzdf.zzg:(Lcom/google/android/gms/internal/zzag$zza;)Ljava/lang/String;
        //    31: astore_3       
        //    32: aload_1        
        //    33: getstatic       com/google/android/gms/tagmanager/zzdb.zzblp:Ljava/lang/String;
        //    36: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    41: checkcast       Lcom/google/android/gms/internal/zzag$zza;
        //    44: invokestatic    com/google/android/gms/tagmanager/zzdf.zzg:(Lcom/google/android/gms/internal/zzag$zza;)Ljava/lang/String;
        //    47: astore          4
        //    49: aload_1        
        //    50: getstatic       com/google/android/gms/tagmanager/zzdb.zzblq:Ljava/lang/String;
        //    53: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    58: checkcast       Lcom/google/android/gms/internal/zzag$zza;
        //    61: invokestatic    com/google/android/gms/tagmanager/zzdf.zzg:(Lcom/google/android/gms/internal/zzag$zza;)Ljava/lang/String;
        //    64: astore          5
        //    66: aload           4
        //    68: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //    71: lstore          16
        //    73: lload           16
        //    75: lstore          7
        //    77: aload           5
        //    79: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //    82: lstore          14
        //    84: lload           14
        //    86: lstore          10
        //    88: lload           7
        //    90: lconst_0       
        //    91: lcmp           
        //    92: ifle            173
        //    95: aload_2        
        //    96: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    99: ifne            173
        //   102: aload_3        
        //   103: ifnull          113
        //   106: aload_3        
        //   107: invokevirtual   java/lang/String.isEmpty:()Z
        //   110: ifeq            116
        //   113: ldc             "0"
        //   115: astore_3       
        //   116: aload_0        
        //   117: getfield        com/google/android/gms/tagmanager/zzdb.zzblv:Ljava/util/Set;
        //   120: aload_3        
        //   121: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   126: ifne            173
        //   129: ldc             "0"
        //   131: aload_3        
        //   132: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   135: ifne            149
        //   138: aload_0        
        //   139: getfield        com/google/android/gms/tagmanager/zzdb.zzblv:Ljava/util/Set;
        //   142: aload_3        
        //   143: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   148: pop            
        //   149: aload_0        
        //   150: getfield        com/google/android/gms/tagmanager/zzdb.mHandler:Landroid/os/Handler;
        //   153: new             Lcom/google/android/gms/tagmanager/zzdb$zza;
        //   156: dup            
        //   157: aload_0        
        //   158: aload_2        
        //   159: aload_3        
        //   160: lload           7
        //   162: lload           10
        //   164: invokespecial   com/google/android/gms/tagmanager/zzdb$zza.<init>:(Lcom/google/android/gms/tagmanager/zzdb;Ljava/lang/String;Ljava/lang/String;JJ)V
        //   167: lload           7
        //   169: invokevirtual   android/os/Handler.postDelayed:(Ljava/lang/Runnable;J)Z
        //   172: pop            
        //   173: invokestatic    com/google/android/gms/tagmanager/zzdf.zzHF:()Lcom/google/android/gms/internal/zzag$zza;
        //   176: areturn        
        //   177: astore          6
        //   179: lconst_0       
        //   180: lstore          7
        //   182: goto            77
        //   185: astore          9
        //   187: lconst_0       
        //   188: lstore          10
        //   190: goto            88
        //    Signature:
        //  (Ljava/util/Map<Ljava/lang/String;Lcom/google/android/gms/internal/zzag$zza;>;)Lcom/google/android/gms/internal/zzag$zza;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  66     73     177    185    Ljava/lang/NumberFormatException;
        //  77     84     185    193    Ljava/lang/NumberFormatException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private final class zza implements Runnable
    {
        private final long zzCv;
        private final long zzaNY;
        private final String zzblw;
        private final String zzblx;
        private final long zzbly;
        private long zzblz;
        
        zza(final String zzblw, final String zzblx, final long zzaNY, final long zzbly) {
            this.zzblw = zzblw;
            this.zzblx = zzblx;
            this.zzaNY = zzaNY;
            this.zzbly = zzbly;
            this.zzCv = System.currentTimeMillis();
        }
        
        @Override
        public void run() {
            if (this.zzbly > 0L && this.zzblz >= this.zzbly) {
                if (!"0".equals(this.zzblx)) {
                    zzdb.this.zzblv.remove(this.zzblx);
                }
                return;
            }
            ++this.zzblz;
            if (this.zzcH()) {
                final long currentTimeMillis = System.currentTimeMillis();
                zzdb.this.zzbhN.push(DataLayer.mapOf("event", this.zzblw, "gtm.timerInterval", String.valueOf(this.zzaNY), "gtm.timerLimit", String.valueOf(this.zzbly), "gtm.timerStartTime", String.valueOf(this.zzCv), "gtm.timerCurrentTime", String.valueOf(currentTimeMillis), "gtm.timerElapsedTime", String.valueOf(currentTimeMillis - this.zzCv), "gtm.timerEventNumber", String.valueOf(this.zzblz), "gtm.triggers", this.zzblx));
            }
            zzdb.this.mHandler.postDelayed((Runnable)this, this.zzaNY);
        }
        
        protected boolean zzcH() {
            if (zzdb.this.zzblt) {
                return zzdb.this.zzbls;
            }
            final ActivityManager activityManager = (ActivityManager)zzdb.this.mContext.getSystemService("activity");
            final KeyguardManager keyguardManager = (KeyguardManager)zzdb.this.mContext.getSystemService("keyguard");
            final PowerManager powerManager = (PowerManager)zzdb.this.mContext.getSystemService("power");
            for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (Process.myPid() == activityManager$RunningAppProcessInfo.pid && activityManager$RunningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && powerManager.isScreenOn()) {
                    return true;
                }
            }
            return false;
        }
    }
}
