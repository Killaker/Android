package com.google.android.gms.common.api.internal;

import android.os.*;
import android.util.*;
import com.google.android.gms.common.api.*;

public static class zza<R extends Result> extends Handler
{
    public zza() {
        this(Looper.getMainLooper());
    }
    
    public zza(final Looper looper) {
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, (Throwable)new Exception());
            }
            case 1: {
                final Pair pair = (Pair)message.obj;
                this.zzb((ResultCallback<? super Result>)pair.first, (Result)pair.second);
            }
            case 2: {
                ((zzb)message.obj).zzx(Status.zzagF);
            }
        }
    }
    
    public void zza(final ResultCallback<? super R> resultCallback, final R r) {
        this.sendMessage(this.obtainMessage(1, (Object)new Pair((Object)resultCallback, (Object)r)));
    }
    
    public void zza(final zzb<R> zzb, final long n) {
        this.sendMessageDelayed(this.obtainMessage(2, (Object)zzb), n);
    }
    
    protected void zzb(final ResultCallback<? super R> resultCallback, final R r) {
        try {
            resultCallback.onResult(r);
        }
        catch (RuntimeException ex) {
            zzb.zzc(r);
            throw ex;
        }
    }
    
    public void zzph() {
        this.removeMessages(2);
    }
}
