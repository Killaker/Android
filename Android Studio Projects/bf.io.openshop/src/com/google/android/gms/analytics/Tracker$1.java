package com.google.android.gms.analytics;

import java.util.*;
import android.text.*;
import com.google.android.gms.analytics.internal.*;
import com.google.android.gms.internal.*;

class Tracker$1 implements Runnable {
    final /* synthetic */ String zzPA;
    final /* synthetic */ long zzPB;
    final /* synthetic */ boolean zzPC;
    final /* synthetic */ boolean zzPD;
    final /* synthetic */ String zzPE;
    final /* synthetic */ Map zzPy;
    final /* synthetic */ boolean zzPz;
    
    @Override
    public void run() {
        boolean b = true;
        if (Tracker.zza(Tracker.this).zziM()) {
            this.zzPy.put("sc", "start");
        }
        zzam.zzd(this.zzPy, "cid", Tracker.this.zziC().getClientId());
        final String s = this.zzPy.get("sf");
        if (s != null) {
            final double zza = zzam.zza(s, 100.0);
            if (zzam.zza(zza, this.zzPy.get("cid"))) {
                Tracker.this.zzb("Sampling enabled. Hit sampled out. sample rate", zza);
                return;
            }
        }
        final com.google.android.gms.analytics.internal.zza zzb = Tracker.zzb(Tracker.this);
        if (this.zzPz) {
            zzam.zzb(this.zzPy, "ate", zzb.zziU());
            zzam.zzc(this.zzPy, "adid", zzb.zziY());
        }
        else {
            this.zzPy.remove("ate");
            this.zzPy.remove("adid");
        }
        final zzpq zzjS = Tracker.zzc(Tracker.this).zzjS();
        zzam.zzc(this.zzPy, "an", zzjS.zzlg());
        zzam.zzc(this.zzPy, "av", zzjS.zzli());
        zzam.zzc(this.zzPy, "aid", zzjS.zzwK());
        zzam.zzc(this.zzPy, "aiid", zzjS.zzAJ());
        this.zzPy.put("v", "1");
        this.zzPy.put("_v", zze.zzQm);
        zzam.zzc(this.zzPy, "ul", Tracker.zzd(Tracker.this).zzkZ().getLanguage());
        zzam.zzc(this.zzPy, "sr", Tracker.zze(Tracker.this).zzla());
        if (((!this.zzPA.equals("transaction") && !this.zzPA.equals("item")) || !b) && !Tracker.zzf(Tracker.this).zzlw()) {
            Tracker.zzg(Tracker.this).zzh(this.zzPy, "Too many hits sent too quickly, rate limiting invoked");
            return;
        }
        long n = zzam.zzbt(this.zzPy.get("ht"));
        if (n == 0L) {
            n = this.zzPB;
        }
        if (this.zzPC) {
            Tracker.zzh(Tracker.this).zzc("Dry run enabled. Would have sent hit", new zzab(Tracker.this, this.zzPy, n, this.zzPD));
            return;
        }
        final String s2 = this.zzPy.get("cid");
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        zzam.zza(hashMap, "uid", this.zzPy);
        zzam.zza(hashMap, "an", this.zzPy);
        zzam.zza(hashMap, "aid", this.zzPy);
        zzam.zza(hashMap, "av", this.zzPy);
        zzam.zza(hashMap, "aiid", this.zzPy);
        final String zzPE = this.zzPE;
        if (TextUtils.isEmpty((CharSequence)this.zzPy.get("adid"))) {
            b = false;
        }
        this.zzPy.put("_s", String.valueOf(Tracker.zzi(Tracker.this).zza(new zzh(0L, s2, zzPE, b, 0L, hashMap))));
        Tracker.zzj(Tracker.this).zza(new zzab(Tracker.this, this.zzPy, n, this.zzPD));
    }
}