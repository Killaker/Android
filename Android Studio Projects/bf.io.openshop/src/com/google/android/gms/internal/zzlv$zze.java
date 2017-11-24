package com.google.android.gms.internal;

import java.util.concurrent.*;

private static final class zze
{
    private int mSize;
    
    private zze() {
        this.mSize = 0;
    }
    
    public boolean zza(final long n, final TimeUnit timeUnit) throws InterruptedException {
        final long currentTimeMillis = System.currentTimeMillis();
        long convert = TimeUnit.MILLISECONDS.convert(n, timeUnit);
        // monitorenter(this)
        while (true) {
            try {
                if (this.mSize == 0) {
                    return true;
                }
                if (convert <= 0L) {
                    return false;
                }
            }
            finally {
            }
            // monitorexit(this)
            this.wait(convert);
            convert -= System.currentTimeMillis() - currentTimeMillis;
        }
    }
    
    public void zzoH() {
        synchronized (this) {
            ++this.mSize;
        }
    }
    
    public void zzoI() {
        synchronized (this) {
            if (this.mSize == 0) {
                throw new RuntimeException("too many decrements");
            }
        }
        --this.mSize;
        if (this.mSize == 0) {
            this.notifyAll();
        }
    }
    // monitorexit(this)
}
