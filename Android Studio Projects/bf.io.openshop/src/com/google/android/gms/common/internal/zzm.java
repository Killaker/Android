package com.google.android.gms.common.internal;

import com.google.android.gms.common.stats.*;
import android.content.*;
import android.os.*;
import android.annotation.*;
import java.util.*;

final class zzm extends zzl implements Handler$Callback
{
    private final Handler mHandler;
    private final HashMap<zza, zzb> zzalZ;
    private final com.google.android.gms.common.stats.zzb zzama;
    private final long zzamb;
    private final Context zzsa;
    
    zzm(final Context context) {
        this.zzalZ = new HashMap<zza, zzb>();
        this.zzsa = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), (Handler$Callback)this);
        this.zzama = com.google.android.gms.common.stats.zzb.zzrP();
        this.zzamb = 5000L;
    }
    
    private boolean zza(final zza zza, final ServiceConnection serviceConnection, final String s) {
        while (true) {
            zzx.zzb(serviceConnection, "ServiceConnection must not be null");
            while (true) {
                zzb zzb;
                synchronized (this.zzalZ) {
                    zzb = this.zzalZ.get(zza);
                    if (zzb == null) {
                        zzb = new zzb(zza);
                        zzb.zza(serviceConnection, s);
                        zzb.zzcH(s);
                        this.zzalZ.put(zza, zzb);
                        return zzb.isBound();
                    }
                    this.mHandler.removeMessages(0, (Object)zzb);
                    if (zzb.zza(serviceConnection)) {
                        throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + zza);
                    }
                }
                zzb.zza(serviceConnection, s);
                switch (zzb.getState()) {
                    case 1: {
                        serviceConnection.onServiceConnected(zzb.getComponentName(), zzb.getBinder());
                        continue;
                    }
                    case 2: {
                        zzb.zzcH(s);
                        continue;
                    }
                    default: {
                        continue;
                    }
                }
                break;
            }
        }
    }
    
    private void zzb(final zza zza, final ServiceConnection serviceConnection, final String s) {
        zzx.zzb(serviceConnection, "ServiceConnection must not be null");
        final zzb zzb;
        synchronized (this.zzalZ) {
            zzb = this.zzalZ.get(zza);
            if (zzb == null) {
                throw new IllegalStateException("Nonexistent connection status for service config: " + zza);
            }
        }
        if (!zzb.zza(serviceConnection)) {
            throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + zza);
        }
        zzb.zzb(serviceConnection, s);
        if (zzb.zzqT()) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, (Object)zzb), this.zzamb);
        }
    }
    // monitorexit(hashMap)
    
    public boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                final zzb zzb = (zzb)message.obj;
                synchronized (this.zzalZ) {
                    if (zzb.zzqT()) {
                        if (zzb.isBound()) {
                            zzb.zzcI("GmsClientSupervisor");
                        }
                        this.zzalZ.remove(zzb.zzamg);
                    }
                    return true;
                }
                break;
            }
        }
    }
    
    @Override
    public boolean zza(final ComponentName componentName, final ServiceConnection serviceConnection, final String s) {
        return this.zza(new zza(componentName), serviceConnection, s);
    }
    
    @Override
    public boolean zza(final String s, final ServiceConnection serviceConnection, final String s2) {
        return this.zza(new zza(s), serviceConnection, s2);
    }
    
    @Override
    public void zzb(final ComponentName componentName, final ServiceConnection serviceConnection, final String s) {
        this.zzb(new zza(componentName), serviceConnection, s);
    }
    
    @Override
    public void zzb(final String s, final ServiceConnection serviceConnection, final String s2) {
        this.zzb(new zza(s), serviceConnection, s2);
    }
    
    private static final class zza
    {
        private final String zzSU;
        private final ComponentName zzamc;
        
        public zza(final ComponentName componentName) {
            this.zzSU = null;
            this.zzamc = zzx.zzz(componentName);
        }
        
        public zza(final String s) {
            this.zzSU = zzx.zzcM(s);
            this.zzamc = null;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this != o) {
                if (!(o instanceof zza)) {
                    return false;
                }
                final zza zza = (zza)o;
                if (!zzw.equal(this.zzSU, zza.zzSU) || !zzw.equal(this.zzamc, zza.zzamc)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return zzw.hashCode(this.zzSU, this.zzamc);
        }
        
        @Override
        public String toString() {
            if (this.zzSU == null) {
                return this.zzamc.flattenToString();
            }
            return this.zzSU;
        }
        
        public Intent zzqS() {
            if (this.zzSU != null) {
                return new Intent(this.zzSU).setPackage("com.google.android.gms");
            }
            return new Intent().setComponent(this.zzamc);
        }
    }
    
    private final class zzb
    {
        private int mState;
        private IBinder zzakD;
        private ComponentName zzamc;
        private final zza zzamd;
        private final Set<ServiceConnection> zzame;
        private boolean zzamf;
        private final zzm.zza zzamg;
        
        public zzb(final zzm.zza zzamg) {
            this.zzamg = zzamg;
            this.zzamd = new zza();
            this.zzame = new HashSet<ServiceConnection>();
            this.mState = 2;
        }
        
        public IBinder getBinder() {
            return this.zzakD;
        }
        
        public ComponentName getComponentName() {
            return this.zzamc;
        }
        
        public int getState() {
            return this.mState;
        }
        
        public boolean isBound() {
            return this.zzamf;
        }
        
        public void zza(final ServiceConnection serviceConnection, final String s) {
            zzm.this.zzama.zza(zzm.this.zzsa, serviceConnection, s, this.zzamg.zzqS());
            this.zzame.add(serviceConnection);
        }
        
        public boolean zza(final ServiceConnection serviceConnection) {
            return this.zzame.contains(serviceConnection);
        }
        
        public void zzb(final ServiceConnection serviceConnection, final String s) {
            zzm.this.zzama.zzb(zzm.this.zzsa, serviceConnection);
            this.zzame.remove(serviceConnection);
        }
        
        @TargetApi(14)
        public void zzcH(final String s) {
            this.mState = 3;
            if (this.zzamf = zzm.this.zzama.zza(zzm.this.zzsa, s, this.zzamg.zzqS(), (ServiceConnection)this.zzamd, 129)) {
                return;
            }
            this.mState = 2;
            try {
                zzm.this.zzama.zza(zzm.this.zzsa, (ServiceConnection)this.zzamd);
            }
            catch (IllegalArgumentException ex) {}
        }
        
        public void zzcI(final String s) {
            zzm.this.zzama.zza(zzm.this.zzsa, (ServiceConnection)this.zzamd);
            this.zzamf = false;
            this.mState = 2;
        }
        
        public boolean zzqT() {
            return this.zzame.isEmpty();
        }
        
        public class zza implements ServiceConnection
        {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                synchronized (zzm.this.zzalZ) {
                    zzb.this.zzakD = binder;
                    zzb.this.zzamc = componentName;
                    final Iterator<ServiceConnection> iterator = zzb.this.zzame.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onServiceConnected(componentName, binder);
                    }
                }
                zzb.this.mState = 1;
            }
            // monitorexit(hashMap)
            
            public void onServiceDisconnected(final ComponentName componentName) {
                synchronized (zzm.this.zzalZ) {
                    zzb.this.zzakD = null;
                    zzb.this.zzamc = componentName;
                    final Iterator<ServiceConnection> iterator = zzb.this.zzame.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onServiceDisconnected(componentName);
                    }
                }
                zzb.this.mState = 2;
            }
            // monitorexit(hashMap)
        }
    }
}
