package com.google.android.gms.common.api.internal;

import java.lang.ref.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import java.util.concurrent.*;
import com.google.android.gms.common.api.*;
import android.os.*;
import android.util.*;

public abstract class zzb<R extends Result> extends PendingResult<R>
{
    private boolean zzL;
    private final Object zzagI;
    protected final zza<R> zzagJ;
    private final WeakReference<GoogleApiClient> zzagK;
    private final ArrayList<PendingResult.zza> zzagL;
    private ResultCallback<? super R> zzagM;
    private volatile boolean zzagN;
    private boolean zzagO;
    private boolean zzagP;
    private zzq zzagQ;
    private Integer zzagR;
    private volatile zzx<R> zzagS;
    private volatile R zzagy;
    private final CountDownLatch zzpJ;
    
    protected zzb(final Looper looper) {
        this.zzagI = new Object();
        this.zzpJ = new CountDownLatch(1);
        this.zzagL = new ArrayList<PendingResult.zza>();
        this.zzagJ = new zza<R>(looper);
        this.zzagK = new WeakReference<GoogleApiClient>(null);
    }
    
    protected zzb(final GoogleApiClient googleApiClient) {
        this.zzagI = new Object();
        this.zzpJ = new CountDownLatch(1);
        this.zzagL = new ArrayList<PendingResult.zza>();
        Looper looper;
        if (googleApiClient != null) {
            looper = googleApiClient.getLooper();
        }
        else {
            looper = Looper.getMainLooper();
        }
        this.zzagJ = new zza<R>(looper);
        this.zzagK = new WeakReference<GoogleApiClient>(googleApiClient);
    }
    
    private R get() {
        boolean b = true;
        synchronized (this.zzagI) {
            if (this.zzagN) {
                b = false;
            }
            com.google.android.gms.common.internal.zzx.zza(b, (Object)"Result has already been consumed.");
            com.google.android.gms.common.internal.zzx.zza(this.isReady(), (Object)"Result is not ready.");
            final Result zzagy = this.zzagy;
            this.zzagy = null;
            this.zzagM = null;
            this.zzagN = true;
            // monitorexit(this.zzagI)
            this.zzpf();
            return (R)zzagy;
        }
    }
    
    private void zzb(final R zzagy) {
        this.zzagy = zzagy;
        this.zzagQ = null;
        this.zzpJ.countDown();
        final Status status = this.zzagy.getStatus();
        if (this.zzagM != null) {
            this.zzagJ.zzph();
            if (!this.zzL) {
                this.zzagJ.zza(this.zzagM, this.get());
            }
        }
        final Iterator<PendingResult.zza> iterator = this.zzagL.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzu(status);
        }
        this.zzagL.clear();
    }
    
    public static void zzc(final Result result) {
        if (!(result instanceof Releasable)) {
            return;
        }
        try {
            ((Releasable)result).release();
        }
        catch (RuntimeException ex) {
            Log.w("BasePendingResult", "Unable to release " + result, (Throwable)ex);
        }
    }
    
    @Override
    public final R await() {
        boolean b = true;
        while (true) {
        Label_0073_Outer:
            while (true) {
                boolean b3 = false;
                Label_0028: {
                    while (true) {
                        boolean b2 = false;
                        Label_0013: {
                            if (Looper.myLooper() != Looper.getMainLooper()) {
                                b2 = b;
                                break Label_0013;
                            }
                            Label_0068: {
                                break Label_0068;
                                while (true) {
                                    com.google.android.gms.common.internal.zzx.zza(b, (Object)"Cannot await if then() has been called.");
                                    while (true) {
                                        try {
                                            this.zzpJ.await();
                                            com.google.android.gms.common.internal.zzx.zza(this.isReady(), (Object)"Result is not ready.");
                                            return this.get();
                                            b3 = false;
                                            break Label_0028;
                                            b2 = false;
                                            break;
                                            b = false;
                                        }
                                        catch (InterruptedException ex) {
                                            this.zzx(Status.zzagD);
                                            continue Label_0073_Outer;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        com.google.android.gms.common.internal.zzx.zza(b2, (Object)"await must not be called on the UI thread");
                        if (this.zzagN) {
                            continue;
                        }
                        break;
                    }
                    b3 = b;
                }
                com.google.android.gms.common.internal.zzx.zza(b3, (Object)"Result has already been consumed");
                if (this.zzagS == null) {
                    continue Label_0073_Outer;
                }
                break;
            }
            continue;
        }
    }
    
    @Override
    public final R await(final long n, final TimeUnit timeUnit) {
        boolean b = true;
        while (true) {
        Label_0100_Outer:
            while (true) {
                boolean b3 = false;
                Label_0040: {
                    while (true) {
                        boolean b2 = false;
                        Label_0022: {
                            if (n <= 0L || Looper.myLooper() != Looper.getMainLooper()) {
                                b2 = b;
                                break Label_0022;
                            }
                            Label_0094: {
                                break Label_0094;
                                while (true) {
                                    com.google.android.gms.common.internal.zzx.zza(b, (Object)"Cannot await if then() has been called.");
                                    while (true) {
                                        try {
                                            if (!this.zzpJ.await(n, timeUnit)) {
                                                this.zzx(Status.zzagF);
                                            }
                                            com.google.android.gms.common.internal.zzx.zza(this.isReady(), (Object)"Result is not ready.");
                                            return this.get();
                                            b3 = false;
                                            break Label_0040;
                                            b2 = false;
                                            break;
                                            b = false;
                                        }
                                        catch (InterruptedException ex) {
                                            this.zzx(Status.zzagD);
                                            continue Label_0100_Outer;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        com.google.android.gms.common.internal.zzx.zza(b2, (Object)"await must not be called on the UI thread when time is greater than zero.");
                        if (this.zzagN) {
                            continue;
                        }
                        break;
                    }
                    b3 = b;
                }
                com.google.android.gms.common.internal.zzx.zza(b3, (Object)"Result has already been consumed.");
                if (this.zzagS == null) {
                    continue Label_0100_Outer;
                }
                break;
            }
            continue;
        }
    }
    
    @Override
    public void cancel() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/common/api/internal/zzb.zzagI:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/common/api/internal/zzb.zzL:Z
        //    11: ifne            21
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/common/api/internal/zzb.zzagN:Z
        //    18: ifeq            24
        //    21: aload_1        
        //    22: monitorexit    
        //    23: return         
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/common/api/internal/zzb.zzagQ:Lcom/google/android/gms/common/internal/zzq;
        //    28: astore_3       
        //    29: aload_3        
        //    30: ifnull          42
        //    33: aload_0        
        //    34: getfield        com/google/android/gms/common/api/internal/zzb.zzagQ:Lcom/google/android/gms/common/internal/zzq;
        //    37: invokeinterface com/google/android/gms/common/internal/zzq.cancel:()V
        //    42: aload_0        
        //    43: getfield        com/google/android/gms/common/api/internal/zzb.zzagy:Lcom/google/android/gms/common/api/Result;
        //    46: invokestatic    com/google/android/gms/common/api/internal/zzb.zzc:(Lcom/google/android/gms/common/api/Result;)V
        //    49: aload_0        
        //    50: aconst_null    
        //    51: putfield        com/google/android/gms/common/api/internal/zzb.zzagM:Lcom/google/android/gms/common/api/ResultCallback;
        //    54: aload_0        
        //    55: iconst_1       
        //    56: putfield        com/google/android/gms/common/api/internal/zzb.zzL:Z
        //    59: aload_0        
        //    60: aload_0        
        //    61: getstatic       com/google/android/gms/common/api/Status.zzagG:Lcom/google/android/gms/common/api/Status;
        //    64: invokevirtual   com/google/android/gms/common/api/internal/zzb.zzc:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/common/api/Result;
        //    67: invokespecial   com/google/android/gms/common/api/internal/zzb.zzb:(Lcom/google/android/gms/common/api/Result;)V
        //    70: aload_1        
        //    71: monitorexit    
        //    72: return         
        //    73: astore_2       
        //    74: aload_1        
        //    75: monitorexit    
        //    76: aload_2        
        //    77: athrow         
        //    78: astore          4
        //    80: goto            42
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      21     73     78     Any
        //  21     23     73     78     Any
        //  24     29     73     78     Any
        //  33     42     78     83     Landroid/os/RemoteException;
        //  33     42     73     78     Any
        //  42     72     73     78     Any
        //  74     76     73     78     Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public boolean isCanceled() {
        synchronized (this.zzagI) {
            return this.zzL;
        }
    }
    
    public final boolean isReady() {
        return this.zzpJ.getCount() == 0L;
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<? super R> zzagM) {
        while (true) {
            boolean b = true;
            com.google.android.gms.common.internal.zzx.zza(!this.zzagN && b, (Object)"Result has already been consumed.");
            while (true) {
                Label_0129: {
                    synchronized (this.zzagI) {
                        if (this.zzagS != null) {
                            break Label_0129;
                        }
                        com.google.android.gms.common.internal.zzx.zza(b, (Object)"Cannot set callbacks if then() has been called.");
                        if (this.isCanceled()) {
                            return;
                        }
                        if (this.zzagP && (this.zzagK.get() == null || !(zzagM instanceof zzx))) {
                            this.cancel();
                            return;
                        }
                    }
                    break;
                }
                b = false;
                continue;
            }
        }
        if (this.isReady()) {
            this.zzagJ.zza(zzagM, this.get());
        }
        else {
            this.zzagM = zzagM;
        }
    }
    // monitorexit(o)
    
    @Override
    public final void setResultCallback(final ResultCallback<? super R> zzagM, final long n, final TimeUnit timeUnit) {
        while (true) {
            boolean b = true;
            com.google.android.gms.common.internal.zzx.zza(!this.zzagN && b, (Object)"Result has already been consumed.");
            while (true) {
                Label_0149: {
                    synchronized (this.zzagI) {
                        if (this.zzagS != null) {
                            break Label_0149;
                        }
                        com.google.android.gms.common.internal.zzx.zza(b, (Object)"Cannot set callbacks if then() has been called.");
                        if (this.isCanceled()) {
                            return;
                        }
                        if (this.zzagP && (this.zzagK.get() == null || !(zzagM instanceof zzx))) {
                            this.cancel();
                            return;
                        }
                    }
                    break;
                }
                b = false;
                continue;
            }
        }
        if (this.isReady()) {
            this.zzagJ.zza(zzagM, this.get());
        }
        else {
            this.zzagM = zzagM;
            this.zzagJ.zza(this, timeUnit.toMillis(n));
        }
    }
    // monitorexit(o)
    
    @Override
    public <S extends Result> TransformedResult<S> then(final ResultTransform<? super R, ? extends S> resultTransform) {
        boolean b = true;
        while (true) {
            Label_0130: {
                if (this.zzagN) {
                    break Label_0130;
                }
                final boolean b2 = b;
            Label_0051_Outer:
                while (true) {
                    com.google.android.gms.common.internal.zzx.zza(b2, (Object)"Result has already been consumed.");
                    while (true) {
                    Label_0141:
                        while (true) {
                            Label_0135: {
                                synchronized (this.zzagI) {
                                    if (this.zzagS != null) {
                                        break Label_0135;
                                    }
                                    final boolean b3 = b;
                                    com.google.android.gms.common.internal.zzx.zza(b3, (Object)"Cannot call then() twice.");
                                    if (this.zzagM == null) {
                                        com.google.android.gms.common.internal.zzx.zza(b, (Object)"Cannot call then() if callbacks are set.");
                                        this.zzagS = new zzx<R>(this.zzagK);
                                        final TransformedResult<S> then = this.zzagS.then(resultTransform);
                                        if (this.isReady()) {
                                            this.zzagJ.zza(this.zzagS, this.get());
                                        }
                                        else {
                                            this.zzagM = this.zzagS;
                                        }
                                        return then;
                                    }
                                    break Label_0141;
                                }
                                break;
                            }
                            final boolean b3 = false;
                            continue Label_0051_Outer;
                        }
                        b = false;
                        continue;
                    }
                }
            }
            final boolean b2 = false;
            continue;
        }
    }
    
    @Override
    public final void zza(final PendingResult.zza zza) {
        boolean b = true;
        while (true) {
            Label_0083: {
                if (this.zzagN) {
                    break Label_0083;
                }
                final boolean b2 = b;
                com.google.android.gms.common.internal.zzx.zza(b2, (Object)"Result has already been consumed.");
                if (zza == null) {
                    b = false;
                }
                com.google.android.gms.common.internal.zzx.zzb(b, (Object)"Callback cannot be null.");
                synchronized (this.zzagI) {
                    if (this.isReady()) {
                        zza.zzu(this.zzagy.getStatus());
                    }
                    else {
                        this.zzagL.add(zza);
                    }
                    return;
                }
            }
            final boolean b2 = false;
            continue;
        }
    }
    
    public final void zza(final R r) {
    Label_0055_Outer:
        while (true) {
            boolean b = true;
            while (true) {
            Label_0082:
                while (true) {
                    synchronized (this.zzagI) {
                        if (this.zzagO || this.zzL) {
                            zzc(r);
                            return;
                        }
                        if (!this.isReady()) {
                            final boolean b2 = b;
                            com.google.android.gms.common.internal.zzx.zza(b2, (Object)"Results have already been set");
                            if (!this.zzagN) {
                                com.google.android.gms.common.internal.zzx.zza(b, (Object)"Result has already been consumed");
                                this.zzb(r);
                                return;
                            }
                            break Label_0082;
                        }
                    }
                    final boolean b2 = false;
                    continue Label_0055_Outer;
                }
                b = false;
                continue;
            }
        }
    }
    
    protected final void zza(final zzq zzagQ) {
        synchronized (this.zzagI) {
            this.zzagQ = zzagQ;
        }
    }
    
    protected abstract R zzc(final Status p0);
    
    @Override
    public Integer zzpa() {
        return this.zzagR;
    }
    
    protected void zzpf() {
    }
    
    public void zzpg() {
        while (true) {
            synchronized (this.zzagI) {
                if (this.zzagK.get() == null) {
                    this.cancel();
                    return;
                }
                if (this.zzagM == null || this.zzagM instanceof zzx) {
                    this.zzagP = true;
                    return;
                }
            }
            this.cancel();
        }
    }
    
    public final void zzx(final Status status) {
        synchronized (this.zzagI) {
            if (!this.isReady()) {
                this.zza(this.zzc(status));
                this.zzagO = true;
            }
        }
    }
    
    public static class zza<R extends Result> extends Handler
    {
        public zza() {
            this(Looper.getMainLooper());
        }
        
        public zza(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, (Throwable)new Exception());
                }
                case 1: {
                    final Pair pair = (Pair)message.obj;
                    this.zzb((ResultCallback<? super Result>)pair.first, (Result)pair.second);
                }
                case 2: {
                    ((zzb)message.obj).zzx(Status.zzagF);
                }
            }
        }
        
        public void zza(final ResultCallback<? super R> resultCallback, final R r) {
            this.sendMessage(this.obtainMessage(1, (Object)new Pair((Object)resultCallback, (Object)r)));
        }
        
        public void zza(final zzb<R> zzb, final long n) {
            this.sendMessageDelayed(this.obtainMessage(2, (Object)zzb), n);
        }
        
        protected void zzb(final ResultCallback<? super R> resultCallback, final R r) {
            try {
                resultCallback.onResult(r);
            }
            catch (RuntimeException ex) {
                zzb.zzc(r);
                throw ex;
            }
        }
        
        public void zzph() {
            this.removeMessages(2);
        }
    }
}
