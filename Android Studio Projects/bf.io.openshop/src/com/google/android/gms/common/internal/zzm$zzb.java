package com.google.android.gms.common.internal;

import android.os.*;
import android.content.*;
import android.annotation.*;
import java.util.*;

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
        zzm.zzc(zzm.this).zza(zzm.zzb(zzm.this), serviceConnection, s, this.zzamg.zzqS());
        this.zzame.add(serviceConnection);
    }
    
    public boolean zza(final ServiceConnection serviceConnection) {
        return this.zzame.contains(serviceConnection);
    }
    
    public void zzb(final ServiceConnection serviceConnection, final String s) {
        zzm.zzc(zzm.this).zzb(zzm.zzb(zzm.this), serviceConnection);
        this.zzame.remove(serviceConnection);
    }
    
    @TargetApi(14)
    public void zzcH(final String s) {
        this.mState = 3;
        if (this.zzamf = zzm.zzc(zzm.this).zza(zzm.zzb(zzm.this), s, this.zzamg.zzqS(), (ServiceConnection)this.zzamd, 129)) {
            return;
        }
        this.mState = 2;
        try {
            zzm.zzc(zzm.this).zza(zzm.zzb(zzm.this), (ServiceConnection)this.zzamd);
        }
        catch (IllegalArgumentException ex) {}
    }
    
    public void zzcI(final String s) {
        zzm.zzc(zzm.this).zza(zzm.zzb(zzm.this), (ServiceConnection)this.zzamd);
        this.zzamf = false;
        this.mState = 2;
    }
    
    public boolean zzqT() {
        return this.zzame.isEmpty();
    }
    
    public class zza implements ServiceConnection
    {
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            synchronized (zzm.zza(zzm.this)) {
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
            synchronized (zzm.zza(zzm.this)) {
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
