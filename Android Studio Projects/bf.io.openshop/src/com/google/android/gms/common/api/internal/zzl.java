package com.google.android.gms.common.api.internal;

import android.content.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;
import java.util.concurrent.locks.*;
import com.google.android.gms.common.*;
import java.util.*;
import android.app.*;
import java.util.concurrent.*;
import java.io.*;
import android.support.annotation.*;
import com.google.android.gms.common.api.*;
import android.os.*;
import android.util.*;

public class zzl implements zzp
{
    private final Context mContext;
    private final Lock zzXG;
    final zzj zzagW;
    private final zzc zzags;
    final Api.zza<? extends zzrn, zzro> zzagt;
    final Map<Api<?>, Integer> zzahA;
    final Map<Api.zzc<?>, Api.zzb> zzahT;
    final zzf zzahz;
    private final Condition zzaim;
    private final zzb zzain;
    final Map<Api.zzc<?>, ConnectionResult> zzaio;
    private volatile zzk zzaip;
    private ConnectionResult zzaiq;
    int zzair;
    final zzp.zza zzais;
    
    public zzl(final Context mContext, final zzj zzagW, final Lock zzXG, final Looper looper, final zzc zzags, final Map<Api.zzc<?>, Api.zzb> zzahT, final zzf zzahz, final Map<Api<?>, Integer> zzahA, final Api.zza<? extends zzrn, zzro> zzagt, final ArrayList<com.google.android.gms.common.api.internal.zzc> list, final zzp.zza zzais) {
        this.zzaio = new HashMap<Api.zzc<?>, ConnectionResult>();
        this.zzaiq = null;
        this.mContext = mContext;
        this.zzXG = zzXG;
        this.zzags = zzags;
        this.zzahT = zzahT;
        this.zzahz = zzahz;
        this.zzahA = zzahA;
        this.zzagt = zzagt;
        this.zzagW = zzagW;
        this.zzais = zzais;
        final Iterator<com.google.android.gms.common.api.internal.zzc> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next().zza(this);
        }
        this.zzain = new zzb(looper);
        this.zzaim = zzXG.newCondition();
        this.zzaip = new zzi(this);
    }
    
    @Override
    public ConnectionResult blockingConnect() {
        this.connect();
        while (this.isConnecting()) {
            try {
                this.zzaim.await();
                continue;
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
            break;
        }
        if (this.isConnected()) {
            return ConnectionResult.zzafB;
        }
        if (this.zzaiq != null) {
            return this.zzaiq;
        }
        return new ConnectionResult(13, null);
    }
    
    @Override
    public ConnectionResult blockingConnect(final long n, final TimeUnit timeUnit) {
        this.connect();
        long n2 = timeUnit.toNanos(n);
    Label_0041_Outer:
        while (this.isConnecting()) {
            while (true) {
                if (n2 <= 0L) {
                    try {
                        this.disconnect();
                        return new ConnectionResult(14, null);
                        n2 = this.zzaim.awaitNanos(n2);
                        continue Label_0041_Outer;
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        return new ConnectionResult(15, null);
                    }
                    break;
                }
                continue;
            }
        }
        if (this.isConnected()) {
            return ConnectionResult.zzafB;
        }
        if (this.zzaiq != null) {
            return this.zzaiq;
        }
        return new ConnectionResult(13, null);
    }
    
    @Override
    public void connect() {
        this.zzaip.connect();
    }
    
    @Override
    public boolean disconnect() {
        final boolean disconnect = this.zzaip.disconnect();
        if (disconnect) {
            this.zzaio.clear();
        }
        return disconnect;
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        final String string = s + "  ";
        for (final Api<?> api : this.zzahA.keySet()) {
            printWriter.append(s).append(api.getName()).println(":");
            this.zzahT.get(api.zzoR()).dump(string, fileDescriptor, printWriter, array);
        }
    }
    
    @Nullable
    @Override
    public ConnectionResult getConnectionResult(@NonNull final Api<?> api) {
        final Api.zzc<?> zzoR = (Api.zzc<?>)api.zzoR();
        if (this.zzahT.containsKey(zzoR)) {
            if (this.zzahT.get(zzoR).isConnected()) {
                return ConnectionResult.zzafB;
            }
            if (this.zzaio.containsKey(zzoR)) {
                return this.zzaio.get(zzoR);
            }
        }
        return null;
    }
    
    @Override
    public boolean isConnected() {
        return this.zzaip instanceof zzg;
    }
    
    @Override
    public boolean isConnecting() {
        return this.zzaip instanceof zzh;
    }
    
    public void onConnected(@Nullable final Bundle bundle) {
        this.zzXG.lock();
        try {
            this.zzaip.onConnected(bundle);
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    public void onConnectionSuspended(final int n) {
        this.zzXG.lock();
        try {
            this.zzaip.onConnectionSuspended(n);
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends com.google.android.gms.common.api.internal.zza.zza<R, A>> T zza(@NonNull final T t) {
        return this.zzaip.zza(t);
    }
    
    public void zza(@NonNull final ConnectionResult connectionResult, @NonNull final Api<?> api, final int n) {
        this.zzXG.lock();
        try {
            this.zzaip.zza(connectionResult, api, n);
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    void zza(final zza zza) {
        this.zzain.sendMessage(this.zzain.obtainMessage(1, (Object)zza));
    }
    
    void zza(final RuntimeException ex) {
        this.zzain.sendMessage(this.zzain.obtainMessage(2, (Object)ex));
    }
    
    @Override
    public boolean zza(final zzu zzu) {
        return false;
    }
    
    @Override
    public <A extends Api.zzb, T extends com.google.android.gms.common.api.internal.zza.zza<? extends Result, A>> T zzb(@NonNull final T t) {
        return this.zzaip.zzb(t);
    }
    
    void zzh(final ConnectionResult zzaiq) {
        this.zzXG.lock();
        try {
            this.zzaiq = zzaiq;
            (this.zzaip = new zzi(this)).begin();
            this.zzaim.signalAll();
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    @Override
    public void zzoW() {
    }
    
    void zzpK() {
        this.zzXG.lock();
        try {
            (this.zzaip = new zzh(this, this.zzahz, this.zzahA, this.zzags, this.zzagt, this.zzXG, this.mContext)).begin();
            this.zzaim.signalAll();
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    void zzpL() {
        this.zzXG.lock();
        try {
            this.zzagW.zzpF();
            (this.zzaip = new zzg(this)).begin();
            this.zzaim.signalAll();
        }
        finally {
            this.zzXG.unlock();
        }
    }
    
    void zzpM() {
        final Iterator<Api.zzb> iterator = this.zzahT.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().disconnect();
        }
    }
    
    @Override
    public void zzpj() {
        if (this.isConnected()) {
            ((zzg)this.zzaip).zzps();
        }
    }
    
    abstract static class zza
    {
        private final zzk zzait;
        
        protected zza(final zzk zzait) {
            this.zzait = zzait;
        }
        
        public final void zzd(final zzl zzl) {
            zzl.zzXG.lock();
            try {
                if (zzl.zzaip != this.zzait) {
                    return;
                }
                this.zzpt();
            }
            finally {
                zzl.zzXG.unlock();
            }
        }
        
        protected abstract void zzpt();
    }
    
    final class zzb extends Handler
    {
        zzb(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.w("GACStateManager", "Unknown message id: " + message.what);
                }
                case 1: {
                    ((zza)message.obj).zzd(zzl.this);
                }
                case 2: {
                    throw (RuntimeException)message.obj;
                }
            }
        }
    }
}
