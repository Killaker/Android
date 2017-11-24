package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.*;
import android.os.*;

class zzo implements ContainerHolder
{
    private Status zzUX;
    private final Looper zzagr;
    private boolean zzapK;
    private Container zzbhU;
    private Container zzbhV;
    private zzb zzbhW;
    private zza zzbhX;
    private TagManager zzbhY;
    
    public zzo(final Status zzUX) {
        this.zzUX = zzUX;
        this.zzagr = null;
    }
    
    public zzo(final TagManager zzbhY, Looper mainLooper, final Container zzbhU, final zza zzbhX) {
        this.zzbhY = zzbhY;
        if (mainLooper == null) {
            mainLooper = Looper.getMainLooper();
        }
        this.zzagr = mainLooper;
        this.zzbhU = zzbhU;
        this.zzbhX = zzbhX;
        this.zzUX = Status.zzagC;
        zzbhY.zza(this);
    }
    
    private void zzGe() {
        if (this.zzbhW != null) {
            this.zzbhW.zzfU(this.zzbhV.zzGb());
        }
    }
    
    @Override
    public Container getContainer() {
        Container zzbhU = null;
        synchronized (this) {
            if (this.zzapK) {
                zzbg.e("ContainerHolder is released.");
            }
            else {
                if (this.zzbhV != null) {
                    this.zzbhU = this.zzbhV;
                    this.zzbhV = null;
                }
                zzbhU = this.zzbhU;
            }
            return zzbhU;
        }
    }
    
    String getContainerId() {
        if (this.zzapK) {
            zzbg.e("getContainerId called on a released ContainerHolder.");
            return "";
        }
        return this.zzbhU.getContainerId();
    }
    
    @Override
    public Status getStatus() {
        return this.zzUX;
    }
    
    @Override
    public void refresh() {
        synchronized (this) {
            if (this.zzapK) {
                zzbg.e("Refreshing a released ContainerHolder.");
            }
            else {
                this.zzbhX.zzGf();
            }
        }
    }
    
    @Override
    public void release() {
        synchronized (this) {
            if (this.zzapK) {
                zzbg.e("Releasing a released ContainerHolder.");
            }
            else {
                this.zzapK = true;
                this.zzbhY.zzb(this);
                this.zzbhU.release();
                this.zzbhU = null;
                this.zzbhV = null;
                this.zzbhX = null;
                this.zzbhW = null;
            }
        }
    }
    
    @Override
    public void setContainerAvailableListener(final ContainerAvailableListener containerAvailableListener) {
        while (true) {
            Label_0034: {
                synchronized (this) {
                    if (this.zzapK) {
                        zzbg.e("ContainerHolder is released.");
                    }
                    else {
                        if (containerAvailableListener != null) {
                            break Label_0034;
                        }
                        this.zzbhW = null;
                    }
                    return;
                }
            }
            this.zzbhW = new zzb(containerAvailableListener, this.zzagr);
            if (this.zzbhV != null) {
                this.zzGe();
            }
        }
    }
    
    String zzGd() {
        if (this.zzapK) {
            zzbg.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
            return "";
        }
        return this.zzbhX.zzGd();
    }
    
    public void zza(final Container zzbhV) {
        while (true) {
            Label_0031: {
                synchronized (this) {
                    if (!this.zzapK) {
                        if (zzbhV != null) {
                            break Label_0031;
                        }
                        zzbg.e("Unexpected null container.");
                    }
                    return;
                }
            }
            this.zzbhV = zzbhV;
            this.zzGe();
        }
    }
    
    public void zzfR(final String s) {
        synchronized (this) {
            if (!this.zzapK) {
                this.zzbhU.zzfR(s);
            }
        }
    }
    
    void zzfT(final String s) {
        if (this.zzapK) {
            zzbg.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
            return;
        }
        this.zzbhX.zzfT(s);
    }
    
    public interface zza
    {
        String zzGd();
        
        void zzGf();
        
        void zzfT(final String p0);
    }
    
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
}
