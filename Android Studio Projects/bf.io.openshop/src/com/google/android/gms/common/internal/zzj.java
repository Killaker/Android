package com.google.android.gms.common.internal;

import android.accounts.*;
import java.util.concurrent.atomic.*;
import android.util.*;
import java.io.*;
import java.text.*;
import java.util.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.common.*;
import android.support.annotation.*;
import android.app.*;
import android.content.*;
import android.os.*;

public abstract class zzj<T extends IInterface> implements Api.zzb, zzk.zza
{
    public static final String[] zzalJ;
    private final Context mContext;
    final Handler mHandler;
    private final Account zzTI;
    private final Set<Scope> zzXf;
    private final Looper zzagr;
    private final com.google.android.gms.common.zzc zzags;
    private final com.google.android.gms.common.internal.zzf zzahz;
    private GoogleApiClient.zza zzalA;
    private T zzalB;
    private final ArrayList<zzc<?>> zzalC;
    private zze zzalD;
    private int zzalE;
    private final GoogleApiClient.ConnectionCallbacks zzalF;
    private final GoogleApiClient.OnConnectionFailedListener zzalG;
    private final int zzalH;
    protected AtomicInteger zzalI;
    private int zzals;
    private long zzalt;
    private long zzalu;
    private int zzalv;
    private long zzalw;
    private final zzl zzalx;
    private final Object zzaly;
    private zzs zzalz;
    private final Object zzpV;
    
    static {
        zzalJ = new String[] { "service_esmobile", "service_googleme" };
    }
    
    protected zzj(final Context context, final Looper looper, final int n, final com.google.android.gms.common.internal.zzf zzf, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, zzl.zzau(context), com.google.android.gms.common.zzc.zzoK(), n, zzf, zzx.zzz(connectionCallbacks), zzx.zzz(onConnectionFailedListener));
    }
    
    protected zzj(final Context context, final Looper looper, final zzl zzl, final com.google.android.gms.common.zzc zzc, final int zzalH, final com.google.android.gms.common.internal.zzf zzf, final GoogleApiClient.ConnectionCallbacks zzalF, final GoogleApiClient.OnConnectionFailedListener zzalG) {
        this.zzpV = new Object();
        this.zzaly = new Object();
        this.zzalA = new zzf();
        this.zzalC = new ArrayList<zzc<?>>();
        this.zzalE = 1;
        this.zzalI = new AtomicInteger(0);
        this.mContext = zzx.zzb(context, "Context must not be null");
        this.zzagr = zzx.zzb(looper, "Looper must not be null");
        this.zzalx = zzx.zzb(zzl, "Supervisor must not be null");
        this.zzags = zzx.zzb(zzc, "API availability must not be null");
        this.mHandler = new zzb(looper);
        this.zzalH = zzalH;
        this.zzahz = zzx.zzz(zzf);
        this.zzTI = zzf.getAccount();
        this.zzXf = this.zza(zzf.zzqt());
        this.zzalF = zzalF;
        this.zzalG = zzalG;
    }
    
    private Set<Scope> zza(final Set<Scope> set) {
        final Set<Scope> zzb = this.zzb(set);
        if (zzb == null) {
            return zzb;
        }
        final Iterator<Scope> iterator = zzb.iterator();
        while (iterator.hasNext()) {
            if (!set.contains(iterator.next())) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return zzb;
    }
    
    private boolean zza(final int n, final int n2, final T t) {
        synchronized (this.zzpV) {
            if (this.zzalE != n) {
                return false;
            }
            this.zzb(n2, t);
            return true;
        }
    }
    
    private void zzb(final int zzalE, final T zzalB) {
        boolean b = true;
        if ((zzalE == 3 && b) != (zzalB != null && b)) {
            b = false;
        }
        while (true) {
            zzx.zzac(b);
            Label_0107: {
                Label_0099: {
                    synchronized (this.zzpV) {
                        this.zzc(this.zzalE = zzalE, this.zzalB = zzalB);
                        switch (zzalE) {
                            case 2: {
                                this.zzqE();
                                break;
                            }
                            case 3: {
                                break Label_0099;
                            }
                            case 1: {
                                break Label_0107;
                            }
                        }
                        return;
                    }
                }
                this.zza(zzalB);
                return;
            }
            this.zzqF();
        }
    }
    
    private void zzqE() {
        if (this.zzalD != null) {
            Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + this.zzgu());
            this.zzalx.zzb(this.zzgu(), (ServiceConnection)this.zzalD, this.zzqD());
            this.zzalI.incrementAndGet();
        }
        this.zzalD = new zze(this.zzalI.get());
        if (!this.zzalx.zza(this.zzgu(), (ServiceConnection)this.zzalD, this.zzqD())) {
            Log.e("GmsClient", "unable to connect to service: " + this.zzgu());
            this.zzm(8, this.zzalI.get());
        }
    }
    
    private void zzqF() {
        if (this.zzalD != null) {
            this.zzalx.zzb(this.zzgu(), (ServiceConnection)this.zzalD, this.zzqD());
            this.zzalD = null;
        }
    }
    
    @Override
    public void disconnect() {
        this.zzalI.incrementAndGet();
        final ArrayList<zzc<?>> zzalC = this.zzalC;
        final Object zzaly;
        synchronized (zzalC) {
            for (int size = this.zzalC.size(), i = 0; i < size; ++i) {
                this.zzalC.get(i).zzqO();
            }
            this.zzalC.clear();
            // monitorexit(zzalC)
            zzaly = this.zzaly;
            // monitorenter(zzaly)
            final zzj zzj = this;
            final zzs zzs = null;
            zzj.zzalz = zzs;
            final Object o = zzaly;
            // monitorexit(o)
            final zzj zzj2 = this;
            final int n = 1;
            final IInterface interface1 = null;
            zzj2.zzb(n, (T)interface1);
            return;
        }
        try {
            final zzj zzj = this;
            final zzs zzs = null;
            zzj.zzalz = zzs;
            final Object o = zzaly;
            // monitorexit(o)
            final zzj zzj2 = this;
            final int n = 1;
            final IInterface interface1 = null;
            zzj2.zzb(n, (T)interface1);
        }
        finally {
        }
        // monitorexit(zzaly)
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        int zzalE;
        IInterface zzalB = null;
        SimpleDateFormat simpleDateFormat;
        Label_0095_Outer:Label_0236_Outer:
        while (true) {
            while (true) {
                Label_0464: {
                Label_0453:
                    while (true) {
                    Label_0420:
                        while (true) {
                            Label_0410: {
                                Label_0400: {
                                    Label_0390: {
                                        synchronized (this.zzpV) {
                                            zzalE = this.zzalE;
                                            zzalB = this.zzalB;
                                            // monitorexit(this.zzpV)
                                            printWriter.append(s).append("mConnectState=");
                                            switch (zzalE) {
                                                default: {
                                                    printWriter.print("UNKNOWN");
                                                    printWriter.append(" mService=");
                                                    if (zzalB == null) {
                                                        printWriter.println("null");
                                                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
                                                        if (this.zzalu > 0L) {
                                                            printWriter.append(s).append("lastConnectedTime=").println(this.zzalu + " " + simpleDateFormat.format(new Date(this.zzalu)));
                                                        }
                                                        if (this.zzalt > 0L) {
                                                            printWriter.append(s).append("lastSuspendedCause=");
                                                            switch (this.zzals) {
                                                                default: {
                                                                    printWriter.append(String.valueOf(this.zzals));
                                                                    printWriter.append(" lastSuspendedTime=").println(this.zzalt + " " + simpleDateFormat.format(new Date(this.zzalt)));
                                                                    break;
                                                                }
                                                                case 1: {
                                                                    break Label_0453;
                                                                }
                                                                case 2: {
                                                                    break Label_0464;
                                                                }
                                                            }
                                                        }
                                                        if (this.zzalw > 0L) {
                                                            printWriter.append(s).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzalv));
                                                            printWriter.append(" lastFailedTime=").println(this.zzalw + " " + simpleDateFormat.format(new Date(this.zzalw)));
                                                        }
                                                        return;
                                                    }
                                                    break Label_0420;
                                                }
                                                case 2: {
                                                    break;
                                                }
                                                case 3: {
                                                    break Label_0390;
                                                }
                                                case 4: {
                                                    break Label_0400;
                                                }
                                                case 1: {
                                                    break Label_0410;
                                                }
                                            }
                                        }
                                        printWriter.print("CONNECTING");
                                        continue Label_0095_Outer;
                                    }
                                    printWriter.print("CONNECTED");
                                    continue Label_0095_Outer;
                                }
                                printWriter.print("DISCONNECTING");
                                continue Label_0095_Outer;
                            }
                            printWriter.print("DISCONNECTED");
                            continue Label_0095_Outer;
                        }
                        printWriter.append(this.zzgv()).append("@").println(Integer.toHexString(System.identityHashCode(zzalB.asBinder())));
                        continue Label_0236_Outer;
                    }
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    continue;
                }
                printWriter.append("CAUSE_NETWORK_LOST");
                continue;
            }
        }
    }
    
    public final Context getContext() {
        return this.mContext;
    }
    
    public final Looper getLooper() {
        return this.zzagr;
    }
    
    @Override
    public boolean isConnected() {
        while (true) {
            synchronized (this.zzpV) {
                if (this.zzalE == 3) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean isConnecting() {
        while (true) {
            synchronized (this.zzpV) {
                if (this.zzalE == 2) {
                    return true;
                }
            }
            return false;
        }
    }
    
    @CallSuper
    protected void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzalv = connectionResult.getErrorCode();
        this.zzalw = System.currentTimeMillis();
    }
    
    @CallSuper
    protected void onConnectionSuspended(final int zzals) {
        this.zzals = zzals;
        this.zzalt = System.currentTimeMillis();
    }
    
    @Nullable
    protected abstract T zzW(final IBinder p0);
    
    @BinderThread
    protected void zza(final int n, final IBinder binder, final Bundle bundle, final int n2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, n2, -1, (Object)new zzg(n, binder, bundle)));
    }
    
    @CallSuper
    protected void zza(@NonNull final T t) {
        this.zzalu = System.currentTimeMillis();
    }
    
    @Override
    public void zza(@NonNull final GoogleApiClient.zza zza) {
        this.zzalA = zzx.zzb(zza, "Connection progress callbacks cannot be null.");
        this.zzb(2, null);
    }
    
    @WorkerThread
    @Override
    public void zza(final zzp p0, final Set<Scope> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/google/android/gms/common/internal/zzj.zzml:()Landroid/os/Bundle;
        //     4: astore          7
        //     6: new             Lcom/google/android/gms/common/internal/GetServiceRequest;
        //     9: dup            
        //    10: aload_0        
        //    11: getfield        com/google/android/gms/common/internal/zzj.zzalH:I
        //    14: invokespecial   com/google/android/gms/common/internal/GetServiceRequest.<init>:(I)V
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/common/internal/zzj.mContext:Landroid/content/Context;
        //    21: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    24: invokevirtual   com/google/android/gms/common/internal/GetServiceRequest.zzcG:(Ljava/lang/String;)Lcom/google/android/gms/common/internal/GetServiceRequest;
        //    27: aload           7
        //    29: invokevirtual   com/google/android/gms/common/internal/GetServiceRequest.zzj:(Landroid/os/Bundle;)Lcom/google/android/gms/common/internal/GetServiceRequest;
        //    32: astore          8
        //    34: aload_2        
        //    35: ifnull          45
        //    38: aload           8
        //    40: aload_2        
        //    41: invokevirtual   com/google/android/gms/common/internal/GetServiceRequest.zzd:(Ljava/util/Collection;)Lcom/google/android/gms/common/internal/GetServiceRequest;
        //    44: pop            
        //    45: aload_0        
        //    46: invokevirtual   com/google/android/gms/common/internal/zzj.zzmE:()Z
        //    49: ifeq            112
        //    52: aload           8
        //    54: aload_0        
        //    55: invokevirtual   com/google/android/gms/common/internal/zzj.zzqq:()Landroid/accounts/Account;
        //    58: invokevirtual   com/google/android/gms/common/internal/GetServiceRequest.zzc:(Landroid/accounts/Account;)Lcom/google/android/gms/common/internal/GetServiceRequest;
        //    61: aload_1        
        //    62: invokevirtual   com/google/android/gms/common/internal/GetServiceRequest.zzb:(Lcom/google/android/gms/common/internal/zzp;)Lcom/google/android/gms/common/internal/GetServiceRequest;
        //    65: pop            
        //    66: aload_0        
        //    67: getfield        com/google/android/gms/common/internal/zzj.zzaly:Ljava/lang/Object;
        //    70: astore          11
        //    72: aload           11
        //    74: monitorenter   
        //    75: aload_0        
        //    76: getfield        com/google/android/gms/common/internal/zzj.zzalz:Lcom/google/android/gms/common/internal/zzs;
        //    79: ifnull          150
        //    82: aload_0        
        //    83: getfield        com/google/android/gms/common/internal/zzj.zzalz:Lcom/google/android/gms/common/internal/zzs;
        //    86: new             Lcom/google/android/gms/common/internal/zzj$zzd;
        //    89: dup            
        //    90: aload_0        
        //    91: aload_0        
        //    92: getfield        com/google/android/gms/common/internal/zzj.zzalI:Ljava/util/concurrent/atomic/AtomicInteger;
        //    95: invokevirtual   java/util/concurrent/atomic/AtomicInteger.get:()I
        //    98: invokespecial   com/google/android/gms/common/internal/zzj$zzd.<init>:(Lcom/google/android/gms/common/internal/zzj;I)V
        //   101: aload           8
        //   103: invokeinterface com/google/android/gms/common/internal/zzs.zza:(Lcom/google/android/gms/common/internal/zzr;Lcom/google/android/gms/common/internal/GetServiceRequest;)V
        //   108: aload           11
        //   110: monitorexit    
        //   111: return         
        //   112: aload_0        
        //   113: invokevirtual   com/google/android/gms/common/internal/zzj.zzqK:()Z
        //   116: ifeq            66
        //   119: aload           8
        //   121: aload_0        
        //   122: getfield        com/google/android/gms/common/internal/zzj.zzTI:Landroid/accounts/Account;
        //   125: invokevirtual   com/google/android/gms/common/internal/GetServiceRequest.zzc:(Landroid/accounts/Account;)Lcom/google/android/gms/common/internal/GetServiceRequest;
        //   128: pop            
        //   129: goto            66
        //   132: astore          5
        //   134: ldc_w           "GmsClient"
        //   137: ldc_w           "service died"
        //   140: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   143: pop            
        //   144: aload_0        
        //   145: iconst_1       
        //   146: invokevirtual   com/google/android/gms/common/internal/zzj.zzbS:(I)V
        //   149: return         
        //   150: ldc_w           "GmsClient"
        //   153: ldc_w           "mServiceBroker is null, client disconnected"
        //   156: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   159: pop            
        //   160: goto            108
        //   163: astore          12
        //   165: aload           11
        //   167: monitorexit    
        //   168: aload           12
        //   170: athrow         
        //   171: astore_3       
        //   172: ldc_w           "GmsClient"
        //   175: ldc_w           "Remote exception occurred"
        //   178: aload_3        
        //   179: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   182: pop            
        //   183: return         
        //    Signature:
        //  (Lcom/google/android/gms/common/internal/zzp;Ljava/util/Set<Lcom/google/android/gms/common/api/Scope;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      34     132    150    Landroid/os/DeadObjectException;
        //  0      34     171    184    Landroid/os/RemoteException;
        //  38     45     132    150    Landroid/os/DeadObjectException;
        //  38     45     171    184    Landroid/os/RemoteException;
        //  45     66     132    150    Landroid/os/DeadObjectException;
        //  45     66     171    184    Landroid/os/RemoteException;
        //  66     75     132    150    Landroid/os/DeadObjectException;
        //  66     75     171    184    Landroid/os/RemoteException;
        //  75     108    163    171    Any
        //  108    111    163    171    Any
        //  112    129    132    150    Landroid/os/DeadObjectException;
        //  112    129    171    184    Landroid/os/RemoteException;
        //  150    160    163    171    Any
        //  165    168    163    171    Any
        //  168    171    132    150    Landroid/os/DeadObjectException;
        //  168    171    171    184    Landroid/os/RemoteException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @NonNull
    protected Set<Scope> zzb(@NonNull final Set<Scope> set) {
        return set;
    }
    
    public void zzbS(final int n) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.zzalI.get(), n));
    }
    
    void zzc(final int n, final T t) {
    }
    
    @NonNull
    protected abstract String zzgu();
    
    @NonNull
    protected abstract String zzgv();
    
    protected void zzm(final int n, final int n2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, n2, -1, (Object)new zzh(n)));
    }
    
    @Override
    public boolean zzmE() {
        return false;
    }
    
    protected Bundle zzml() {
        return new Bundle();
    }
    
    @Override
    public boolean zznb() {
        return false;
    }
    
    @Override
    public Intent zznc() {
        throw new UnsupportedOperationException("Not a sign in API");
    }
    
    @Nullable
    @Override
    public IBinder zzoT() {
        synchronized (this.zzaly) {
            if (this.zzalz == null) {
                return null;
            }
            return this.zzalz.asBinder();
        }
    }
    
    @Override
    public Bundle zzoi() {
        return null;
    }
    
    @Nullable
    protected final String zzqD() {
        return this.zzahz.zzqw();
    }
    
    public void zzqG() {
        final int googlePlayServicesAvailable = this.zzags.isGooglePlayServicesAvailable(this.mContext);
        if (googlePlayServicesAvailable != 0) {
            this.zzb(1, null);
            this.zzalA = new zzf();
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzalI.get(), googlePlayServicesAvailable));
            return;
        }
        this.zza(new zzf());
    }
    
    protected final com.google.android.gms.common.internal.zzf zzqH() {
        return this.zzahz;
    }
    
    protected final void zzqI() {
        if (!this.isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }
    
    public final T zzqJ() throws DeadObjectException {
        synchronized (this.zzpV) {
            if (this.zzalE == 4) {
                throw new DeadObjectException();
            }
        }
        this.zzqI();
        zzx.zza(this.zzalB != null, (Object)"Client is connected but service is null");
        final IInterface zzalB = this.zzalB;
        // monitorexit(o)
        return (T)zzalB;
    }
    
    public boolean zzqK() {
        return false;
    }
    
    public final Account zzqq() {
        if (this.zzTI != null) {
            return this.zzTI;
        }
        return new Account("<<default account>>", "com.google");
    }
    
    private abstract class zza extends zzc<Boolean>
    {
        public final int statusCode;
        public final Bundle zzalK;
        
        protected zza(final int statusCode, final Bundle zzalK) {
            super(true);
            this.statusCode = statusCode;
            this.zzalK = zzalK;
        }
        
        protected void zzc(final Boolean b) {
            if (b == null) {
                zzj.this.zzb(1, null);
            }
            else {
                switch (this.statusCode) {
                    default: {
                        zzj.this.zzb(1, null);
                        final Bundle zzalK = this.zzalK;
                        PendingIntent pendingIntent = null;
                        if (zzalK != null) {
                            pendingIntent = (PendingIntent)this.zzalK.getParcelable("pendingIntent");
                        }
                        this.zzj(new ConnectionResult(this.statusCode, pendingIntent));
                    }
                    case 0: {
                        if (!this.zzqL()) {
                            zzj.this.zzb(1, null);
                            this.zzj(new ConnectionResult(8, null));
                            return;
                        }
                        break;
                    }
                    case 10: {
                        zzj.this.zzb(1, null);
                        throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                    }
                }
            }
        }
        
        protected abstract void zzj(final ConnectionResult p0);
        
        protected abstract boolean zzqL();
        
        @Override
        protected void zzqM() {
        }
    }
    
    final class zzb extends Handler
    {
        public zzb(final Looper looper) {
            super(looper);
        }
        
        private void zza(final Message message) {
            final zzc zzc = (zzc)message.obj;
            zzc.zzqM();
            zzc.unregister();
        }
        
        private boolean zzb(final Message message) {
            return message.what == 2 || message.what == 1 || message.what == 5;
        }
        
        public void handleMessage(final Message message) {
            if (zzj.this.zzalI.get() != message.arg1) {
                if (this.zzb(message)) {
                    this.zza(message);
                }
                return;
            }
            if ((message.what == 1 || message.what == 5) && !zzj.this.isConnecting()) {
                this.zza(message);
                return;
            }
            if (message.what == 3) {
                final ConnectionResult connectionResult = new ConnectionResult(message.arg2, null);
                zzj.this.zzalA.zza(connectionResult);
                zzj.this.onConnectionFailed(connectionResult);
                return;
            }
            if (message.what == 4) {
                zzj.this.zzb(4, null);
                if (zzj.this.zzalF != null) {
                    zzj.this.zzalF.onConnectionSuspended(message.arg2);
                }
                zzj.this.onConnectionSuspended(message.arg2);
                zzj.this.zza(4, 1, null);
                return;
            }
            if (message.what == 2 && !zzj.this.isConnected()) {
                this.zza(message);
                return;
            }
            if (this.zzb(message)) {
                ((zzc)message.obj).zzqN();
                return;
            }
            Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, (Throwable)new Exception());
        }
    }
    
    protected abstract class zzc<TListener>
    {
        private TListener mListener;
        private boolean zzalM;
        
        public zzc(final TListener mListener) {
            this.mListener = mListener;
            this.zzalM = false;
        }
        
        public void unregister() {
            this.zzqO();
            synchronized (zzj.this.zzalC) {
                zzj.this.zzalC.remove(this);
            }
        }
        
        protected abstract void zzqM();
        
        public void zzqN() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: monitorenter   
            //     2: aload_0        
            //     3: getfield        com/google/android/gms/common/internal/zzj$zzc.mListener:Ljava/lang/Object;
            //     6: astore_2       
            //     7: aload_0        
            //     8: getfield        com/google/android/gms/common/internal/zzj$zzc.zzalM:Z
            //    11: ifeq            44
            //    14: ldc             "GmsClient"
            //    16: new             Ljava/lang/StringBuilder;
            //    19: dup            
            //    20: invokespecial   java/lang/StringBuilder.<init>:()V
            //    23: ldc             "Callback proxy "
            //    25: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    28: aload_0        
            //    29: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //    32: ldc             " being reused. This is not safe."
            //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    37: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    40: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
            //    43: pop            
            //    44: aload_0        
            //    45: monitorexit    
            //    46: aload_2        
            //    47: ifnull          83
            //    50: aload_0        
            //    51: aload_2        
            //    52: invokevirtual   com/google/android/gms/common/internal/zzj$zzc.zzw:(Ljava/lang/Object;)V
            //    55: aload_0        
            //    56: monitorenter   
            //    57: aload_0        
            //    58: iconst_1       
            //    59: putfield        com/google/android/gms/common/internal/zzj$zzc.zzalM:Z
            //    62: aload_0        
            //    63: monitorexit    
            //    64: aload_0        
            //    65: invokevirtual   com/google/android/gms/common/internal/zzj$zzc.unregister:()V
            //    68: return         
            //    69: astore_1       
            //    70: aload_0        
            //    71: monitorexit    
            //    72: aload_1        
            //    73: athrow         
            //    74: astore          4
            //    76: aload_0        
            //    77: invokevirtual   com/google/android/gms/common/internal/zzj$zzc.zzqM:()V
            //    80: aload           4
            //    82: athrow         
            //    83: aload_0        
            //    84: invokevirtual   com/google/android/gms/common/internal/zzj$zzc.zzqM:()V
            //    87: goto            55
            //    90: astore_3       
            //    91: aload_0        
            //    92: monitorexit    
            //    93: aload_3        
            //    94: athrow         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                        
            //  -----  -----  -----  -----  ----------------------------
            //  2      44     69     74     Any
            //  44     46     69     74     Any
            //  50     55     74     83     Ljava/lang/RuntimeException;
            //  57     64     90     95     Any
            //  70     72     69     74     Any
            //  91     93     90     95     Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public void zzqO() {
            synchronized (this) {
                this.mListener = null;
            }
        }
        
        protected abstract void zzw(final TListener p0);
    }
    
    public static final class zzd extends zzr.zza
    {
        private zzj zzalN;
        private final int zzalO;
        
        public zzd(@NonNull final zzj zzalN, final int zzalO) {
            this.zzalN = zzalN;
            this.zzalO = zzalO;
        }
        
        private void zzqP() {
            this.zzalN = null;
        }
        
        @BinderThread
        public void zza(final int n, @NonNull final IBinder binder, @Nullable final Bundle bundle) {
            zzx.zzb(this.zzalN, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzalN.zza(n, binder, bundle, this.zzalO);
            this.zzqP();
        }
        
        @BinderThread
        public void zzb(final int n, @Nullable final Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", (Throwable)new Exception());
        }
    }
    
    public final class zze implements ServiceConnection
    {
        private final int zzalO;
        
        public zze(final int zzalO) {
            this.zzalO = zzalO;
        }
        
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            zzx.zzb(binder, "Expecting a valid IBinder");
            synchronized (zzj.this.zzaly) {
                zzj.this.zzalz = zzs.zza.zzaS(binder);
                // monitorexit(zzj.zza(this.zzalL))
                zzj.this.zzm(0, this.zzalO);
            }
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
            synchronized (zzj.this.zzaly) {
                zzj.this.zzalz = null;
                // monitorexit(zzj.zza(this.zzalL))
                zzj.this.mHandler.sendMessage(zzj.this.mHandler.obtainMessage(4, this.zzalO, 1));
            }
        }
    }
    
    protected class zzf implements GoogleApiClient.zza
    {
        @Override
        public void zza(@NonNull final ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                zzj.this.zza(null, zzj.this.zzXf);
            }
            else if (zzj.this.zzalG != null) {
                zzj.this.zzalG.onConnectionFailed(connectionResult);
            }
        }
    }
    
    protected final class zzg extends zza
    {
        public final IBinder zzalP;
        
        public zzg(final int n, final IBinder zzalP, final Bundle bundle) {
            super(n, bundle);
            this.zzalP = zzalP;
        }
        
        @Override
        protected void zzj(final ConnectionResult connectionResult) {
            if (zzj.this.zzalG != null) {
                zzj.this.zzalG.onConnectionFailed(connectionResult);
            }
            zzj.this.onConnectionFailed(connectionResult);
        }
        
        @Override
        protected boolean zzqL() {
            while (true) {
                try {
                    final String interfaceDescriptor = this.zzalP.getInterfaceDescriptor();
                    if (!zzj.this.zzgv().equals(interfaceDescriptor)) {
                        Log.e("GmsClient", "service descriptor mismatch: " + zzj.this.zzgv() + " vs. " + interfaceDescriptor);
                        return false;
                    }
                }
                catch (RemoteException ex) {
                    Log.w("GmsClient", "service probably died");
                    return false;
                }
                final IInterface zzW = zzj.this.zzW(this.zzalP);
                if (zzW != null && zzj.this.zza(2, 3, zzW)) {
                    break;
                }
                return false;
            }
            final Bundle zzoi = zzj.this.zzoi();
            if (zzj.this.zzalF != null) {
                zzj.this.zzalF.onConnected(zzoi);
            }
            return true;
        }
    }
    
    protected final class zzh extends zza
    {
        public zzh(final int n) {
            super(n, null);
        }
        
        @Override
        protected void zzj(final ConnectionResult connectionResult) {
            zzj.this.zzalA.zza(connectionResult);
            zzj.this.onConnectionFailed(connectionResult);
        }
        
        @Override
        protected boolean zzqL() {
            zzj.this.zzalA.zza(ConnectionResult.zzafB);
            return true;
        }
    }
}
