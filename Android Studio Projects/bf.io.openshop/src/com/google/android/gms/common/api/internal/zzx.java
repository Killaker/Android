package com.google.android.gms.common.api.internal;

import java.lang.ref.*;
import com.google.android.gms.common.api.*;
import android.util.*;
import android.support.annotation.*;
import android.os.*;

public class zzx<R extends Result> extends TransformedResult<R> implements ResultCallback<R>
{
    private final Object zzagI;
    private final WeakReference<GoogleApiClient> zzagK;
    private ResultTransform<? super R, ? extends Result> zzaiN;
    private zzx<? extends Result> zzaiO;
    private ResultCallbacks<? super R> zzaiP;
    private PendingResult<R> zzaiQ;
    private Status zzaiR;
    private final zza zzaiS;
    
    public zzx(final WeakReference<GoogleApiClient> zzagK) {
        this.zzaiN = null;
        this.zzaiO = null;
        this.zzaiP = null;
        this.zzaiQ = null;
        this.zzagI = new Object();
        this.zzaiR = null;
        com.google.android.gms.common.internal.zzx.zzb(zzagK, "GoogleApiClient reference must not be null");
        this.zzagK = zzagK;
        final GoogleApiClient googleApiClient = this.zzagK.get();
        Looper looper;
        if (googleApiClient != null) {
            looper = googleApiClient.getLooper();
        }
        else {
            looper = Looper.getMainLooper();
        }
        this.zzaiS = new zza(looper);
    }
    
    private void zzc(final Result result) {
        if (!(result instanceof Releasable)) {
            return;
        }
        try {
            ((Releasable)result).release();
        }
        catch (RuntimeException ex) {
            Log.w("TransformedResultImpl", "Unable to release " + result, (Throwable)ex);
        }
    }
    
    private void zzpT() {
        if (this.zzaiN != null || this.zzaiP != null) {
            final GoogleApiClient googleApiClient = this.zzagK.get();
            if (this.zzaiN != null && googleApiClient != null) {
                googleApiClient.zza(this);
            }
            if (this.zzaiR != null) {
                this.zzz(this.zzaiR);
                return;
            }
            if (this.zzaiQ != null) {
                this.zzaiQ.setResultCallback(this);
            }
        }
    }
    
    private boolean zzpV() {
        final GoogleApiClient googleApiClient = this.zzagK.get();
        return this.zzaiP != null && googleApiClient != null;
    }
    
    private void zzy(final Status zzaiR) {
        synchronized (this.zzagI) {
            this.zzz(this.zzaiR = zzaiR);
        }
    }
    
    private void zzz(final Status status) {
        synchronized (this.zzagI) {
            if (this.zzaiN != null) {
                final Status onFailure = this.zzaiN.onFailure(status);
                com.google.android.gms.common.internal.zzx.zzb(onFailure, "onFailure must not return null");
                this.zzaiO.zzy(onFailure);
            }
            else if (this.zzpV()) {
                this.zzaiP.onFailure(status);
            }
        }
    }
    
    @Override
    public void andFinally(@NonNull final ResultCallbacks<? super R> zzaiP) {
    Label_0033_Outer:
        while (true) {
            boolean b = true;
            while (true) {
            Label_0064:
                while (true) {
                    synchronized (this.zzagI) {
                        if (this.zzaiP == null) {
                            final boolean b2 = b;
                            com.google.android.gms.common.internal.zzx.zza(b2, (Object)"Cannot call andFinally() twice.");
                            if (this.zzaiN == null) {
                                com.google.android.gms.common.internal.zzx.zza(b, (Object)"Cannot call then() and andFinally() on the same TransformedResult.");
                                this.zzaiP = zzaiP;
                                this.zzpT();
                                return;
                            }
                            break Label_0064;
                        }
                    }
                    final boolean b2 = false;
                    continue Label_0033_Outer;
                }
                b = false;
                continue;
            }
        }
    }
    
    @Override
    public void onResult(final R r) {
        while (true) {
            synchronized (this.zzagI) {
                if (r.getStatus().isSuccess()) {
                    if (this.zzaiN != null) {
                        zzs.zzpN().submit(new Runnable() {
                            @WorkerThread
                            @Override
                            public void run() {
                                try {
                                    zzx.this.zzaiS.sendMessage(zzx.this.zzaiS.obtainMessage(0, (Object)zzx.this.zzaiN.onSuccess(r)));
                                }
                                catch (RuntimeException ex) {
                                    zzx.this.zzaiS.sendMessage(zzx.this.zzaiS.obtainMessage(1, (Object)ex));
                                }
                                finally {
                                    zzx.this.zzc(r);
                                    final GoogleApiClient googleApiClient = (GoogleApiClient)zzx.this.zzagK.get();
                                    if (googleApiClient != null) {
                                        googleApiClient.zzb(zzx.this);
                                    }
                                }
                            }
                        });
                    }
                    else if (this.zzpV()) {
                        this.zzaiP.onSuccess((Object)r);
                    }
                    return;
                }
            }
            this.zzy(r.getStatus());
            this.zzc(r);
        }
    }
    
    @NonNull
    @Override
    public <S extends Result> TransformedResult<S> then(@NonNull final ResultTransform<? super R, ? extends S> zzaiN) {
    Label_0033_Outer:
        while (true) {
            boolean b = true;
            while (true) {
            Label_0085:
                while (true) {
                    synchronized (this.zzagI) {
                        if (this.zzaiN == null) {
                            final boolean b2 = b;
                            com.google.android.gms.common.internal.zzx.zza(b2, (Object)"Cannot call then() twice.");
                            if (this.zzaiP == null) {
                                com.google.android.gms.common.internal.zzx.zza(b, (Object)"Cannot call then() and andFinally() on the same TransformedResult.");
                                this.zzaiN = zzaiN;
                                final zzx<Result> zzaiO = new zzx<Result>(this.zzagK);
                                this.zzaiO = zzaiO;
                                this.zzpT();
                                return (TransformedResult<S>)zzaiO;
                            }
                            break Label_0085;
                        }
                    }
                    final boolean b2 = false;
                    continue Label_0033_Outer;
                }
                b = false;
                continue;
            }
        }
    }
    
    public void zza(final PendingResult<?> zzaiQ) {
        synchronized (this.zzagI) {
            this.zzaiQ = (PendingResult<R>)zzaiQ;
            this.zzpT();
        }
    }
    
    void zzpU() {
        synchronized (this.zzagI) {
            this.zzaiP = null;
        }
    }
    
    private final class zza extends Handler
    {
        public zza(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.e("TransformedResultImpl", "TransformationResultHandler received unknown message type: " + message.what);
                }
                case 0: {
                    final PendingResult pendingResult = (PendingResult)message.obj;
                    final Object zzf = zzx.this.zzagI;
                    // monitorenter(zzf)
                    Label_0116: {
                        if (pendingResult != null) {
                            break Label_0116;
                        }
                        try {
                            zzx.this.zzaiO.zzy(new Status(13, "Transform returned null"));
                            return;
                        }
                        finally {
                        }
                        // monitorexit(zzf)
                    }
                    if (pendingResult instanceof zzt) {
                        zzx.this.zzaiO.zzy(((zzt<?>)pendingResult).getStatus());
                        return;
                    }
                    zzx.this.zzaiO.zza(pendingResult);
                }
                case 1: {
                    final RuntimeException ex = (RuntimeException)message.obj;
                    Log.e("TransformedResultImpl", "Runtime exception on the transformation worker thread: " + ex.getMessage());
                    throw ex;
                }
            }
        }
    }
}
