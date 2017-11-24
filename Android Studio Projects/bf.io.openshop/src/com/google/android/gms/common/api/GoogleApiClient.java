package com.google.android.gms.common.api;

import java.io.*;
import java.util.concurrent.*;
import android.content.*;
import android.support.v4.app.*;
import android.accounts.*;
import android.view.*;
import com.google.android.gms.common.*;
import android.support.v4.util.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.api.internal.*;
import java.util.concurrent.locks.*;
import java.util.*;
import android.support.annotation.*;
import android.os.*;

public abstract class GoogleApiClient
{
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    private static final Set<GoogleApiClient> zzagg;
    
    static {
        zzagg = Collections.newSetFromMap(new WeakHashMap<GoogleApiClient, Boolean>());
    }
    
    public static void dumpAll(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        synchronized (GoogleApiClient.zzagg) {
            final String string = s + "  ";
            final Iterator<GoogleApiClient> iterator = GoogleApiClient.zzagg.iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final GoogleApiClient googleApiClient = iterator.next();
                final PrintWriter append = printWriter.append(s).append("GoogleApiClient#");
                final int n2 = n + 1;
                append.println(n);
                googleApiClient.dump(string, fileDescriptor, printWriter, array);
                n = n2;
            }
        }
    }
    
    public static Set<GoogleApiClient> zzoV() {
        return GoogleApiClient.zzagg;
    }
    
    public abstract ConnectionResult blockingConnect();
    
    public abstract ConnectionResult blockingConnect(final long p0, @NonNull final TimeUnit p1);
    
    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();
    
    public abstract void connect();
    
    public void connect(final int n) {
        throw new UnsupportedOperationException();
    }
    
    public abstract void disconnect();
    
    public abstract void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
    
    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull final Api<?> p0);
    
    public Context getContext() {
        throw new UnsupportedOperationException();
    }
    
    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }
    
    public abstract boolean hasConnectedApi(@NonNull final Api<?> p0);
    
    public abstract boolean isConnected();
    
    public abstract boolean isConnecting();
    
    public abstract boolean isConnectionCallbacksRegistered(@NonNull final ConnectionCallbacks p0);
    
    public abstract boolean isConnectionFailedListenerRegistered(@NonNull final OnConnectionFailedListener p0);
    
    public abstract void reconnect();
    
    public abstract void registerConnectionCallbacks(@NonNull final ConnectionCallbacks p0);
    
    public abstract void registerConnectionFailedListener(@NonNull final OnConnectionFailedListener p0);
    
    public abstract void stopAutoManage(@NonNull final FragmentActivity p0);
    
    public abstract void unregisterConnectionCallbacks(@NonNull final ConnectionCallbacks p0);
    
    public abstract void unregisterConnectionFailedListener(@NonNull final OnConnectionFailedListener p0);
    
    @NonNull
    public <C extends Api.zzb> C zza(@NonNull final Api.zzc<C> zzc) {
        throw new UnsupportedOperationException();
    }
    
    public <A extends Api.zzb, R extends Result, T extends com.google.android.gms.common.api.internal.zza.zza<R, A>> T zza(@NonNull final T t) {
        throw new UnsupportedOperationException();
    }
    
    public void zza(final zzx zzx) {
        throw new UnsupportedOperationException();
    }
    
    public boolean zza(@NonNull final Api<?> api) {
        throw new UnsupportedOperationException();
    }
    
    public boolean zza(final zzu zzu) {
        throw new UnsupportedOperationException();
    }
    
    public <A extends Api.zzb, T extends com.google.android.gms.common.api.internal.zza.zza<? extends Result, A>> T zzb(@NonNull final T t) {
        throw new UnsupportedOperationException();
    }
    
    public void zzb(final zzx zzx) {
        throw new UnsupportedOperationException();
    }
    
    public void zzoW() {
        throw new UnsupportedOperationException();
    }
    
    public <L> zzq<L> zzr(@NonNull final L l) {
        throw new UnsupportedOperationException();
    }
    
    public static final class Builder
    {
        private final Context mContext;
        private Account zzTI;
        private String zzUW;
        private final Set<Scope> zzagh;
        private final Set<Scope> zzagi;
        private int zzagj;
        private View zzagk;
        private String zzagl;
        private final Map<Api<?>, zzf.zza> zzagm;
        private final Map<Api<?>, Api.ApiOptions> zzagn;
        private FragmentActivity zzago;
        private int zzagp;
        private OnConnectionFailedListener zzagq;
        private Looper zzagr;
        private zzc zzags;
        private Api.zza<? extends zzrn, zzro> zzagt;
        private final ArrayList<ConnectionCallbacks> zzagu;
        private final ArrayList<OnConnectionFailedListener> zzagv;
        
        public Builder(@NonNull final Context mContext) {
            this.zzagh = new HashSet<Scope>();
            this.zzagi = new HashSet<Scope>();
            this.zzagm = new ArrayMap<Api<?>, zzf.zza>();
            this.zzagn = new ArrayMap<Api<?>, Api.ApiOptions>();
            this.zzagp = -1;
            this.zzags = zzc.zzoK();
            this.zzagt = zzrl.zzUJ;
            this.zzagu = new ArrayList<ConnectionCallbacks>();
            this.zzagv = new ArrayList<OnConnectionFailedListener>();
            this.mContext = mContext;
            this.zzagr = mContext.getMainLooper();
            this.zzUW = mContext.getPackageName();
            this.zzagl = mContext.getClass().getName();
        }
        
        public Builder(@NonNull final Context context, @NonNull final ConnectionCallbacks connectionCallbacks, @NonNull final OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            com.google.android.gms.common.internal.zzx.zzb(connectionCallbacks, "Must provide a connected listener");
            this.zzagu.add(connectionCallbacks);
            com.google.android.gms.common.internal.zzx.zzb(onConnectionFailedListener, "Must provide a connection failed listener");
            this.zzagv.add(onConnectionFailedListener);
        }
        
        private static <C extends Api.zzb, O> C zza(final Api.zza<C, O> zza, final Object o, final Context context, final Looper looper, final zzf zzf, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
            return zza.zza(context, looper, zzf, (O)o, connectionCallbacks, onConnectionFailedListener);
        }
        
        private static <C extends Api.zzd, O> zzad zza(final Api.zze<C, O> zze, final Object o, final Context context, final Looper looper, final zzf zzf, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
            return new zzad(context, looper, zze.zzoU(), connectionCallbacks, onConnectionFailedListener, zzf, zze.zzq((O)o));
        }
        
        private <O extends Api.ApiOptions> void zza(final Api<O> api, final O o, final int n, final Scope... array) {
            boolean b = true;
            int i = 0;
            if (n != (b ? 1 : 0)) {
                if (n != 2) {
                    throw new IllegalArgumentException("Invalid resolution mode: '" + n + "', use a constant from GoogleApiClient.ResolutionMode");
                }
                b = false;
            }
            final HashSet<Scope> set = new HashSet<Scope>(api.zzoP().zzo(o));
            while (i < array.length) {
                set.add(array[i]);
                ++i;
            }
            this.zzagm.put(api, new zzf.zza(set, b));
        }
        
        private void zza(final zzw zzw, final GoogleApiClient googleApiClient) {
            zzw.zza(this.zzagp, googleApiClient, this.zzagq);
        }
        
        private void zze(final GoogleApiClient googleApiClient) {
            final zzw zza = zzw.zza(this.zzago);
            if (zza == null) {
                new Handler(this.mContext.getMainLooper()).post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Builder.this.zzago.isFinishing() || Builder.this.zzago.getSupportFragmentManager().isDestroyed()) {
                            return;
                        }
                        Builder.this.zza(zzw.zzb(Builder.this.zzago), googleApiClient);
                    }
                });
                return;
            }
            this.zza(zza, googleApiClient);
        }
        
        private GoogleApiClient zzoZ() {
            final zzf zzoY = this.zzoY();
            Api<?> api = null;
            final Map<Api<?>, zzf.zza> zzqu = zzoY.zzqu();
            final ArrayMap<Api<?>, Integer> arrayMap = new ArrayMap<Api<?>, Integer>();
            final ArrayMap<Api.zzc<?>, Api.zzb> arrayMap2 = new ArrayMap<Api.zzc<?>, Api.zzb>();
            final ArrayList<com.google.android.gms.common.api.internal.zzc> list = new ArrayList<com.google.android.gms.common.api.internal.zzc>();
            final Iterator<Api<?>> iterator = this.zzagn.keySet().iterator();
            Api api2 = null;
            while (iterator.hasNext()) {
                Api<?> api3 = iterator.next();
                final Api.ApiOptions value = this.zzagn.get(api3);
                final zzf.zza value2 = zzqu.get(api3);
                int n = 0;
                if (value2 != null) {
                    if (((zzf.zza)zzqu.get(api3)).zzalf) {
                        n = 1;
                    }
                    else {
                        n = 2;
                    }
                }
                arrayMap.put(api3, n);
                final com.google.android.gms.common.api.internal.zzc zzc = new com.google.android.gms.common.api.internal.zzc(api3, n);
                list.add(zzc);
                zzad zzad;
                Api<?> api5;
                if (api3.zzoS()) {
                    final Api.zze<?, ?> zzoQ = api3.zzoQ();
                    Api<?> api4;
                    if (zzoQ.getPriority() == 1) {
                        api4 = api3;
                    }
                    else {
                        api4 = (Api<?>)api2;
                    }
                    zzad = zza(zzoQ, value, this.mContext, this.zzagr, zzoY, zzc, zzc);
                    api5 = api4;
                }
                else {
                    final Api.zza<?, ?> zzoP = api3.zzoP();
                    Api<?> api6;
                    if (zzoP.getPriority() == 1) {
                        api6 = api3;
                    }
                    else {
                        api6 = (Api<?>)api2;
                    }
                    zzad = zza(zzoP, value, this.mContext, this.zzagr, zzoY, zzc, zzc);
                    api5 = api6;
                }
                arrayMap2.put(api3.zzoR(), zzad);
                if (((Api.zzb)zzad).zznb()) {
                    if (api != null) {
                        throw new IllegalStateException(api3.getName() + " cannot be used with " + api.getName());
                    }
                }
                else {
                    api3 = api;
                }
                api2 = api5;
                api = api3;
            }
            if (api != null) {
                if (api2 != null) {
                    throw new IllegalStateException(api.getName() + " cannot be used with " + api2.getName());
                }
                com.google.android.gms.common.internal.zzx.zza(this.zzTI == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.getName());
                com.google.android.gms.common.internal.zzx.zza(this.zzagh.equals(this.zzagi), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.getName());
            }
            return new zzj(this.mContext, new ReentrantLock(), this.zzagr, zzoY, this.zzags, this.zzagt, arrayMap, this.zzagu, this.zzagv, arrayMap2, this.zzagp, zzj.zza((Iterable<Api.zzb>)arrayMap2.values(), true), list);
        }
        
        public Builder addApi(@NonNull final Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            com.google.android.gms.common.internal.zzx.zzb(api, "Api must not be null");
            this.zzagn.put(api, null);
            final List<Scope> zzo = api.zzoP().zzo((Api.ApiOptions.NotRequiredOptions)null);
            this.zzagi.addAll(zzo);
            this.zzagh.addAll(zzo);
            return this;
        }
        
        public <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull final Api<O> api, @NonNull final O o) {
            com.google.android.gms.common.internal.zzx.zzb(api, "Api must not be null");
            com.google.android.gms.common.internal.zzx.zzb(o, "Null options are not permitted for this Api");
            this.zzagn.put(api, (Api.ApiOptions)o);
            final List<Scope> zzo = api.zzoP().zzo(o);
            this.zzagi.addAll(zzo);
            this.zzagh.addAll(zzo);
            return this;
        }
        
        public <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull final Api<O> api, @NonNull final O o, final Scope... array) {
            com.google.android.gms.common.internal.zzx.zzb(api, "Api must not be null");
            com.google.android.gms.common.internal.zzx.zzb(o, "Null options are not permitted for this Api");
            this.zzagn.put(api, (Api.ApiOptions)o);
            this.zza(api, o, 1, array);
            return this;
        }
        
        public Builder addApiIfAvailable(@NonNull final Api<? extends Api.ApiOptions.NotRequiredOptions> api, final Scope... array) {
            com.google.android.gms.common.internal.zzx.zzb(api, "Api must not be null");
            this.zzagn.put(api, null);
            this.zza((Api<Api.ApiOptions>)api, null, 1, array);
            return this;
        }
        
        public Builder addConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
            com.google.android.gms.common.internal.zzx.zzb(connectionCallbacks, "Listener must not be null");
            this.zzagu.add(connectionCallbacks);
            return this;
        }
        
        public Builder addOnConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
            com.google.android.gms.common.internal.zzx.zzb(onConnectionFailedListener, "Listener must not be null");
            this.zzagv.add(onConnectionFailedListener);
            return this;
        }
        
        public Builder addScope(@NonNull final Scope scope) {
            com.google.android.gms.common.internal.zzx.zzb(scope, "Scope must not be null");
            this.zzagh.add(scope);
            return this;
        }
        
        public GoogleApiClient build() {
            Label_0058: {
                if (this.zzagn.isEmpty()) {
                    break Label_0058;
                }
                boolean b = true;
                while (true) {
                    com.google.android.gms.common.internal.zzx.zzb(b, (Object)"must call addApi() to add at least one API");
                    final GoogleApiClient zzoZ = this.zzoZ();
                    synchronized (GoogleApiClient.zzagg) {
                        GoogleApiClient.zzagg.add(zzoZ);
                        // monitorexit(GoogleApiClient.zzoX())
                        if (this.zzagp >= 0) {
                            this.zze(zzoZ);
                        }
                        return zzoZ;
                        b = false;
                    }
                }
            }
        }
        
        public Builder enableAutoManage(@NonNull final FragmentActivity fragmentActivity, final int zzagp, @Nullable final OnConnectionFailedListener zzagq) {
            com.google.android.gms.common.internal.zzx.zzb(zzagp >= 0, (Object)"clientId must be non-negative");
            this.zzagp = zzagp;
            this.zzago = com.google.android.gms.common.internal.zzx.zzb(fragmentActivity, "Null activity is not permitted.");
            this.zzagq = zzagq;
            return this;
        }
        
        public Builder enableAutoManage(@NonNull final FragmentActivity fragmentActivity, @Nullable final OnConnectionFailedListener onConnectionFailedListener) {
            return this.enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }
        
        public Builder setAccountName(final String s) {
            Account zzTI;
            if (s == null) {
                zzTI = null;
            }
            else {
                zzTI = new Account(s, "com.google");
            }
            this.zzTI = zzTI;
            return this;
        }
        
        public Builder setGravityForPopups(final int zzagj) {
            this.zzagj = zzagj;
            return this;
        }
        
        public Builder setHandler(@NonNull final Handler handler) {
            com.google.android.gms.common.internal.zzx.zzb(handler, "Handler must not be null");
            this.zzagr = handler.getLooper();
            return this;
        }
        
        public Builder setViewForPopups(@NonNull final View zzagk) {
            com.google.android.gms.common.internal.zzx.zzb(zzagk, "View must not be null");
            this.zzagk = zzagk;
            return this;
        }
        
        public Builder useDefaultAccount() {
            return this.setAccountName("<<default account>>");
        }
        
        public zzf zzoY() {
            zzro zzbgV = zzro.zzbgV;
            if (this.zzagn.containsKey(zzrl.API)) {
                zzbgV = (zzro)this.zzagn.get(zzrl.API);
            }
            return new zzf(this.zzTI, this.zzagh, this.zzagm, this.zzagj, this.zzagk, this.zzUW, this.zzagl, zzbgV);
        }
    }
    
    public interface ConnectionCallbacks
    {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;
        
        void onConnected(@Nullable final Bundle p0);
        
        void onConnectionSuspended(final int p0);
    }
    
    public interface OnConnectionFailedListener
    {
        void onConnectionFailed(@NonNull final ConnectionResult p0);
    }
    
    public interface zza
    {
        void zza(@NonNull final ConnectionResult p0);
    }
}
