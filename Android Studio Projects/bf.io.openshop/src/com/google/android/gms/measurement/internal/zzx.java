package com.google.android.gms.measurement.internal;

import android.text.*;
import android.os.*;
import com.google.android.gms.common.*;
import java.util.concurrent.*;
import java.util.*;
import android.support.annotation.*;

public class zzx extends zzm.zza
{
    private final zzw zzaTV;
    private final boolean zzaYw;
    
    public zzx(final zzw zzaTV) {
        com.google.android.gms.common.internal.zzx.zzz(zzaTV);
        this.zzaTV = zzaTV;
        this.zzaYw = false;
    }
    
    public zzx(final zzw zzaTV, final boolean zzaYw) {
        com.google.android.gms.common.internal.zzx.zzz(zzaTV);
        this.zzaTV = zzaTV;
        this.zzaYw = zzaYw;
    }
    
    @BinderThread
    private void zzfm(final String s) throws SecurityException {
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.zzaTV.zzAo().zzCE().zzfg("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        try {
            this.zzfn(s);
        }
        catch (SecurityException ex) {
            this.zzaTV.zzAo().zzCE().zzj("Measurement Service called with invalid calling package", s);
            throw ex;
        }
    }
    
    private void zzfn(final String s) throws SecurityException {
        int n;
        if (this.zzaYw) {
            n = Process.myUid();
        }
        else {
            n = Binder.getCallingUid();
        }
        if (!zze.zzb(this.zzaTV.getContext(), n, s) && (!zze.zzf(this.zzaTV.getContext(), n) || this.zzaTV.zzCZ())) {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", s));
        }
    }
    
    @BinderThread
    public List<UserAttributeParcel> zza(final AppMetadata appMetadata, final boolean b) {
        com.google.android.gms.common.internal.zzx.zzz(appMetadata);
        this.zzfm(appMetadata.packageName);
        final Future<List<zzai>> zzd = this.zzaTV.zzCn().zzd((Callable<List<zzai>>)new Callable<List<zzai>>() {
            public List<zzai> zzDh() throws Exception {
                return zzx.this.zzaTV.zzCj().zzeX(appMetadata.zzaVt);
            }
        });
        try {
            final List<zzai> list = zzd.get();
            final ArrayList list2 = new ArrayList<UserAttributeParcel>(list.size());
            for (final zzai zzai : list) {
                if (b || !zzaj.zzfv(zzai.mName)) {
                    list2.add(new UserAttributeParcel(zzai));
                }
            }
            goto Label_0149;
        }
        catch (InterruptedException ex) {}
        catch (ExecutionException ex2) {
            goto Label_0130;
        }
    }
    
    @BinderThread
    public void zza(final AppMetadata appMetadata) {
        com.google.android.gms.common.internal.zzx.zzz(appMetadata);
        this.zzfm(appMetadata.packageName);
        this.zzaTV.zzCn().zzg(new Runnable() {
            @Override
            public void run() {
                zzx.this.zzfl(appMetadata.zzaVx);
                zzx.this.zzaTV.zzd(appMetadata);
            }
        });
    }
    
    @BinderThread
    public void zza(final EventParcel eventParcel, final AppMetadata appMetadata) {
        com.google.android.gms.common.internal.zzx.zzz(eventParcel);
        com.google.android.gms.common.internal.zzx.zzz(appMetadata);
        this.zzfm(appMetadata.packageName);
        this.zzaTV.zzCn().zzg(new Runnable() {
            @Override
            public void run() {
                zzx.this.zzfl(appMetadata.zzaVx);
                zzx.this.zzaTV.zzb(eventParcel, appMetadata);
            }
        });
    }
    
    @BinderThread
    public void zza(final EventParcel eventParcel, final String s, final String s2) {
        com.google.android.gms.common.internal.zzx.zzz(eventParcel);
        com.google.android.gms.common.internal.zzx.zzcM(s);
        this.zzfm(s);
        this.zzaTV.zzCn().zzg(new Runnable() {
            @Override
            public void run() {
                zzx.this.zzfl(s2);
                zzx.this.zzaTV.zza(eventParcel, s);
            }
        });
    }
    
    @BinderThread
    public void zza(final UserAttributeParcel userAttributeParcel, final AppMetadata appMetadata) {
        com.google.android.gms.common.internal.zzx.zzz(userAttributeParcel);
        com.google.android.gms.common.internal.zzx.zzz(appMetadata);
        this.zzfm(appMetadata.packageName);
        if (userAttributeParcel.getValue() == null) {
            this.zzaTV.zzCn().zzg(new Runnable() {
                @Override
                public void run() {
                    zzx.this.zzfl(appMetadata.zzaVx);
                    zzx.this.zzaTV.zzc(userAttributeParcel, appMetadata);
                }
            });
            return;
        }
        this.zzaTV.zzCn().zzg(new Runnable() {
            @Override
            public void run() {
                zzx.this.zzfl(appMetadata.zzaVx);
                zzx.this.zzaTV.zzb(userAttributeParcel, appMetadata);
            }
        });
    }
    
    @BinderThread
    public void zzb(final AppMetadata appMetadata) {
        com.google.android.gms.common.internal.zzx.zzz(appMetadata);
        this.zzfm(appMetadata.packageName);
        this.zzaTV.zzCn().zzg(new Runnable() {
            @Override
            public void run() {
                zzx.this.zzfl(appMetadata.zzaVx);
                zzx.this.zzaTV.zzc(appMetadata);
            }
        });
    }
    
    @WorkerThread
    void zzfl(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        final String[] split = s.split(":", 2);
        if (split.length != 2) {
            return;
        }
        long longValue;
        try {
            longValue = Long.valueOf(split[0]);
            if (longValue > 0L) {
                this.zzaTV.zzCo().zzaXi.zzf(split[1], longValue);
                return;
            }
        }
        catch (NumberFormatException ex) {
            this.zzaTV.zzAo().zzCF().zzj("Combining sample with a non-number weight", split[0]);
            return;
        }
        this.zzaTV.zzAo().zzCF().zzj("Combining sample with a non-positive weight", longValue);
    }
}
