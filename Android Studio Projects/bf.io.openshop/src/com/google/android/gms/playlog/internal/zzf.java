package com.google.android.gms.playlog.internal;

import android.content.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.internal.*;
import android.util.*;
import java.util.*;
import android.os.*;

public class zzf extends zzj<com.google.android.gms.playlog.internal.zza>
{
    private final String zzTJ;
    private final com.google.android.gms.playlog.internal.zzd zzbdT;
    private final com.google.android.gms.playlog.internal.zzb zzbdU;
    private boolean zzbdV;
    private final Object zzpV;
    
    public zzf(final Context context, final Looper looper, final com.google.android.gms.playlog.internal.zzd zzd, final com.google.android.gms.common.internal.zzf zzf) {
        super(context, looper, 24, zzf, zzd, zzd);
        this.zzTJ = context.getPackageName();
        (this.zzbdT = zzx.zzz(zzd)).zza(this);
        this.zzbdU = new com.google.android.gms.playlog.internal.zzb();
        this.zzpV = new Object();
        this.zzbdV = true;
    }
    
    private void zzEW() {
        com.google.android.gms.common.internal.zzb.zzab(!this.zzbdV);
        if (!this.zzbdU.isEmpty()) {
            PlayLoggerContext playLoggerContext;
            ArrayList<LogEvent> list = null;
            while (true) {
                playLoggerContext = null;
                while (true) {
                    Label_0123: {
                        try {
                            list = new ArrayList<LogEvent>();
                            for (final com.google.android.gms.playlog.internal.zzb.zza zza : this.zzbdU.zzEU()) {
                                if (zza.zzbdI == null) {
                                    break Label_0123;
                                }
                                this.zzqJ().zza(this.zzTJ, zza.zzbdG, zzsu.toByteArray(zza.zzbdI));
                            }
                            break;
                        }
                        catch (RemoteException ex) {
                            Log.e("PlayLoggerImpl", "Couldn't send cached log events to AndroidLog service.  Retaining in memory cache.");
                        }
                        return;
                    }
                    final com.google.android.gms.playlog.internal.zzb.zza zza;
                    PlayLoggerContext playLoggerContext2;
                    if (zza.zzbdG.equals(playLoggerContext)) {
                        list.add(zza.zzbdH);
                        playLoggerContext2 = playLoggerContext;
                    }
                    else {
                        if (!list.isEmpty()) {
                            this.zzqJ().zza(this.zzTJ, playLoggerContext, list);
                            list.clear();
                        }
                        final PlayLoggerContext zzbdG = zza.zzbdG;
                        list.add(zza.zzbdH);
                        playLoggerContext2 = zzbdG;
                    }
                    playLoggerContext = playLoggerContext2;
                    continue;
                }
            }
            if (!list.isEmpty()) {
                this.zzqJ().zza(this.zzTJ, playLoggerContext, list);
            }
            this.zzbdU.clear();
        }
    }
    
    private void zzc(final PlayLoggerContext playLoggerContext, final LogEvent logEvent) {
        this.zzbdU.zza(playLoggerContext, logEvent);
    }
    
    private void zzd(final PlayLoggerContext playLoggerContext, final LogEvent logEvent) {
        try {
            this.zzEW();
            this.zzqJ().zza(this.zzTJ, playLoggerContext, logEvent);
        }
        catch (RemoteException ex) {
            Log.e("PlayLoggerImpl", "Couldn't send log event.  Will try caching.");
            this.zzc(playLoggerContext, logEvent);
        }
        catch (IllegalStateException ex2) {
            Log.e("PlayLoggerImpl", "Service was disconnected.  Will try caching.");
            this.zzc(playLoggerContext, logEvent);
        }
    }
    
    public void start() {
        synchronized (this.zzpV) {
            if (this.isConnecting() || this.isConnected()) {
                return;
            }
            this.zzbdT.zzat(true);
            this.zzqG();
        }
    }
    
    public void stop() {
        synchronized (this.zzpV) {
            this.zzbdT.zzat(false);
            this.disconnect();
        }
    }
    
    void zzau(final boolean zzbdV) {
        synchronized (this.zzpV) {
            final boolean zzbdV2 = this.zzbdV;
            this.zzbdV = zzbdV;
            if (zzbdV2 && !this.zzbdV) {
                this.zzEW();
            }
        }
    }
    
    public void zzb(final PlayLoggerContext playLoggerContext, final LogEvent logEvent) {
        synchronized (this.zzpV) {
            if (this.zzbdV) {
                this.zzc(playLoggerContext, logEvent);
            }
            else {
                this.zzd(playLoggerContext, logEvent);
            }
        }
    }
    
    protected com.google.android.gms.playlog.internal.zza zzdO(final IBinder binder) {
        return com.google.android.gms.playlog.internal.zza.zza.zzdN(binder);
    }
    
    @Override
    protected String zzgu() {
        return "com.google.android.gms.playlog.service.START";
    }
    
    @Override
    protected String zzgv() {
        return "com.google.android.gms.playlog.internal.IPlayLogService";
    }
}
