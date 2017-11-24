package com.google.android.gms.tagmanager;

import android.os.*;

private class zzb extends Handler
{
    private final ContainerAvailableListener zzbhZ;
    
    public zzb(final ContainerAvailableListener zzbhZ, final Looper looper) {
        super(looper);
        this.zzbhZ = zzbhZ;
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                zzbg.e("Don't know how to handle this message.");
            }
            case 1: {
                this.zzfV((String)message.obj);
            }
        }
    }
    
    public void zzfU(final String s) {
        this.sendMessage(this.obtainMessage(1, (Object)s));
    }
    
    protected void zzfV(final String s) {
        this.zzbhZ.onContainerAvailable(zzo.this, s);
    }
}
