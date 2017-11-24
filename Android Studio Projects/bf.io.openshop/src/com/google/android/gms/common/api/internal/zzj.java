package com.google.android.gms.common.api.internal;

import android.content.*;
import java.util.concurrent.locks.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import com.google.android.gms.internal.*;
import android.support.annotation.*;
import com.google.android.gms.common.*;
import java.util.concurrent.*;
import com.google.android.gms.common.api.*;
import java.util.concurrent.atomic.*;
import android.util.*;
import android.app.*;
import android.support.v4.app.*;
import java.io.*;
import java.lang.ref.*;
import android.os.*;

public final class zzj extends GoogleApiClient implements zzp.zza
{
    private final Context mContext;
    private final Lock zzXG;
    private final int zzagp;
    private final Looper zzagr;
    private final com.google.android.gms.common.zzc zzags;
    final Api.zza<? extends zzrn, zzro> zzagt;
    final Map<Api<?>, Integer> zzahA;
    private final zzk zzahL;
    private zzp zzahM;
    final Queue<com.google.android.gms.common.api.internal.zza.zza<?, ?>> zzahN;
    private volatile boolean zzahO;
    private long zzahP;
    private long zzahQ;
    private final zza zzahR;
    zzc zzahS;
    final Map<Api.zzc<?>, Api.zzb> zzahT;
    Set<Scope> zzahU;
    private final Set<zzq<?>> zzahV;
    final Set<zze<?>> zzahW;
    private com.google.android.gms.common.api.zza zzahX;
    private final ArrayList<com.google.android.gms.common.api.internal.zzc> zzahY;
    private Integer zzahZ;
    final zzf zzahz;
    Set<zzx> zzaia;
    private final zzd zzaib;
    private final zzk.zza zzaic;
    
    public zzj(final Context mContext, final Lock zzXG, final Looper zzagr, final zzf zzahz, final com.google.android.gms.common.zzc zzags, final Api.zza<? extends zzrn, zzro> zzagt, final Map<Api<?>, Integer> zzahA, final List<ConnectionCallbacks> list, final List<OnConnectionFailedListener> list2, final Map<Api.zzc<?>, Api.zzb> zzahT, final int zzagp, final int n, final ArrayList<com.google.android.gms.common.api.internal.zzc> zzahY) {
        this.zzahM = null;
        this.zzahN = new LinkedList<com.google.android.gms.common.api.internal.zza.zza<?, ?>>();
        this.zzahP = 120000L;
        this.zzahQ = 5000L;
        this.zzahU = new HashSet<Scope>();
        this.zzahV = Collections.newSetFromMap(new WeakHashMap<zzq<?>, Boolean>());
        this.zzahW = Collections.newSetFromMap(new ConcurrentHashMap<zze<?>, Boolean>(16, 0.75f, 2));
        this.zzahZ = null;
        this.zzaia = null;
        this.zzaib = (zzd)new zzd() {
            @Override
            public void zzc(final zze<?> zze) {
                zzj.this.zzahW.remove(zze);
                if (zze.zzpa() != null && zzj.this.zzahX != null) {
                    zzj.this.zzahX.remove(zze.zzpa());
                }
            }
        };
        this.zzaic = new zzk.zza() {
            @Override
            public boolean isConnected() {
                return zzj.this.isConnected();
            }
            
            @Override
            public Bundle zzoi() {
                return null;
            }
        };
        this.mContext = mContext;
        this.zzXG = zzXG;
        this.zzahL = new zzk(zzagr, this.zzaic);
        this.zzagr = zzagr;
        this.zzahR = new zza(zzagr);
        this.zzags = zzags;
        this.zzagp = zzagp;
        if (this.zzagp >= 0) {
            this.zzahZ = n;
        }
        this.zzahA = zzahA;
        this.zzahT = zzahT;
        this.zzahY = zzahY;
        final Iterator<ConnectionCallbacks> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.zzahL.registerConnectionCallbacks(iterator.next());
        }
        final Iterator<OnConnectionFailedListener> iterator2 = list2.iterator();
        while (iterator2.hasNext()) {
            this.zzahL.registerConnectionFailedListener(iterator2.next());
        }
        this.zzahz = zzahz;
        this.zzagt = zzagt;
    }
    
    private void resume() {
        this.zzXG.lock();
        try {
            if (this.zzpB()) {
                this.zzpC();
            }
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    public static int zza(final Iterable<Api.zzb> iterable, final boolean b) {
        int n = 1;
        final Iterator<Api.zzb> iterator = iterable.iterator();
        int n2 = 0;
        int n3 = 0;
        while (iterator.hasNext()) {
            final Api.zzb zzb = iterator.next();
            if (zzb.zzmE()) {
                n3 = n;
            }
            int n4;
            if (zzb.zznb()) {
                n4 = n;
            }
            else {
                n4 = n2;
            }
            n2 = n4;
        }
        if (n3 != 0) {
            if (n2 != 0 && b) {
                n = 2;
            }
            return n;
        }
        return 3;
    }
    
    private void zza(final GoogleApiClient googleApiClient, final zzv zzv, final boolean b) {
        zzmf.zzamA.zzf(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            public void zzp(@NonNull final Status status) {
                com.google.android.gms.auth.api.signin.internal.zzq.zzaf(zzj.this.mContext).zznr();
                if (status.isSuccess() && zzj.this.isConnected()) {
                    zzj.this.reconnect();
                }
                zzv.zza(status);
                if (b) {
                    googleApiClient.disconnect();
                }
            }
        });
    }
    
    private static void zza(final zze<?> zze, final com.google.android.gms.common.api.zza zza, final IBinder binder) {
        if (zze.isReady()) {
            zze.zza(new zzb((zze)zze, zza, binder));
            return;
        }
        if (binder != null && binder.isBinderAlive()) {
            final zzb zzb = new zzb((zze)zze, zza, binder);
            zze.zza(zzb);
            try {
                binder.linkToDeath((IBinder$DeathRecipient)zzb, 0);
                return;
            }
            catch (RemoteException ex) {
                zze.cancel();
                zza.remove(zze.zzpa());
                return;
            }
        }
        zze.zza(null);
        zze.cancel();
        zza.remove(zze.zzpa());
    }
    
    private void zzbB(final int n) {
        if (this.zzahZ == null) {
            this.zzahZ = n;
        }
        else if (this.zzahZ != n) {
            throw new IllegalStateException("Cannot use sign-in mode: " + zzbC(n) + ". Mode was already set to " + zzbC(this.zzahZ));
        }
        if (this.zzahM != null) {
            return;
        }
        final Iterator<Api.zzb> iterator = this.zzahT.values().iterator();
        int n2 = 0;
        boolean b = false;
        while (iterator.hasNext()) {
            final Api.zzb zzb = iterator.next();
            if (zzb.zzmE()) {
                b = true;
            }
            int n3;
            if (zzb.zznb()) {
                n3 = 1;
            }
            else {
                n3 = n2;
            }
            n2 = n3;
        }
        switch (this.zzahZ) {
            case 1: {
                if (!b) {
                    throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                }
                if (n2 != 0) {
                    throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
                break;
            }
            case 2: {
                if (b) {
                    this.zzahM = new com.google.android.gms.common.api.internal.zzd(this.mContext, this, this.zzXG, this.zzagr, this.zzags, this.zzahT, this.zzahz, this.zzahA, this.zzagt, this.zzahY);
                    return;
                }
                break;
            }
        }
        this.zzahM = new zzl(this.mContext, this, this.zzXG, this.zzagr, this.zzags, this.zzahT, this.zzahz, this.zzahA, this.zzagt, this.zzahY, this);
    }
    
    static String zzbC(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN";
            }
            case 3: {
                return "SIGN_IN_MODE_NONE";
            }
            case 1: {
                return "SIGN_IN_MODE_REQUIRED";
            }
            case 2: {
                return "SIGN_IN_MODE_OPTIONAL";
            }
        }
    }
    
    private void zzpC() {
        this.zzahL.zzqR();
        this.zzahM.connect();
    }
    
    private void zzpD() {
        this.zzXG.lock();
        try {
            if (this.zzpF()) {
                this.zzpC();
            }
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    @Override
    public ConnectionResult blockingConnect() {
        boolean b = true;
        Label_0091: {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                break Label_0091;
            }
            boolean b2 = b;
        Label_0050_Outer:
            while (true) {
                com.google.android.gms.common.internal.zzx.zza(b2, (Object)"blockingConnect must not be called on the UI thread");
                this.zzXG.lock();
                while (true) {
                    Label_0143: {
                        try {
                            if (this.zzagp >= 0) {
                                if (this.zzahZ == null) {
                                    b = false;
                                }
                                com.google.android.gms.common.internal.zzx.zza(b, (Object)"Sign-in mode should have been set explicitly by auto-manage.");
                            }
                            else {
                                if (this.zzahZ != null) {
                                    break Label_0143;
                                }
                                this.zzahZ = zza(this.zzahT.values(), false);
                            }
                            this.zzbB(this.zzahZ);
                            this.zzahL.zzqR();
                            return this.zzahM.blockingConnect();
                            b2 = false;
                            continue Label_0050_Outer;
                        }
                        finally {
                            this.zzXG.unlock();
                        }
                    }
                    if (this.zzahZ == 2) {
                        break;
                    }
                    continue;
                }
            }
        }
        throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }
    
    @Override
    public ConnectionResult blockingConnect(final long n, @NonNull final TimeUnit timeUnit) {
        final Looper myLooper = Looper.myLooper();
        final Looper mainLooper = Looper.getMainLooper();
        boolean b = false;
        if (myLooper != mainLooper) {
            b = true;
        }
        com.google.android.gms.common.internal.zzx.zza(b, (Object)"blockingConnect must not be called on the UI thread");
        com.google.android.gms.common.internal.zzx.zzb(timeUnit, "TimeUnit must not be null");
        this.zzXG.lock();
        try {
            if (this.zzahZ == null) {
                this.zzahZ = zza(this.zzahT.values(), false);
            }
            else if (this.zzahZ == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            this.zzbB(this.zzahZ);
            this.zzahL.zzqR();
            return this.zzahM.blockingConnect(n, timeUnit);
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    @Override
    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        com.google.android.gms.common.internal.zzx.zza(this.isConnected(), (Object)"GoogleApiClient is not connected yet.");
        com.google.android.gms.common.internal.zzx.zza(this.zzahZ != 2, (Object)"Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        final zzv zzv = new zzv(this);
        if (this.zzahT.containsKey(zzmf.zzUI)) {
            this.zza(this, zzv, false);
            return zzv;
        }
        final AtomicReference<GoogleApiClient> atomicReference = new AtomicReference<GoogleApiClient>();
        final GoogleApiClient build = new Builder(this.mContext).addApi(zzmf.API).addConnectionCallbacks(new ConnectionCallbacks() {
            @Override
            public void onConnected(final Bundle bundle) {
                zzj.this.zza(atomicReference.get(), zzv, true);
            }
            
            @Override
            public void onConnectionSuspended(final int n) {
            }
        }).addOnConnectionFailedListener(new OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
                zzv.zza(new Status(8));
            }
        }).setHandler(this.zzahR).build();
        atomicReference.set(build);
        build.connect();
        return zzv;
    }
    
    @Override
    public void connect() {
        while (true) {
            this.zzXG.lock();
            while (true) {
                Label_0099: {
                    try {
                        if (this.zzagp >= 0) {
                            final Integer zzahZ = this.zzahZ;
                            boolean b = false;
                            if (zzahZ != null) {
                                b = true;
                            }
                            com.google.android.gms.common.internal.zzx.zza(b, (Object)"Sign-in mode should have been set explicitly by auto-manage.");
                        }
                        else {
                            if (this.zzahZ != null) {
                                break Label_0099;
                            }
                            this.zzahZ = zza(this.zzahT.values(), false);
                        }
                        this.connect(this.zzahZ);
                        return;
                    }
                    finally {
                        this.zzXG.unlock();
                    }
                }
                if (this.zzahZ == 2) {
                    break;
                }
                continue;
            }
        }
        throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }
    
    @Override
    public void connect(final int n) {
        boolean b = true;
        this.zzXG.lock();
        Label_0069: {
            if (n != 3 && n != (b ? 1 : 0)) {
                if (n != 2) {
                    break Label_0069;
                }
            }
            try {
                while (true) {
                    com.google.android.gms.common.internal.zzx.zzb(b, (Object)("Illegal sign-in mode: " + n));
                    this.zzbB(n);
                    this.zzpC();
                    return;
                    b = false;
                    continue;
                }
            }
            finally {
                this.zzXG.unlock();
            }
        }
    }
    
    @Override
    public void disconnect() {
        while (true) {
            this.zzXG.lock();
            while (true) {
                try {
                    if (this.zzahM != null && !this.zzahM.disconnect()) {
                        final boolean b = true;
                        this.zzaa(b);
                        final Iterator<zzq<?>> iterator = this.zzahV.iterator();
                        while (iterator.hasNext()) {
                            iterator.next().clear();
                        }
                        break;
                    }
                }
                finally {
                    this.zzXG.unlock();
                }
                final boolean b = false;
                continue;
            }
        }
        this.zzahV.clear();
        for (final zze zze : this.zzahN) {
            zze.zza(null);
            zze.cancel();
        }
        this.zzahN.clear();
        if (this.zzahM == null) {
            this.zzXG.unlock();
            return;
        }
        this.zzpF();
        this.zzahL.zzqQ();
        this.zzXG.unlock();
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("mContext=").println(this.mContext);
        printWriter.append(s).append("mResuming=").print(this.zzahO);
        printWriter.append(" mWorkQueue.size()=").print(this.zzahN.size());
        printWriter.append(" mUnconsumedRunners.size()=").println(this.zzahW.size());
        if (this.zzahM != null) {
            this.zzahM.dump(s, fileDescriptor, printWriter, array);
        }
    }
    
    @NonNull
    @Override
    public ConnectionResult getConnectionResult(@NonNull final Api<?> api) {
        this.zzXG.lock();
        try {
            if (!this.isConnected() && !this.zzpB()) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            }
        }
        finally {
            this.zzXG.unlock();
        }
        if (!this.zzahT.containsKey(api.zzoR())) {
            throw new IllegalArgumentException(api.getName() + " was never registered with GoogleApiClient");
        }
        final ConnectionResult connectionResult = this.zzahM.getConnectionResult(api);
        if (connectionResult != null) {
            this.zzXG.unlock();
            return connectionResult;
        }
        if (this.zzpB()) {
            final ConnectionResult zzafB = ConnectionResult.zzafB;
            this.zzXG.unlock();
            return zzafB;
        }
        Log.i("GoogleApiClientImpl", this.zzpH());
        Log.wtf("GoogleApiClientImpl", api.getName() + " requested in getConnectionResult" + " is not connected but is not present in the failed " + " connections map", (Throwable)new Exception());
        final ConnectionResult connectionResult2 = new ConnectionResult(8, null);
        this.zzXG.unlock();
        return connectionResult2;
    }
    
    @Override
    public Context getContext() {
        return this.mContext;
    }
    
    @Override
    public Looper getLooper() {
        return this.zzagr;
    }
    
    public int getSessionId() {
        return System.identityHashCode(this);
    }
    
    @Override
    public boolean hasConnectedApi(@NonNull final Api<?> api) {
        final Api.zzb zzb = this.zzahT.get(api.zzoR());
        return zzb != null && zzb.isConnected();
    }
    
    @Override
    public boolean isConnected() {
        return this.zzahM != null && this.zzahM.isConnected();
    }
    
    @Override
    public boolean isConnecting() {
        return this.zzahM != null && this.zzahM.isConnecting();
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(@NonNull final ConnectionCallbacks connectionCallbacks) {
        return this.zzahL.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        return this.zzahL.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    @Override
    public void reconnect() {
        this.disconnect();
        this.connect();
    }
    
    @Override
    public void registerConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
        this.zzahL.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        this.zzahL.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Override
    public void stopAutoManage(@NonNull final FragmentActivity fragmentActivity) {
        if (this.zzagp < 0) {
            throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
        }
        final zzw zza = zzw.zza(fragmentActivity);
        if (zza == null) {
            new Handler(this.mContext.getMainLooper()).post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (fragmentActivity.isFinishing() || fragmentActivity.getSupportFragmentManager().isDestroyed()) {
                        return;
                    }
                    zzw.zzb(fragmentActivity).zzbD(zzj.this.zzagp);
                }
            });
            return;
        }
        zza.zzbD(this.zzagp);
    }
    
    @Override
    public void unregisterConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
        this.zzahL.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        this.zzahL.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    @NonNull
    @Override
    public <C extends Api.zzb> C zza(@NonNull final Api.zzc<C> zzc) {
        final Api.zzb zzb = this.zzahT.get(zzc);
        com.google.android.gms.common.internal.zzx.zzb(zzb, "Appropriate Api was not requested.");
        return (C)zzb;
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends com.google.android.gms.common.api.internal.zza.zza<R, A>> T zza(@NonNull final T t) {
        Label_0073: {
            if (t.zzoR() == null) {
                break Label_0073;
            }
            boolean b = true;
            while (true) {
                com.google.android.gms.common.internal.zzx.zzb(b, (Object)"This task can not be enqueued (it's probably a Batch or malformed)");
                com.google.android.gms.common.internal.zzx.zzb(this.zzahT.containsKey(t.zzoR()), (Object)"GoogleApiClient is not configured to use the API required for this call.");
                this.zzXG.lock();
                try {
                    if (this.zzahM == null) {
                        this.zzahN.add(t);
                        return t;
                    }
                    return this.zzahM.zza(t);
                    b = false;
                }
                finally {
                    this.zzXG.unlock();
                }
            }
        }
    }
    
    @Override
    public void zza(final zzx zzx) {
        this.zzXG.lock();
        try {
            if (this.zzaia == null) {
                this.zzaia = new HashSet<zzx>();
            }
            this.zzaia.add(zzx);
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    @Override
    public boolean zza(@NonNull final Api<?> api) {
        return this.zzahT.containsKey(api.zzoR());
    }
    
    @Override
    public boolean zza(final zzu zzu) {
        return this.zzahM != null && this.zzahM.zza(zzu);
    }
    
    void zzaa(final boolean b) {
        for (final zze<?> zze : this.zzahW) {
            if (zze.zzpa() == null) {
                if (b) {
                    zze.zzpg();
                }
                else {
                    zze.cancel();
                    this.zzahW.remove(zze);
                }
            }
            else {
                zze.zzpe();
                zza(zze, this.zzahX, this.zza((Api.zzc<Api.zzb>)zze.zzoR()).zzoT());
                this.zzahW.remove(zze);
            }
        }
    }
    
    @Override
    public <A extends Api.zzb, T extends com.google.android.gms.common.api.internal.zza.zza<? extends Result, A>> T zzb(@NonNull final T t) {
        while (true) {
            Label_0055: {
                if (t.zzoR() == null) {
                    break Label_0055;
                }
                final boolean b = true;
                com.google.android.gms.common.internal.zzx.zzb(b, (Object)"This task can not be executed (it's probably a Batch or malformed)");
                this.zzXG.lock();
                Label_0060: {
                    try {
                        if (this.zzahM == null) {
                            throw new IllegalStateException("GoogleApiClient is not connected yet.");
                        }
                        break Label_0060;
                    }
                    finally {
                        this.zzXG.unlock();
                    }
                    break Label_0055;
                }
                if (this.zzpB()) {
                    this.zzahN.add(t);
                    while (!this.zzahN.isEmpty()) {
                        final zze zze = (zze)this.zzahN.remove();
                        this.zzb((zze<Api.zzb>)zze);
                        zze.zzw(Status.zzagE);
                    }
                    this.zzXG.unlock();
                    return t;
                }
                final com.google.android.gms.common.api.internal.zza.zza<? extends Result, A> zzb = this.zzahM.zzb(t);
                this.zzXG.unlock();
                return (T)zzb;
            }
            final boolean b = false;
            continue;
        }
    }
    
     <A extends Api.zzb> void zzb(final zze<A> zze) {
        this.zzahW.add(zze);
        zze.zza(this.zzaib);
    }
    
    @Override
    public void zzb(final zzx zzx) {
        while (true) {
            this.zzXG.lock();
            Label_0088: {
                try {
                    if (this.zzaia == null) {
                        Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", (Throwable)new Exception());
                    }
                    else {
                        if (this.zzaia.remove(zzx)) {
                            break Label_0088;
                        }
                        Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", (Throwable)new Exception());
                    }
                    return;
                }
                finally {
                    this.zzXG.unlock();
                }
            }
            if (!this.zzpG()) {
                this.zzahM.zzpj();
            }
        }
    }
    
    @Override
    public void zzc(final int n, final boolean b) {
        if (n == 1 && !b) {
            this.zzpE();
        }
        for (final zze<?> zze : this.zzahW) {
            if (b) {
                zze.zzpe();
            }
            zze.zzx(new Status(8, "The connection to Google Play services was lost"));
        }
        this.zzahW.clear();
        this.zzahL.zzbT(n);
        this.zzahL.zzqQ();
        if (n == 2) {
            this.zzpC();
        }
    }
    
    @Override
    public void zzd(final ConnectionResult connectionResult) {
        if (!this.zzags.zzd(this.mContext, connectionResult.getErrorCode())) {
            this.zzpF();
        }
        if (!this.zzpB()) {
            this.zzahL.zzk(connectionResult);
            this.zzahL.zzqQ();
        }
    }
    
    @Override
    public void zzi(final Bundle bundle) {
        while (!this.zzahN.isEmpty()) {
            this.zzb(this.zzahN.remove());
        }
        this.zzahL.zzk(bundle);
    }
    
    @Override
    public void zzoW() {
        if (this.zzahM != null) {
            this.zzahM.zzoW();
        }
    }
    
    boolean zzpB() {
        return this.zzahO;
    }
    
    void zzpE() {
        if (this.zzpB()) {
            return;
        }
        this.zzahO = true;
        if (this.zzahS == null) {
            this.zzahS = zzn.zza(this.mContext.getApplicationContext(), new zzc(this), this.zzags);
        }
        this.zzahR.sendMessageDelayed(this.zzahR.obtainMessage(1), this.zzahP);
        this.zzahR.sendMessageDelayed(this.zzahR.obtainMessage(2), this.zzahQ);
    }
    
    boolean zzpF() {
        if (!this.zzpB()) {
            return false;
        }
        this.zzahO = false;
        this.zzahR.removeMessages(2);
        this.zzahR.removeMessages(1);
        if (this.zzahS != null) {
            this.zzahS.unregister();
            this.zzahS = null;
        }
        return true;
    }
    
    boolean zzpG() {
        this.zzXG.lock();
        try {
            if (this.zzaia == null) {
                return false;
            }
            final boolean empty = this.zzaia.isEmpty();
            boolean b = false;
            if (!empty) {
                b = true;
            }
            return b;
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    String zzpH() {
        final StringWriter stringWriter = new StringWriter();
        this.dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }
    
    @Override
    public <L> zzq<L> zzr(@NonNull final L l) {
        com.google.android.gms.common.internal.zzx.zzb(l, "Listener must not be null");
        this.zzXG.lock();
        try {
            final zzq<Object> zzq = new zzq<Object>(this.zzagr, l);
            this.zzahV.add(zzq);
            return (zzq<L>)zzq;
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    final class zza extends Handler
    {
        zza(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
                }
                case 1: {
                    zzj.this.zzpD();
                }
                case 2: {
                    zzj.this.resume();
                }
            }
        }
    }
    
    private static class zzb implements IBinder$DeathRecipient, zzd
    {
        private final WeakReference<zze<?>> zzaii;
        private final WeakReference<com.google.android.gms.common.api.zza> zzaij;
        private final WeakReference<IBinder> zzaik;
        
        private zzb(final zze zze, final com.google.android.gms.common.api.zza zza, final IBinder binder) {
            this.zzaij = new WeakReference<com.google.android.gms.common.api.zza>(zza);
            this.zzaii = new WeakReference<zze<?>>(zze);
            this.zzaik = new WeakReference<IBinder>(binder);
        }
        
        private void zzpI() {
            final zze zze = this.zzaii.get();
            final com.google.android.gms.common.api.zza zza = this.zzaij.get();
            if (zza != null && zze != null) {
                zza.remove(zze.zzpa());
            }
            final IBinder binder = this.zzaik.get();
            if (this.zzaik != null) {
                binder.unlinkToDeath((IBinder$DeathRecipient)this, 0);
            }
        }
        
        public void binderDied() {
            this.zzpI();
        }
        
        public void zzc(final zze<?> zze) {
            this.zzpI();
        }
    }
    
    static class zzc extends zzn
    {
        private WeakReference<zzj> zzail;
        
        zzc(final zzj zzj) {
            this.zzail = new WeakReference<zzj>(zzj);
        }
        
        public void zzpJ() {
            final zzj zzj = this.zzail.get();
            if (zzj == null) {
                return;
            }
            zzj.resume();
        }
    }
    
    interface zzd
    {
        void zzc(final zze<?> p0);
    }
    
    interface zze<A extends Api.zzb>
    {
        void cancel();
        
        boolean isReady();
        
        void zza(final zzd p0);
        
        void zzb(final A p0) throws DeadObjectException;
        
        Api.zzc<A> zzoR();
        
        Integer zzpa();
        
        void zzpe();
        
        void zzpg();
        
        void zzw(final Status p0);
        
        void zzx(final Status p0);
    }
}
