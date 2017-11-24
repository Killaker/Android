package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.*;
import com.google.android.gms.analytics.*;
import android.content.*;
import java.util.concurrent.*;

public class zzb extends zzd
{
    private final zzl zzQb;
    
    public zzb(final zzf zzf, final zzg zzg) {
        super(zzf);
        zzx.zzz(zzg);
        this.zzQb = zzg.zzj(zzf);
    }
    
    void onServiceConnected() {
        this.zzjk();
        this.zzQb.onServiceConnected();
    }
    
    public void setLocalDispatchPeriod(final int n) {
        this.zzjv();
        this.zzb("setLocalDispatchPeriod (sec)", n);
        this.zzjo().zzf(new Runnable() {
            @Override
            public void run() {
                zzb.this.zzQb.zzs(1000L * n);
            }
        });
    }
    
    public void start() {
        this.zzQb.start();
    }
    
    public void zzJ(final boolean b) {
        this.zza("Network connectivity status changed", b);
        this.zzjo().zzf(new Runnable() {
            @Override
            public void run() {
                zzb.this.zzQb.zzJ(b);
            }
        });
    }
    
    public long zza(final zzh zzh) {
        this.zzjv();
        zzx.zzz(zzh);
        this.zzjk();
        final long zza = this.zzQb.zza(zzh, true);
        if (zza == 0L) {
            this.zzQb.zzc(zzh);
        }
        return zza;
    }
    
    public void zza(final zzab zzab) {
        zzx.zzz(zzab);
        this.zzjv();
        this.zzb("Hit delivery requested", zzab);
        this.zzjo().zzf(new Runnable() {
            @Override
            public void run() {
                zzb.this.zzQb.zza(zzab);
            }
        });
    }
    
    public void zza(final zzw zzw) {
        this.zzjv();
        this.zzjo().zzf(new Runnable() {
            @Override
            public void run() {
                zzb.this.zzQb.zzb(zzw);
            }
        });
    }
    
    public void zza(final String s, final Runnable runnable) {
        zzx.zzh(s, "campaign param can't be empty");
        this.zzjo().zzf(new Runnable() {
            @Override
            public void run() {
                zzb.this.zzQb.zzbl(s);
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }
    
    @Override
    protected void zziJ() {
        this.zzQb.zza();
    }
    
    public void zzjc() {
        this.zzjv();
        this.zzjj();
        this.zzjo().zzf(new Runnable() {
            @Override
            public void run() {
                zzb.this.zzQb.zzjc();
            }
        });
    }
    
    public void zzjd() {
        this.zzjv();
        final Context context = this.getContext();
        if (AnalyticsReceiver.zzY(context) && AnalyticsService.zzZ(context)) {
            final Intent intent = new Intent(context, (Class)AnalyticsService.class);
            intent.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            context.startService(intent);
            return;
        }
        this.zza((zzw)null);
    }
    
    public boolean zzje() {
        this.zzjv();
        final Future<Object> zzc = this.zzjo().zzc((Callable<Object>)new Callable<Void>() {
            public Void zzdt() throws Exception {
                zzb.this.zzQb.zzka();
                return null;
            }
        });
        try {
            zzc.get(4L, TimeUnit.SECONDS);
            return true;
        }
        catch (InterruptedException ex) {
            this.zzd("syncDispatchLocalHits interrupted", ex);
            return false;
        }
        catch (ExecutionException ex2) {
            this.zze("syncDispatchLocalHits failed", ex2);
            return false;
        }
        catch (TimeoutException ex3) {
            this.zzd("syncDispatchLocalHits timed out", ex3);
            return false;
        }
    }
    
    public void zzjf() {
        this.zzjv();
        com.google.android.gms.measurement.zzg.zzjk();
        this.zzQb.zzjf();
    }
    
    public void zzjg() {
        this.zzbd("Radio powered up");
        this.zzjd();
    }
    
    void zzjh() {
        this.zzjk();
        this.zzQb.zzjh();
    }
}
