package com.google.android.gms.internal;

import java.util.concurrent.*;
import android.util.*;
import com.google.android.gms.common.api.internal.*;
import com.google.android.gms.clearcut.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public class zzlv implements com.google.android.gms.clearcut.zzc
{
    private static final Object zzafn;
    private static final zze zzafo;
    private static final long zzafp;
    private GoogleApiClient zzaaj;
    private final zza zzafq;
    private final Object zzafr;
    private long zzafs;
    private final long zzaft;
    private ScheduledFuture<?> zzafu;
    private final Runnable zzafv;
    private final zzmq zzqW;
    
    static {
        zzafn = new Object();
        zzafo = new zze();
        zzafp = TimeUnit.MILLISECONDS.convert(2L, TimeUnit.MINUTES);
    }
    
    public zzlv() {
        this(new zzmt(), zzlv.zzafp, (zza)new zzb());
    }
    
    public zzlv(final zzmq zzqW, final long zzaft, final zza zzafq) {
        this.zzafr = new Object();
        this.zzafs = 0L;
        this.zzafu = null;
        this.zzaaj = null;
        this.zzafv = new Runnable() {
            @Override
            public void run() {
                synchronized (zzlv.this.zzafr) {
                    if (zzlv.this.zzafs <= zzlv.this.zzqW.elapsedRealtime() && zzlv.this.zzaaj != null) {
                        Log.i("ClearcutLoggerApiImpl", "disconnect managed GoogleApiClient");
                        zzlv.this.zzaaj.disconnect();
                        zzlv.this.zzaaj = null;
                    }
                }
            }
        };
        this.zzqW = zzqW;
        this.zzaft = zzaft;
        this.zzafq = zzafq;
    }
    
    private static void zza(final LogEventParcelable logEventParcelable) {
        if (logEventParcelable.zzafl != null && logEventParcelable.zzafk.zzbuY.length == 0) {
            logEventParcelable.zzafk.zzbuY = logEventParcelable.zzafl.zzoF();
        }
        if (logEventParcelable.zzafm != null && logEventParcelable.zzafk.zzbvf.length == 0) {
            logEventParcelable.zzafk.zzbvf = logEventParcelable.zzafm.zzoF();
        }
        logEventParcelable.zzafi = zzsu.toByteArray(logEventParcelable.zzafk);
    }
    
    private zzd zzb(final GoogleApiClient googleApiClient, final LogEventParcelable logEventParcelable) {
        zzlv.zzafo.zzoH();
        final zzd zzd = new zzd(logEventParcelable, googleApiClient);
        zzd.zza(new PendingResult.zza() {
            @Override
            public void zzu(final Status status) {
                zzlv.zzafo.zzoI();
            }
        });
        return zzd;
    }
    
    @Override
    public PendingResult<Status> zza(final GoogleApiClient googleApiClient, final LogEventParcelable logEventParcelable) {
        zza(logEventParcelable);
        return googleApiClient.zza((PendingResult<Status>)this.zzb(googleApiClient, logEventParcelable));
    }
    
    @Override
    public boolean zza(final GoogleApiClient googleApiClient, final long n, final TimeUnit timeUnit) {
        try {
            return zzlv.zzafo.zza(n, timeUnit);
        }
        catch (InterruptedException ex) {
            Log.e("ClearcutLoggerApiImpl", "flush interrupted");
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    public interface zza
    {
    }
    
    public static class zzb implements zza
    {
    }
    
    abstract static class zzc<R extends Result> extends zza.zza<R, zzlw>
    {
        public zzc(final GoogleApiClient googleApiClient) {
            super(zzb.zzUI, googleApiClient);
        }
    }
    
    final class zzd extends zzc<Status>
    {
        private final LogEventParcelable zzafx;
        
        zzd(final LogEventParcelable zzafx, final GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.zzafx = zzafx;
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof zzd && this.zzafx.equals(((zzd)o).zzafx);
        }
        
        @Override
        public String toString() {
            return "MethodImpl(" + this.zzafx + ")";
        }
        
        protected void zza(final zzlw zzlw) throws RemoteException {
            final zzlx.zza zza = new zzlx.zza() {
                public void zzv(final Status status) {
                    zzd.this.zza((R)status);
                }
            };
            try {
                zza(this.zzafx);
                zzlw.zza(zza, this.zzafx);
            }
            catch (Throwable t) {
                Log.e("ClearcutLoggerApiImpl", "MessageNanoProducer " + this.zzafx.zzafl.toString() + " threw: " + t.toString());
            }
        }
        
        protected Status zzb(final Status status) {
            return status;
        }
    }
    
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
}
