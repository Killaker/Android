package com.google.android.gms.clearcut;

import java.util.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.playlog.internal.*;

public class zza
{
    private String zzaeS;
    private int zzaeT;
    private String zzaeU;
    private String zzaeV;
    private int zzaeX;
    private final zzb zzafb;
    private zzb zzafc;
    private ArrayList<Integer> zzafd;
    private final zzsz.zzd zzafe;
    private boolean zzaff;
    
    private zza(final zzb zzb, final byte[] array) {
        this(zzb, array, (zzb)null);
    }
    
    private zza(final byte[] zzbuY, final zzb zzafb) {
        this.zzaeT = zzb.zza(zzb.this);
        this.zzaeS = zzb.zzb(zzb.this);
        this.zzaeU = zzb.zzc(zzb.this);
        this.zzaeV = zzb.zzd(zzb.this);
        this.zzaeX = zzb.zze(zzb.this);
        this.zzafd = null;
        this.zzafe = new zzsz.zzd();
        this.zzaff = false;
        this.zzaeU = zzb.zzc(zzb.this);
        this.zzaeV = zzb.zzd(zzb.this);
        this.zzafe.zzbuR = zzb.zzf(zzb.this).currentTimeMillis();
        this.zzafe.zzbuS = zzb.zzf(zzb.this).elapsedRealtime();
        this.zzafe.zzbvi = zzb.zzh(zzb.this).zzah(zzb.zzg(zzb.this));
        this.zzafe.zzbvd = zzb.zzi(zzb.this).zzC(this.zzafe.zzbuR);
        if (zzbuY != null) {
            this.zzafe.zzbuY = zzbuY;
        }
        this.zzafb = zzafb;
    }
    
    public zza zzbq(final int zzbuU) {
        this.zzafe.zzbuU = zzbuU;
        return this;
    }
    
    public zza zzbr(final int zzob) {
        this.zzafe.zzob = zzob;
        return this;
    }
    
    public PendingResult<Status> zzd(final GoogleApiClient googleApiClient) {
        if (this.zzaff) {
            throw new IllegalStateException("do not reuse LogEventBuilder");
        }
        this.zzaff = true;
        return zzb.zzm(zzb.this).zza(googleApiClient, this.zzoE());
    }
    
    public LogEventParcelable zzoE() {
        return new LogEventParcelable(new PlayLoggerContext(zzb.zzk(zzb.this), zzb.zzl(zzb.this), this.zzaeT, this.zzaeS, this.zzaeU, this.zzaeV, zzb.zzj(zzb.this), this.zzaeX), this.zzafe, this.zzafb, this.zzafc, zzb.zzc(this.zzafd));
    }
}
