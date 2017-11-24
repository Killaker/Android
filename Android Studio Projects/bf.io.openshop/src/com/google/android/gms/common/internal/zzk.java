package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.*;
import java.util.concurrent.atomic.*;
import android.util.*;
import java.util.*;
import android.os.*;
import com.google.android.gms.common.*;

public final class zzk implements Handler$Callback
{
    private final Handler mHandler;
    private final zza zzalQ;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzalR;
    final ArrayList<GoogleApiClient.ConnectionCallbacks> zzalS;
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzalT;
    private volatile boolean zzalU;
    private final AtomicInteger zzalV;
    private boolean zzalW;
    private final Object zzpV;
    
    public zzk(final Looper looper, final zza zzalQ) {
        this.zzalR = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
        this.zzalS = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
        this.zzalT = new ArrayList<GoogleApiClient.OnConnectionFailedListener>();
        this.zzalU = false;
        this.zzalV = new AtomicInteger(0);
        this.zzalW = false;
        this.zzpV = new Object();
        this.zzalQ = zzalQ;
        this.mHandler = new Handler(looper, (Handler$Callback)this);
    }
    
    public boolean handleMessage(final Message message) {
        if (message.what == 1) {
            final GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks)message.obj;
            synchronized (this.zzpV) {
                if (this.zzalU && this.zzalQ.isConnected() && this.zzalR.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.zzalQ.zzoi());
                }
                return true;
            }
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + message.what, (Throwable)new Exception());
        return false;
    }
    
    public boolean isConnectionCallbacksRegistered(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzx.zzz(connectionCallbacks);
        synchronized (this.zzpV) {
            return this.zzalR.contains(connectionCallbacks);
        }
    }
    
    public boolean isConnectionFailedListenerRegistered(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzx.zzz(onConnectionFailedListener);
        synchronized (this.zzpV) {
            return this.zzalT.contains(onConnectionFailedListener);
        }
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzx.zzz(connectionCallbacks);
        synchronized (this.zzpV) {
            if (this.zzalR.contains(connectionCallbacks)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + connectionCallbacks + " is already registered");
            }
            else {
                this.zzalR.add(connectionCallbacks);
            }
            // monitorexit(this.zzpV)
            if (this.zzalQ.isConnected()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)connectionCallbacks));
            }
        }
    }
    
    public void registerConnectionFailedListener(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzx.zzz(onConnectionFailedListener);
        synchronized (this.zzpV) {
            if (this.zzalT.contains(onConnectionFailedListener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + onConnectionFailedListener + " is already registered");
            }
            else {
                this.zzalT.add(onConnectionFailedListener);
            }
        }
    }
    
    public void unregisterConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzx.zzz(connectionCallbacks);
        synchronized (this.zzpV) {
            if (!this.zzalR.remove(connectionCallbacks)) {
                Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + connectionCallbacks + " not found");
            }
            else if (this.zzalW) {
                this.zzalS.add(connectionCallbacks);
            }
        }
    }
    
    public void unregisterConnectionFailedListener(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzx.zzz(onConnectionFailedListener);
        synchronized (this.zzpV) {
            if (!this.zzalT.remove(onConnectionFailedListener)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + onConnectionFailedListener + " not found");
            }
        }
    }
    
    public void zzbT(final int n) {
        final Looper myLooper = Looper.myLooper();
        final Looper looper = this.mHandler.getLooper();
        boolean b = false;
        if (myLooper == looper) {
            b = true;
        }
        zzx.zza(b, (Object)"onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzpV) {
            this.zzalW = true;
            final ArrayList<GoogleApiClient.ConnectionCallbacks> list = new ArrayList<GoogleApiClient.ConnectionCallbacks>(this.zzalR);
            final int value = this.zzalV.get();
            for (final GoogleApiClient.ConnectionCallbacks connectionCallbacks : list) {
                if (!this.zzalU || this.zzalV.get() != value) {
                    break;
                }
                if (!this.zzalR.contains(connectionCallbacks)) {
                    continue;
                }
                connectionCallbacks.onConnectionSuspended(n);
            }
            this.zzalS.clear();
            this.zzalW = false;
        }
    }
    
    public void zzk(final Bundle bundle) {
        boolean b = true;
        while (true) {
            Label_0203: {
                if (Looper.myLooper() != this.mHandler.getLooper()) {
                    break Label_0203;
                }
                final boolean b2 = b;
                boolean b3;
                ArrayList<GoogleApiClient.ConnectionCallbacks> list;
                int value;
                Label_0042_Outer:Label_0070_Outer:
                while (true) {
                    zzx.zza(b2, (Object)"onConnectionSuccess must only be called on the Handler thread");
                    while (true) {
                    Label_0214:
                        while (true) {
                            Label_0208: {
                                synchronized (this.zzpV) {
                                    if (this.zzalW) {
                                        break Label_0208;
                                    }
                                    b3 = b;
                                    zzx.zzab(b3);
                                    this.mHandler.removeMessages(1);
                                    this.zzalW = true;
                                    if (this.zzalS.size() == 0) {
                                        zzx.zzab(b);
                                        list = new ArrayList<GoogleApiClient.ConnectionCallbacks>(this.zzalR);
                                        value = this.zzalV.get();
                                        for (final GoogleApiClient.ConnectionCallbacks connectionCallbacks : list) {
                                            if (!this.zzalU || !this.zzalQ.isConnected() || this.zzalV.get() != value) {
                                                break;
                                            }
                                            if (this.zzalS.contains(connectionCallbacks)) {
                                                continue Label_0042_Outer;
                                            }
                                            connectionCallbacks.onConnected(bundle);
                                        }
                                        this.zzalS.clear();
                                        this.zzalW = false;
                                        return;
                                    }
                                    break Label_0214;
                                }
                                break;
                            }
                            b3 = false;
                            continue Label_0070_Outer;
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
    
    public void zzk(final ConnectionResult connectionResult) {
        zzx.zza(Looper.myLooper() == this.mHandler.getLooper(), (Object)"onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzpV) {
            final ArrayList<GoogleApiClient.OnConnectionFailedListener> list = new ArrayList<GoogleApiClient.OnConnectionFailedListener>(this.zzalT);
            final int value = this.zzalV.get();
            for (final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener : list) {
                if (!this.zzalU || this.zzalV.get() != value) {
                    return;
                }
                if (!this.zzalT.contains(onConnectionFailedListener)) {
                    continue;
                }
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }
    // monitorexit(o)
    
    public void zzqQ() {
        this.zzalU = false;
        this.zzalV.incrementAndGet();
    }
    
    public void zzqR() {
        this.zzalU = true;
    }
    
    public interface zza
    {
        boolean isConnected();
        
        Bundle zzoi();
    }
}
