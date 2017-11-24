package com.google.android.gms.common.api.internal;

import android.content.*;
import java.util.concurrent.locks.*;
import com.google.android.gms.common.*;
import com.google.android.gms.internal.*;
import android.support.v4.util.*;
import android.support.annotation.*;
import java.util.*;
import android.util.*;
import com.google.android.gms.common.internal.*;
import android.app.*;
import java.util.concurrent.*;
import java.io.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public class zzd implements zzp
{
    private final Context mContext;
    private final Lock zzXG;
    private final zzj zzagW;
    private final zzl zzagX;
    private final zzl zzagY;
    private final Map<Api.zzc<?>, zzl> zzagZ;
    private final Looper zzagr;
    private final Set<zzu> zzaha;
    private final Api.zzb zzahb;
    private Bundle zzahc;
    private ConnectionResult zzahd;
    private ConnectionResult zzahe;
    private boolean zzahf;
    private int zzahg;
    
    public zzd(final Context mContext, final zzj zzagW, final Lock zzXG, final Looper zzagr, final zzc zzc, final Map<Api.zzc<?>, Api.zzb> map, final zzf zzf, final Map<Api<?>, Integer> map2, final Api.zza<? extends zzrn, zzro> zza, final ArrayList<com.google.android.gms.common.api.internal.zzc> list) {
        this.zzagZ = new ArrayMap<Api.zzc<?>, zzl>();
        this.zzaha = Collections.newSetFromMap(new WeakHashMap<zzu, Boolean>());
        this.zzahd = null;
        this.zzahe = null;
        this.zzahf = false;
        this.zzahg = 0;
        this.mContext = mContext;
        this.zzagW = zzagW;
        this.zzXG = zzXG;
        this.zzagr = zzagr;
        Api.zzb zzahb = null;
        final ArrayMap<Api.zzc<?>, Api.zzb> arrayMap = new ArrayMap<Api.zzc<?>, Api.zzb>();
        final ArrayMap<Api.zzc<?>, Api.zzb> arrayMap2 = new ArrayMap<Api.zzc<?>, Api.zzb>();
        for (final Api.zzc zzc2 : map.keySet()) {
            final Api.zzb zzb = map.get(zzc2);
            if (zzb.zznb()) {
                zzahb = zzb;
            }
            if (zzb.zzmE()) {
                arrayMap.put(zzc2, zzb);
            }
            else {
                arrayMap2.put(zzc2, zzb);
            }
        }
        this.zzahb = zzahb;
        if (arrayMap.isEmpty()) {
            throw new IllegalStateException("CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        }
        final ArrayMap<Api<?>, Integer> arrayMap3 = new ArrayMap<Api<?>, Integer>();
        final ArrayMap<Api<?>, Integer> arrayMap4 = new ArrayMap<Api<?>, Integer>();
        for (final Api<?> api : map2.keySet()) {
            final Api.zzc<?> zzoR = api.zzoR();
            if (arrayMap.containsKey(zzoR)) {
                arrayMap3.put(api, map2.get(api));
            }
            else {
                if (!arrayMap2.containsKey(zzoR)) {
                    throw new IllegalStateException("Each API in the apiTypeMap must have a corresponding client in the clients map.");
                }
                arrayMap4.put(api, map2.get(api));
            }
        }
        final ArrayList<com.google.android.gms.common.api.internal.zzc> list2 = new ArrayList<com.google.android.gms.common.api.internal.zzc>();
        final ArrayList<com.google.android.gms.common.api.internal.zzc> list3 = new ArrayList<com.google.android.gms.common.api.internal.zzc>();
        for (final com.google.android.gms.common.api.internal.zzc zzc3 : list) {
            if (arrayMap3.containsKey(zzc3.zzagT)) {
                list2.add(zzc3);
            }
            else {
                if (!arrayMap4.containsKey(zzc3.zzagT)) {
                    throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the apiTypeMap");
                }
                list3.add(zzc3);
            }
        }
        this.zzagX = new zzl(mContext, this.zzagW, zzXG, zzagr, zzc, arrayMap2, null, arrayMap4, null, list3, new zza() {
            @Override
            public void zzc(final int n, final boolean b) {
                zzd.this.zzXG.lock();
                try {
                    if (zzd.this.zzahf || zzd.this.zzahe == null || !zzd.this.zzahe.isSuccess()) {
                        zzd.this.zzahf = false;
                        zzd.this.zzb(n, b);
                        return;
                    }
                    zzd.this.zzahf = true;
                    zzd.this.zzagY.onConnectionSuspended(n);
                }
                finally {
                    zzd.this.zzXG.unlock();
                }
            }
            
            @Override
            public void zzd(@NonNull final ConnectionResult connectionResult) {
                zzd.this.zzXG.lock();
                try {
                    zzd.this.zzahd = connectionResult;
                    zzd.this.zzpm();
                }
                finally {
                    zzd.this.zzXG.unlock();
                }
            }
            
            @Override
            public void zzi(@Nullable final Bundle bundle) {
                zzd.this.zzXG.lock();
                try {
                    zzd.this.zzh(bundle);
                    zzd.this.zzahd = ConnectionResult.zzafB;
                    zzd.this.zzpm();
                }
                finally {
                    zzd.this.zzXG.unlock();
                }
            }
        });
        this.zzagY = new zzl(mContext, this.zzagW, zzXG, zzagr, zzc, arrayMap, zzf, arrayMap3, zza, list2, new zza() {
            @Override
            public void zzc(final int n, final boolean b) {
                zzd.this.zzXG.lock();
                try {
                    if (zzd.this.zzahf) {
                        zzd.this.zzahf = false;
                        zzd.this.zzb(n, b);
                        return;
                    }
                    zzd.this.zzahf = true;
                    zzd.this.zzagX.onConnectionSuspended(n);
                }
                finally {
                    zzd.this.zzXG.unlock();
                }
            }
            
            @Override
            public void zzd(@NonNull final ConnectionResult connectionResult) {
                zzd.this.zzXG.lock();
                try {
                    zzd.this.zzahe = connectionResult;
                    zzd.this.zzpm();
                }
                finally {
                    zzd.this.zzXG.unlock();
                }
            }
            
            @Override
            public void zzi(@Nullable final Bundle bundle) {
                zzd.this.zzXG.lock();
                try {
                    zzd.this.zzahe = ConnectionResult.zzafB;
                    zzd.this.zzpm();
                }
                finally {
                    zzd.this.zzXG.unlock();
                }
            }
        });
        final Iterator<Api.zzc<?>> iterator4 = arrayMap2.keySet().iterator();
        while (iterator4.hasNext()) {
            this.zzagZ.put((Api.zzc)iterator4.next(), this.zzagX);
        }
        final Iterator<Api.zzc<?>> iterator5 = arrayMap.keySet().iterator();
        while (iterator5.hasNext()) {
            this.zzagZ.put((Api.zzc)iterator5.next(), this.zzagY);
        }
    }
    
    private void zzb(final int n, final boolean b) {
        this.zzagW.zzc(n, b);
        this.zzahe = null;
        this.zzahd = null;
    }
    
    private void zzb(final ConnectionResult connectionResult) {
        switch (this.zzahg) {
            default: {
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", (Throwable)new Exception());
                break;
            }
            case 2: {
                this.zzagW.zzd(connectionResult);
            }
            case 1: {
                this.zzpo();
                break;
            }
        }
        this.zzahg = 0;
    }
    
    private static boolean zzc(final ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }
    
    private boolean zzc(final com.google.android.gms.common.api.internal.zza.zza<? extends Result, ? extends Api.zzb> zza) {
        final Api.zzc<? extends Api.zzb> zzoR = zza.zzoR();
        zzx.zzb(this.zzagZ.containsKey(zzoR), (Object)"GoogleApiClient is not configured to use the API required for this call.");
        return this.zzagZ.get(zzoR).equals(this.zzagY);
    }
    
    private void zzh(final Bundle zzahc) {
        if (this.zzahc == null) {
            this.zzahc = zzahc;
        }
        else if (zzahc != null) {
            this.zzahc.putAll(zzahc);
        }
    }
    
    private void zzpl() {
        this.zzahe = null;
        this.zzahd = null;
        this.zzagX.connect();
        this.zzagY.connect();
    }
    
    private void zzpm() {
        if (zzc(this.zzahd)) {
            if (zzc(this.zzahe) || this.zzpp()) {
                this.zzpn();
            }
            else if (this.zzahe != null) {
                if (this.zzahg == 1) {
                    this.zzpo();
                    return;
                }
                this.zzb(this.zzahe);
                this.zzagX.disconnect();
            }
        }
        else {
            if (this.zzahd != null && zzc(this.zzahe)) {
                this.zzagY.disconnect();
                this.zzb(this.zzahd);
                return;
            }
            if (this.zzahd != null && this.zzahe != null) {
                ConnectionResult connectionResult = this.zzahd;
                if (this.zzagY.zzair < this.zzagX.zzair) {
                    connectionResult = this.zzahe;
                }
                this.zzb(connectionResult);
            }
        }
    }
    
    private void zzpn() {
        switch (this.zzahg) {
            default: {
                Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", (Throwable)new Exception());
                break;
            }
            case 2: {
                this.zzagW.zzi(this.zzahc);
            }
            case 1: {
                this.zzpo();
                break;
            }
        }
        this.zzahg = 0;
    }
    
    private void zzpo() {
        final Iterator<zzu> iterator = this.zzaha.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzna();
        }
        this.zzaha.clear();
    }
    
    private boolean zzpp() {
        return this.zzahe != null && this.zzahe.getErrorCode() == 4;
    }
    
    @Nullable
    private PendingIntent zzpq() {
        if (this.zzahb == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, this.zzagW.getSessionId(), this.zzahb.zznc(), 134217728);
    }
    
    @Override
    public ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ConnectionResult blockingConnect(final long n, @NonNull final TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void connect() {
        this.zzahg = 2;
        this.zzahf = false;
        this.zzpl();
    }
    
    @Override
    public boolean disconnect() {
        this.zzahe = null;
        this.zzahd = null;
        this.zzahg = 0;
        final boolean disconnect = this.zzagX.disconnect();
        final boolean disconnect2 = this.zzagY.disconnect();
        this.zzpo();
        boolean b = false;
        if (disconnect) {
            b = false;
            if (disconnect2) {
                b = true;
            }
        }
        return b;
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("authClient").println(":");
        this.zzagY.dump(s + "  ", fileDescriptor, printWriter, array);
        printWriter.append(s).append("anonClient").println(":");
        this.zzagX.dump(s + "  ", fileDescriptor, printWriter, array);
    }
    
    @Nullable
    @Override
    public ConnectionResult getConnectionResult(@NonNull final Api<?> api) {
        if (!this.zzagZ.get(api.zzoR()).equals(this.zzagY)) {
            return this.zzagX.getConnectionResult(api);
        }
        if (this.zzpp()) {
            return new ConnectionResult(4, this.zzpq());
        }
        return this.zzagY.getConnectionResult(api);
    }
    
    @Override
    public boolean isConnected() {
        boolean b = true;
        this.zzXG.lock();
        try {
            if (!this.zzagX.isConnected() || ((this.zzpk() || this.zzpp()) && this.zzahg != (b ? 1 : 0))) {
                b = false;
            }
            return b;
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    @Override
    public boolean isConnecting() {
        this.zzXG.lock();
        try {
            return this.zzahg == 2;
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends com.google.android.gms.common.api.internal.zza.zza<R, A>> T zza(@NonNull final T t) {
        if (!this.zzc(t)) {
            return this.zzagX.zza(t);
        }
        if (this.zzpp()) {
            ((com.google.android.gms.common.api.internal.zza.zza)t).zzw(new Status(4, null, this.zzpq()));
            return t;
        }
        return this.zzagY.zza(t);
    }
    
    @Override
    public boolean zza(final zzu zzu) {
        this.zzXG.lock();
        try {
            if ((this.isConnecting() || this.isConnected()) && !this.zzpk()) {
                this.zzaha.add(zzu);
                if (this.zzahg == 0) {
                    this.zzahg = 1;
                }
                this.zzahe = null;
                this.zzagY.connect();
                return true;
            }
            return false;
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    @Override
    public <A extends Api.zzb, T extends com.google.android.gms.common.api.internal.zza.zza<? extends Result, A>> T zzb(@NonNull final T t) {
        if (!this.zzc(t)) {
            return this.zzagX.zzb(t);
        }
        if (this.zzpp()) {
            ((com.google.android.gms.common.api.internal.zza.zza)t).zzw(new Status(4, null, this.zzpq()));
            return t;
        }
        return this.zzagY.zzb(t);
    }
    
    @Override
    public void zzoW() {
        this.zzXG.lock();
        try {
            final boolean connecting = this.isConnecting();
            this.zzagY.disconnect();
            this.zzahe = new ConnectionResult(4);
            if (connecting) {
                new Handler(this.zzagr).post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        zzd.this.zzXG.lock();
                        try {
                            zzd.this.zzpm();
                        }
                        finally {
                            zzd.this.zzXG.unlock();
                        }
                    }
                });
            }
            else {
                this.zzpo();
            }
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    @Override
    public void zzpj() {
        this.zzagX.zzpj();
        this.zzagY.zzpj();
    }
    
    public boolean zzpk() {
        return this.zzagY.isConnected();
    }
}
