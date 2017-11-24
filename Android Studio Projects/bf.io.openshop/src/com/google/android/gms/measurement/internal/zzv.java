package com.google.android.gms.measurement.internal;

import android.content.*;
import java.util.concurrent.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.internal.*;

public class zzv extends zzz
{
    private zzc zzaXI;
    private zzc zzaXJ;
    private final BlockingQueue<FutureTask<?>> zzaXK;
    private final BlockingQueue<FutureTask<?>> zzaXL;
    private final Thread.UncaughtExceptionHandler zzaXM;
    private final Thread.UncaughtExceptionHandler zzaXN;
    private final Object zzaXO;
    private final Semaphore zzaXP;
    private volatile boolean zzaXQ;
    
    zzv(final zzw zzw) {
        super(zzw);
        this.zzaXO = new Object();
        this.zzaXP = new Semaphore(2);
        this.zzaXK = new LinkedBlockingQueue<FutureTask<?>>();
        this.zzaXL = new LinkedBlockingQueue<FutureTask<?>>();
        this.zzaXM = new zzb("Thread death: Uncaught exception on worker thread");
        this.zzaXN = new zzb("Thread death: Uncaught exception on network thread");
    }
    
    private void zza(final FutureTask<?> futureTask) {
        synchronized (this.zzaXO) {
            this.zzaXK.add(futureTask);
            if (this.zzaXI == null) {
                (this.zzaXI = new zzc("Measurement Worker", this.zzaXK)).setUncaughtExceptionHandler(this.zzaXM);
                this.zzaXI.start();
            }
            else {
                this.zzaXI.zzfb();
            }
        }
    }
    
    private void zzb(final FutureTask<?> futureTask) {
        synchronized (this.zzaXO) {
            this.zzaXL.add(futureTask);
            if (this.zzaXJ == null) {
                (this.zzaXJ = new zzc("Measurement Network", this.zzaXL)).setUncaughtExceptionHandler(this.zzaXN);
                this.zzaXJ.start();
            }
            else {
                this.zzaXJ.zzfb();
            }
        }
    }
    
    @Override
    public void zzCd() {
        if (Thread.currentThread() != this.zzaXJ) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }
    
    public <V> Future<V> zzd(final Callable<V> callable) throws IllegalStateException {
        this.zzjv();
        zzx.zzz(callable);
        final zza<V> zza = new zza<V>(callable, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzaXI) {
            zza.run();
            return zza;
        }
        this.zza(zza);
        return zza;
    }
    
    public void zzg(final Runnable runnable) throws IllegalStateException {
        this.zzjv();
        zzx.zzz(runnable);
        this.zza(new zza<Object>(runnable, "Task exception on worker thread"));
    }
    
    public void zzh(final Runnable runnable) throws IllegalStateException {
        this.zzjv();
        zzx.zzz(runnable);
        this.zzb(new zza<Object>(runnable, "Task exception on network thread"));
    }
    
    @Override
    protected void zziJ() {
    }
    
    @Override
    public void zzjk() {
        if (Thread.currentThread() != this.zzaXI) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }
    
    private final class zza<V> extends FutureTask<V>
    {
        private final String zzaXR;
        
        zza(final Runnable runnable, final String zzaXR) {
            super(runnable, null);
            zzx.zzz(zzaXR);
            this.zzaXR = zzaXR;
        }
        
        zza(final Callable<V> callable, final String zzaXR) {
            super(callable);
            zzx.zzz(zzaXR);
            this.zzaXR = zzaXR;
        }
        
        @Override
        protected void setException(final Throwable exception) {
            zzv.this.zzAo().zzCE().zzj(this.zzaXR, exception);
            super.setException(exception);
        }
    }
    
    private final class zzb implements UncaughtExceptionHandler
    {
        private final String zzaXR;
        
        public zzb(final String zzaXR) {
            zzx.zzz(zzaXR);
            this.zzaXR = zzaXR;
        }
        
        @Override
        public void uncaughtException(final Thread thread, final Throwable t) {
            synchronized (this) {
                zzv.this.zzAo().zzCE().zzj(this.zzaXR, t);
            }
        }
    }
    
    private final class zzc extends Thread
    {
        private final Object zzaXT;
        private final BlockingQueue<FutureTask<?>> zzaXU;
        
        public zzc(final String name, final BlockingQueue<FutureTask<?>> zzaXU) {
            zzx.zzz(name);
            this.zzaXT = new Object();
            this.zzaXU = zzaXU;
            this.setName(name);
        }
        
        private void zza(final InterruptedException ex) {
            zzv.this.zzAo().zzCF().zzj(this.getName() + " was interrupted", ex);
        }
        
        @Override
        public void run() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: iconst_0       
            //     1: istore_1       
            //     2: iload_1        
            //     3: ifne            32
            //     6: aload_0        
            //     7: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //    10: invokestatic    com/google/android/gms/measurement/internal/zzv.zza:(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/util/concurrent/Semaphore;
            //    13: invokevirtual   java/util/concurrent/Semaphore.acquire:()V
            //    16: iconst_1       
            //    17: istore_1       
            //    18: goto            2
            //    21: astore          18
            //    23: aload_0        
            //    24: aload           18
            //    26: invokespecial   com/google/android/gms/measurement/internal/zzv$zzc.zza:(Ljava/lang/InterruptedException;)V
            //    29: goto            2
            //    32: aload_0        
            //    33: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXU:Ljava/util/concurrent/BlockingQueue;
            //    36: invokeinterface java/util/concurrent/BlockingQueue.poll:()Ljava/lang/Object;
            //    41: checkcast       Ljava/util/concurrent/FutureTask;
            //    44: astore          7
            //    46: aload           7
            //    48: ifnull          114
            //    51: aload           7
            //    53: invokevirtual   java/util/concurrent/FutureTask.run:()V
            //    56: goto            32
            //    59: astore_2       
            //    60: aload_0        
            //    61: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //    64: invokestatic    com/google/android/gms/measurement/internal/zzv.zzc:(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/lang/Object;
            //    67: astore_3       
            //    68: aload_3        
            //    69: monitorenter   
            //    70: aload_0        
            //    71: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //    74: invokestatic    com/google/android/gms/measurement/internal/zzv.zza:(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/util/concurrent/Semaphore;
            //    77: invokevirtual   java/util/concurrent/Semaphore.release:()V
            //    80: aload_0        
            //    81: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //    84: invokestatic    com/google/android/gms/measurement/internal/zzv.zzc:(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/lang/Object;
            //    87: invokevirtual   java/lang/Object.notifyAll:()V
            //    90: aload_0        
            //    91: aload_0        
            //    92: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //    95: invokestatic    com/google/android/gms/measurement/internal/zzv.zzd:(Lcom/google/android/gms/measurement/internal/zzv;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
            //    98: if_acmpne       327
            //   101: aload_0        
            //   102: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   105: aconst_null    
            //   106: invokestatic    com/google/android/gms/measurement/internal/zzv.zza:(Lcom/google/android/gms/measurement/internal/zzv;Lcom/google/android/gms/measurement/internal/zzv$zzc;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
            //   109: pop            
            //   110: aload_3        
            //   111: monitorexit    
            //   112: aload_2        
            //   113: athrow         
            //   114: aload_0        
            //   115: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXT:Ljava/lang/Object;
            //   118: astore          8
            //   120: aload           8
            //   122: monitorenter   
            //   123: aload_0        
            //   124: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXU:Ljava/util/concurrent/BlockingQueue;
            //   127: invokeinterface java/util/concurrent/BlockingQueue.peek:()Ljava/lang/Object;
            //   132: ifnonnull       159
            //   135: aload_0        
            //   136: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   139: invokestatic    com/google/android/gms/measurement/internal/zzv.zzb:(Lcom/google/android/gms/measurement/internal/zzv;)Z
            //   142: istore          16
            //   144: iload           16
            //   146: ifne            159
            //   149: aload_0        
            //   150: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXT:Ljava/lang/Object;
            //   153: ldc2_w          30000
            //   156: invokevirtual   java/lang/Object.wait:(J)V
            //   159: aload           8
            //   161: monitorexit    
            //   162: aload_0        
            //   163: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   166: invokestatic    com/google/android/gms/measurement/internal/zzv.zzc:(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/lang/Object;
            //   169: astore          10
            //   171: aload           10
            //   173: monitorenter   
            //   174: aload_0        
            //   175: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXU:Ljava/util/concurrent/BlockingQueue;
            //   178: invokeinterface java/util/concurrent/BlockingQueue.peek:()Ljava/lang/Object;
            //   183: ifnonnull       313
            //   186: aload           10
            //   188: monitorexit    
            //   189: aload_0        
            //   190: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   193: invokestatic    com/google/android/gms/measurement/internal/zzv.zzc:(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/lang/Object;
            //   196: astore          12
            //   198: aload           12
            //   200: monitorenter   
            //   201: aload_0        
            //   202: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   205: invokestatic    com/google/android/gms/measurement/internal/zzv.zza:(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/util/concurrent/Semaphore;
            //   208: invokevirtual   java/util/concurrent/Semaphore.release:()V
            //   211: aload_0        
            //   212: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   215: invokestatic    com/google/android/gms/measurement/internal/zzv.zzc:(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/lang/Object;
            //   218: invokevirtual   java/lang/Object.notifyAll:()V
            //   221: aload_0        
            //   222: aload_0        
            //   223: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   226: invokestatic    com/google/android/gms/measurement/internal/zzv.zzd:(Lcom/google/android/gms/measurement/internal/zzv;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
            //   229: if_acmpne       264
            //   232: aload_0        
            //   233: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   236: aconst_null    
            //   237: invokestatic    com/google/android/gms/measurement/internal/zzv.zza:(Lcom/google/android/gms/measurement/internal/zzv;Lcom/google/android/gms/measurement/internal/zzv$zzc;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
            //   240: pop            
            //   241: aload           12
            //   243: monitorexit    
            //   244: return         
            //   245: astore          17
            //   247: aload_0        
            //   248: aload           17
            //   250: invokespecial   com/google/android/gms/measurement/internal/zzv$zzc.zza:(Ljava/lang/InterruptedException;)V
            //   253: goto            159
            //   256: astore          9
            //   258: aload           8
            //   260: monitorexit    
            //   261: aload           9
            //   263: athrow         
            //   264: aload_0        
            //   265: aload_0        
            //   266: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   269: invokestatic    com/google/android/gms/measurement/internal/zzv.zze:(Lcom/google/android/gms/measurement/internal/zzv;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
            //   272: if_acmpne       295
            //   275: aload_0        
            //   276: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   279: aconst_null    
            //   280: invokestatic    com/google/android/gms/measurement/internal/zzv.zzb:(Lcom/google/android/gms/measurement/internal/zzv;Lcom/google/android/gms/measurement/internal/zzv$zzc;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
            //   283: pop            
            //   284: goto            241
            //   287: astore          13
            //   289: aload           12
            //   291: monitorexit    
            //   292: aload           13
            //   294: athrow         
            //   295: aload_0        
            //   296: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   299: invokevirtual   com/google/android/gms/measurement/internal/zzv.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
            //   302: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
            //   305: ldc             "Current scheduler thread is neither worker nor network"
            //   307: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
            //   310: goto            241
            //   313: aload           10
            //   315: monitorexit    
            //   316: goto            32
            //   319: astore          11
            //   321: aload           10
            //   323: monitorexit    
            //   324: aload           11
            //   326: athrow         
            //   327: aload_0        
            //   328: aload_0        
            //   329: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   332: invokestatic    com/google/android/gms/measurement/internal/zzv.zze:(Lcom/google/android/gms/measurement/internal/zzv;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
            //   335: if_acmpne       357
            //   338: aload_0        
            //   339: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   342: aconst_null    
            //   343: invokestatic    com/google/android/gms/measurement/internal/zzv.zzb:(Lcom/google/android/gms/measurement/internal/zzv;Lcom/google/android/gms/measurement/internal/zzv$zzc;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
            //   346: pop            
            //   347: goto            110
            //   350: astore          4
            //   352: aload_3        
            //   353: monitorexit    
            //   354: aload           4
            //   356: athrow         
            //   357: aload_0        
            //   358: getfield        com/google/android/gms/measurement/internal/zzv$zzc.zzaXS:Lcom/google/android/gms/measurement/internal/zzv;
            //   361: invokevirtual   com/google/android/gms/measurement/internal/zzv.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
            //   364: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
            //   367: ldc             "Current scheduler thread is neither worker nor network"
            //   369: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
            //   372: goto            110
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  6      16     21     32     Ljava/lang/InterruptedException;
            //  32     46     59     375    Any
            //  51     56     59     375    Any
            //  70     110    350    357    Any
            //  110    112    350    357    Any
            //  114    123    59     375    Any
            //  123    144    256    264    Any
            //  149    159    245    256    Ljava/lang/InterruptedException;
            //  149    159    256    264    Any
            //  159    162    256    264    Any
            //  162    174    59     375    Any
            //  174    189    319    327    Any
            //  201    241    287    295    Any
            //  241    244    287    295    Any
            //  247    253    256    264    Any
            //  258    261    256    264    Any
            //  261    264    59     375    Any
            //  264    284    287    295    Any
            //  289    292    287    295    Any
            //  295    310    287    295    Any
            //  313    316    319    327    Any
            //  321    324    319    327    Any
            //  324    327    59     375    Any
            //  327    347    350    357    Any
            //  352    354    350    357    Any
            //  357    372    350    357    Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public void zzfb() {
            synchronized (this.zzaXT) {
                this.zzaXT.notifyAll();
            }
        }
    }
}
