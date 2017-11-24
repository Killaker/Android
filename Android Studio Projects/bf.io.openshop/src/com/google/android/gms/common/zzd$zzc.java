package com.google.android.gms.common;

import java.lang.ref.*;

abstract static class zzc extends zza
{
    private static final WeakReference<byte[]> zzafJ;
    private WeakReference<byte[]> zzafI;
    
    static {
        zzafJ = new WeakReference<byte[]>(null);
    }
    
    zzc(final byte[] array) {
        super(array);
        this.zzafI = zzc.zzafJ;
    }
    
    @Override
    byte[] getBytes() {
        synchronized (this) {
            byte[] zzoL = this.zzafI.get();
            if (zzoL == null) {
                zzoL = this.zzoL();
                this.zzafI = new WeakReference<byte[]>(zzoL);
            }
            return zzoL;
        }
    }
    
    protected abstract byte[] zzoL();
}
