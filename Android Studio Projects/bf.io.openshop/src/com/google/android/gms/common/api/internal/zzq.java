package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.*;
import android.os.*;

public final class zzq<L>
{
    private volatile L mListener;
    private final zza zzaiw;
    
    zzq(final Looper looper, final L l) {
        this.zzaiw = new zza(looper);
        this.mListener = zzx.zzb(l, "Listener must not be null");
    }
    
    public void clear() {
        this.mListener = null;
    }
    
    public void zza(final zzb<? super L> zzb) {
        zzx.zzb(zzb, "Notifier must not be null");
        this.zzaiw.sendMessage(this.zzaiw.obtainMessage(1, (Object)zzb));
    }
    
    void zzb(final zzb<? super L> zzb) {
        final L mListener = this.mListener;
        if (mListener == null) {
            zzb.zzpr();
            return;
        }
        try {
            zzb.zzt(mListener);
        }
        catch (RuntimeException ex) {
            zzb.zzpr();
            throw ex;
        }
    }
    
    private final class zza extends Handler
    {
        public zza(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            boolean b = true;
            if (message.what != (b ? 1 : 0)) {
                b = false;
            }
            zzx.zzac(b);
            zzq.this.zzb((zzb<? super L>)message.obj);
        }
    }
    
    public interface zzb<L>
    {
        void zzpr();
        
        void zzt(final L p0);
    }
}
