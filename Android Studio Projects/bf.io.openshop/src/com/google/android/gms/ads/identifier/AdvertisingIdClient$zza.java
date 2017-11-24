package com.google.android.gms.ads.identifier;

import java.lang.ref.*;
import java.util.concurrent.*;

static class zza extends Thread
{
    private WeakReference<AdvertisingIdClient> zzoY;
    private long zzoZ;
    CountDownLatch zzpa;
    boolean zzpb;
    
    public zza(final AdvertisingIdClient advertisingIdClient, final long zzoZ) {
        this.zzoY = new WeakReference<AdvertisingIdClient>(advertisingIdClient);
        this.zzoZ = zzoZ;
        this.zzpa = new CountDownLatch(1);
        this.zzpb = false;
        this.start();
    }
    
    private void disconnect() {
        final AdvertisingIdClient advertisingIdClient = this.zzoY.get();
        if (advertisingIdClient != null) {
            advertisingIdClient.finish();
            this.zzpb = true;
        }
    }
    
    public void cancel() {
        this.zzpa.countDown();
    }
    
    @Override
    public void run() {
        try {
            if (!this.zzpa.await(this.zzoZ, TimeUnit.MILLISECONDS)) {
                this.disconnect();
            }
        }
        catch (InterruptedException ex) {
            this.disconnect();
        }
    }
    
    public boolean zzaK() {
        return this.zzpb;
    }
}
