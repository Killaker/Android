package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.measurement.*;
import com.google.android.gms.analytics.*;
import android.content.*;
import android.database.sqlite.*;
import android.text.*;
import android.util.*;
import java.util.*;

class zzl extends zzd
{
    private boolean mStarted;
    private final zzj zzQY;
    private final zzah zzQZ;
    private final zzag zzRa;
    private final zzi zzRb;
    private long zzRc;
    private final zzt zzRd;
    private final zzt zzRe;
    private final zzaj zzRf;
    private long zzRg;
    private boolean zzRh;
    
    protected zzl(final zzf zzf, final zzg zzg) {
        super(zzf);
        zzx.zzz(zzg);
        this.zzRc = Long.MIN_VALUE;
        this.zzRa = zzg.zzk(zzf);
        this.zzQY = zzg.zzm(zzf);
        this.zzQZ = zzg.zzn(zzf);
        this.zzRb = zzg.zzo(zzf);
        this.zzRf = new zzaj(this.zzjl());
        this.zzRd = new zzt(zzf) {
            @Override
            public void run() {
                zzl.this.zzjV();
            }
        };
        this.zzRe = new zzt(zzf) {
            @Override
            public void run() {
                zzl.this.zzjW();
            }
        };
    }
    
    private void zza(final zzh zzh, final zzpr zzpr) {
        zzx.zzz(zzh);
        zzx.zzz(zzpr);
        final zza zza = new zza(this.zzji());
        zza.zzaS(zzh.zzjE());
        zza.enableAdvertisingIdCollection(zzh.zzjF());
        final com.google.android.gms.measurement.zzc zziy = zza.zziy();
        final zzke zzke = zziy.zzf(zzke.class);
        zzke.zzaX("data");
        zzke.zzI(true);
        zziy.zzb(zzpr);
        final zzkd zzkd = zziy.zzf(zzkd.class);
        final zzpq zzpq = zziy.zzf(zzpq.class);
        for (final Map.Entry<String, String> entry : zzh.zzn().entrySet()) {
            final String s = entry.getKey();
            final String userId = entry.getValue();
            if ("an".equals(s)) {
                zzpq.setAppName(userId);
            }
            else if ("av".equals(s)) {
                zzpq.setAppVersion(userId);
            }
            else if ("aid".equals(s)) {
                zzpq.setAppId(userId);
            }
            else if ("aiid".equals(s)) {
                zzpq.setAppInstallerId(userId);
            }
            else if ("uid".equals(s)) {
                zzke.setUserId(userId);
            }
            else {
                zzkd.set(s, userId);
            }
        }
        this.zzb("Sending installation campaign to", zzh.zzjE(), zzpr);
        zziy.zzM(this.zzjq().zzlF());
        zziy.zzAy();
    }
    
    private boolean zzbk(final String s) {
        return this.getContext().checkCallingOrSelfPermission(s) == 0;
    }
    
    private void zzjT() {
        final Context context = this.zzji().getContext();
        if (!AnalyticsReceiver.zzY(context)) {
            this.zzbg("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        }
        else if (!AnalyticsService.zzZ(context)) {
            this.zzbh("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zzY(context)) {
            this.zzbg("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        else if (!CampaignTrackingService.zzZ(context)) {
            this.zzbg("CampaignTrackingService is not registered or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
    }
    
    private void zzjV() {
        this.zzb(new zzw() {
            @Override
            public void zzc(final Throwable t) {
                zzl.this.zzkb();
            }
        });
    }
    
    private void zzjW() {
        while (true) {
            try {
                this.zzQY.zzjN();
                this.zzkb();
                this.zzRe.zzt(this.zzjn().zzkT());
            }
            catch (SQLiteException ex) {
                this.zzd("Failed to delete stale hits", ex);
                continue;
            }
            break;
        }
    }
    
    private boolean zzkc() {
        return !this.zzRh && (!this.zzjn().zzkr() || this.zzjn().zzks()) && this.zzki() > 0L;
    }
    
    private void zzkd() {
        final zzv zzjp = this.zzjp();
        if (zzjp.zzlb() && !zzjp.zzbw()) {
            final long zzjO = this.zzjO();
            if (zzjO != 0L && Math.abs(this.zzjl().currentTimeMillis() - zzjO) <= this.zzjn().zzkB()) {
                this.zza("Dispatch alarm scheduled (ms)", this.zzjn().zzkA());
                zzjp.zzlc();
            }
        }
    }
    
    private void zzke() {
        this.zzkd();
        final long zzki = this.zzki();
        final long zzlH = this.zzjq().zzlH();
        long n;
        if (zzlH != 0L) {
            n = zzki - Math.abs(this.zzjl().currentTimeMillis() - zzlH);
            if (n <= 0L) {
                n = Math.min(this.zzjn().zzky(), zzki);
            }
        }
        else {
            n = Math.min(this.zzjn().zzky(), zzki);
        }
        this.zza("Dispatch scheduled (ms)", n);
        if (this.zzRd.zzbw()) {
            this.zzRd.zzu(Math.max(1L, n + this.zzRd.zzkY()));
            return;
        }
        this.zzRd.zzt(n);
    }
    
    private void zzkf() {
        this.zzkg();
        this.zzkh();
    }
    
    private void zzkg() {
        if (this.zzRd.zzbw()) {
            this.zzbd("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzRd.cancel();
    }
    
    private void zzkh() {
        final zzv zzjp = this.zzjp();
        if (zzjp.zzbw()) {
            zzjp.cancel();
        }
    }
    
    protected void onServiceConnected() {
        this.zzjk();
        if (!this.zzjn().zzkr()) {
            this.zzjY();
        }
    }
    
    void start() {
        this.zzjv();
        zzx.zza(!this.mStarted, (Object)"Analytics backend already started");
        this.mStarted = true;
        if (!this.zzjn().zzkr()) {
            this.zzjT();
        }
        this.zzjo().zzf(new Runnable() {
            @Override
            public void run() {
                zzl.this.zzjU();
            }
        });
    }
    
    public void zzJ(final boolean b) {
        this.zzkb();
    }
    
    public long zza(final zzh p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/google/android/gms/common/internal/zzx.zzz:(Ljava/lang/Object;)Ljava/lang/Object;
        //     4: pop            
        //     5: aload_0        
        //     6: invokevirtual   com/google/android/gms/analytics/internal/zzl.zzjv:()V
        //     9: aload_0        
        //    10: invokevirtual   com/google/android/gms/analytics/internal/zzl.zzjk:()V
        //    13: aload_0        
        //    14: getfield        com/google/android/gms/analytics/internal/zzl.zzQY:Lcom/google/android/gms/analytics/internal/zzj;
        //    17: invokevirtual   com/google/android/gms/analytics/internal/zzj.beginTransaction:()V
        //    20: aload_0        
        //    21: getfield        com/google/android/gms/analytics/internal/zzl.zzQY:Lcom/google/android/gms/analytics/internal/zzj;
        //    24: aload_1        
        //    25: invokevirtual   com/google/android/gms/analytics/internal/zzh.zzjD:()J
        //    28: aload_1        
        //    29: invokevirtual   com/google/android/gms/analytics/internal/zzh.getClientId:()Ljava/lang/String;
        //    32: invokevirtual   com/google/android/gms/analytics/internal/zzj.zza:(JLjava/lang/String;)V
        //    35: aload_0        
        //    36: getfield        com/google/android/gms/analytics/internal/zzl.zzQY:Lcom/google/android/gms/analytics/internal/zzj;
        //    39: aload_1        
        //    40: invokevirtual   com/google/android/gms/analytics/internal/zzh.zzjD:()J
        //    43: aload_1        
        //    44: invokevirtual   com/google/android/gms/analytics/internal/zzh.getClientId:()Ljava/lang/String;
        //    47: aload_1        
        //    48: invokevirtual   com/google/android/gms/analytics/internal/zzh.zzjE:()Ljava/lang/String;
        //    51: invokevirtual   com/google/android/gms/analytics/internal/zzj.zza:(JLjava/lang/String;Ljava/lang/String;)J
        //    54: lstore          8
        //    56: iload_2        
        //    57: ifne            91
        //    60: aload_1        
        //    61: lload           8
        //    63: invokevirtual   com/google/android/gms/analytics/internal/zzh.zzn:(J)V
        //    66: aload_0        
        //    67: getfield        com/google/android/gms/analytics/internal/zzl.zzQY:Lcom/google/android/gms/analytics/internal/zzj;
        //    70: aload_1        
        //    71: invokevirtual   com/google/android/gms/analytics/internal/zzj.zzb:(Lcom/google/android/gms/analytics/internal/zzh;)V
        //    74: aload_0        
        //    75: getfield        com/google/android/gms/analytics/internal/zzl.zzQY:Lcom/google/android/gms/analytics/internal/zzj;
        //    78: invokevirtual   com/google/android/gms/analytics/internal/zzj.setTransactionSuccessful:()V
        //    81: aload_0        
        //    82: getfield        com/google/android/gms/analytics/internal/zzl.zzQY:Lcom/google/android/gms/analytics/internal/zzj;
        //    85: invokevirtual   com/google/android/gms/analytics/internal/zzj.endTransaction:()V
        //    88: lload           8
        //    90: lreturn        
        //    91: lconst_1       
        //    92: lload           8
        //    94: ladd           
        //    95: lstore          11
        //    97: aload_1        
        //    98: lload           11
        //   100: invokevirtual   com/google/android/gms/analytics/internal/zzh.zzn:(J)V
        //   103: goto            66
        //   106: astore          6
        //   108: aload_0        
        //   109: ldc_w           "Failed to update Analytics property"
        //   112: aload           6
        //   114: invokevirtual   com/google/android/gms/analytics/internal/zzl.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   117: aload_0        
        //   118: getfield        com/google/android/gms/analytics/internal/zzl.zzQY:Lcom/google/android/gms/analytics/internal/zzj;
        //   121: invokevirtual   com/google/android/gms/analytics/internal/zzj.endTransaction:()V
        //   124: ldc2_w          -1
        //   127: lreturn        
        //   128: astore          7
        //   130: aload_0        
        //   131: ldc_w           "Failed to end transaction"
        //   134: aload           7
        //   136: invokevirtual   com/google/android/gms/analytics/internal/zzl.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   139: ldc2_w          -1
        //   142: lreturn        
        //   143: astore          10
        //   145: aload_0        
        //   146: ldc_w           "Failed to end transaction"
        //   149: aload           10
        //   151: invokevirtual   com/google/android/gms/analytics/internal/zzl.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   154: lload           8
        //   156: lreturn        
        //   157: astore          4
        //   159: aload_0        
        //   160: getfield        com/google/android/gms/analytics/internal/zzl.zzQY:Lcom/google/android/gms/analytics/internal/zzj;
        //   163: invokevirtual   com/google/android/gms/analytics/internal/zzj.endTransaction:()V
        //   166: aload           4
        //   168: athrow         
        //   169: astore          5
        //   171: aload_0        
        //   172: ldc_w           "Failed to end transaction"
        //   175: aload           5
        //   177: invokevirtual   com/google/android/gms/analytics/internal/zzl.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   180: goto            166
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  13     56     106    143    Landroid/database/sqlite/SQLiteException;
        //  13     56     157    183    Any
        //  60     66     106    143    Landroid/database/sqlite/SQLiteException;
        //  60     66     157    183    Any
        //  66     81     106    143    Landroid/database/sqlite/SQLiteException;
        //  66     81     157    183    Any
        //  81     88     143    157    Landroid/database/sqlite/SQLiteException;
        //  97     103    106    143    Landroid/database/sqlite/SQLiteException;
        //  97     103    157    183    Any
        //  108    117    157    183    Any
        //  117    124    128    143    Landroid/database/sqlite/SQLiteException;
        //  159    166    169    183    Landroid/database/sqlite/SQLiteException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void zza(final zzab zzab) {
        zzx.zzz(zzab);
        com.google.android.gms.measurement.zzg.zzjk();
        this.zzjv();
        if (this.zzRh) {
            this.zzbe("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        }
        else {
            this.zza("Delivering hit", zzab);
        }
        final zzab zzf = this.zzf(zzab);
        this.zzjX();
        if (this.zzRb.zzb(zzf)) {
            this.zzbe("Hit sent to the device AnalyticsService for delivery");
            return;
        }
        if (this.zzjn().zzkr()) {
            this.zzjm().zza(zzf, "Service unavailable on package side");
            return;
        }
        try {
            this.zzQY.zzc(zzf);
            this.zzkb();
        }
        catch (SQLiteException ex) {
            this.zze("Delivery failed to save hit to a database", ex);
            this.zzjm().zza(zzf, "deliver: failed to insert hit to database");
        }
    }
    
    public void zza(final zzw zzw, final long n) {
        com.google.android.gms.measurement.zzg.zzjk();
        this.zzjv();
        long abs = -1L;
        final long zzlH = this.zzjq().zzlH();
        if (zzlH != 0L) {
            abs = Math.abs(this.zzjl().currentTimeMillis() - zzlH);
        }
        this.zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", abs);
        if (!this.zzjn().zzkr()) {
            this.zzjX();
        }
        try {
            if (this.zzjZ()) {
                this.zzjo().zzf(new Runnable() {
                    @Override
                    public void run() {
                        zzl.this.zza(zzw, n);
                    }
                });
                return;
            }
            this.zzjq().zzlI();
            this.zzkb();
            if (zzw != null) {
                zzw.zzc(null);
            }
            if (this.zzRg != n) {
                this.zzRa.zzlA();
            }
        }
        catch (Throwable t) {
            this.zze("Local dispatch failed", t);
            this.zzjq().zzlI();
            this.zzkb();
            if (zzw != null) {
                zzw.zzc(t);
            }
        }
    }
    
    public void zzb(final zzw zzw) {
        this.zza(zzw, this.zzRg);
    }
    
    public void zzbl(final String s) {
        zzx.zzcM(s);
        this.zzjk();
        this.zzjj();
        final zzpr zza = zzam.zza(this.zzjm(), s);
        if (zza == null) {
            this.zzd("Parsing failed. Ignoring invalid campaign data", s);
        }
        else {
            final String zzlJ = this.zzjq().zzlJ();
            if (s.equals(zzlJ)) {
                this.zzbg("Ignoring duplicate install campaign");
                return;
            }
            if (!TextUtils.isEmpty((CharSequence)zzlJ)) {
                this.zzd("Ignoring multiple install campaigns. original, new", zzlJ, s);
                return;
            }
            this.zzjq().zzbp(s);
            if (this.zzjq().zzlG().zzv(this.zzjn().zzkW())) {
                this.zzd("Campaign received too late, ignoring", zza);
                return;
            }
            this.zzb("Received installation campaign", zza);
            final Iterator<zzh> iterator = this.zzQY.zzr(0L).iterator();
            while (iterator.hasNext()) {
                this.zza(iterator.next(), zza);
            }
        }
    }
    
    protected void zzc(final zzh zzh) {
        this.zzjk();
        this.zzb("Sending first hit to property", zzh.zzjE());
        if (!this.zzjq().zzlG().zzv(this.zzjn().zzkW())) {
            final String zzlJ = this.zzjq().zzlJ();
            if (!TextUtils.isEmpty((CharSequence)zzlJ)) {
                final zzpr zza = zzam.zza(this.zzjm(), zzlJ);
                this.zzb("Found relevant installation campaign", zza);
                this.zza(zzh, zza);
            }
        }
    }
    
    zzab zzf(final zzab zzab) {
        if (TextUtils.isEmpty((CharSequence)zzab.zzlv())) {
            final Pair<String, Long> zzlN = this.zzjq().zzlK().zzlN();
            if (zzlN != null) {
                final String string = zzlN.second + ":" + (String)zzlN.first;
                final HashMap<String, String> hashMap = new HashMap<String, String>(zzab.zzn());
                hashMap.put("_m", string);
                return zzab.zza(this, zzab, hashMap);
            }
        }
        return zzab;
    }
    
    @Override
    protected void zziJ() {
        this.zzQY.zza();
        this.zzQZ.zza();
        this.zzRb.zza();
    }
    
    public long zzjO() {
        com.google.android.gms.measurement.zzg.zzjk();
        this.zzjv();
        try {
            return this.zzQY.zzjO();
        }
        catch (SQLiteException ex) {
            this.zze("Failed to get min/max hit times from local store", ex);
            return 0L;
        }
    }
    
    protected void zzjU() {
        this.zzjv();
        this.zzjq().zzlF();
        if (!this.zzbk("android.permission.ACCESS_NETWORK_STATE")) {
            this.zzbh("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            this.zzkj();
        }
        if (!this.zzbk("android.permission.INTERNET")) {
            this.zzbh("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            this.zzkj();
        }
        if (AnalyticsService.zzZ(this.getContext())) {
            this.zzbd("AnalyticsService registered in the app manifest and enabled");
        }
        else if (this.zzjn().zzkr()) {
            this.zzbh("Device AnalyticsService not registered! Hits will not be delivered reliably.");
        }
        else {
            this.zzbg("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!this.zzRh && !this.zzjn().zzkr() && !this.zzQY.isEmpty()) {
            this.zzjX();
        }
        this.zzkb();
    }
    
    protected void zzjX() {
        if (!this.zzRh && this.zzjn().zzkt() && !this.zzRb.isConnected() && this.zzRf.zzv(this.zzjn().zzkO())) {
            this.zzRf.start();
            this.zzbd("Connecting to service");
            if (this.zzRb.connect()) {
                this.zzbd("Connected to service");
                this.zzRf.clear();
                this.onServiceConnected();
            }
        }
    }
    
    public void zzjY() {
        com.google.android.gms.measurement.zzg.zzjk();
        this.zzjv();
        this.zzjj();
        if (!this.zzjn().zzkt()) {
            this.zzbg("Service client disabled. Can't dispatch local hits to device AnalyticsService");
        }
        if (!this.zzRb.isConnected()) {
            this.zzbd("Service not connected");
        }
        else if (!this.zzQY.isEmpty()) {
            this.zzbd("Dispatching local hits to device AnalyticsService");
            while (true) {
                Label_0126: {
                    List<zzab> zzp;
                    try {
                        zzp = this.zzQY.zzp(this.zzjn().zzkC());
                        if (zzp.isEmpty()) {
                            this.zzkb();
                            return;
                        }
                        break Label_0126;
                    }
                    catch (SQLiteException ex) {
                        this.zze("Failed to read hits from store", ex);
                        this.zzkf();
                        return;
                    }
                    while (true) {
                        zzab zzab = null;
                        zzp.remove(zzab);
                        try {
                            this.zzQY.zzq(zzab.zzlq());
                            if (zzp.isEmpty()) {
                                break;
                            }
                            zzab = zzp.get(0);
                            if (!this.zzRb.zzb(zzab)) {
                                this.zzkb();
                                return;
                            }
                            continue;
                        }
                        catch (SQLiteException ex2) {
                            this.zze("Failed to remove hit that was send for delivery", ex2);
                            this.zzkf();
                        }
                    }
                }
            }
        }
    }
    
    protected boolean zzjZ() {
        int n = 1;
        com.google.android.gms.measurement.zzg.zzjk();
        this.zzjv();
        this.zzbd("Dispatching a batch of local hits");
        int n2;
        if (!this.zzRb.isConnected() && !this.zzjn().zzkr()) {
            n2 = n;
        }
        else {
            n2 = 0;
        }
        if (this.zzQZ.zzlB()) {
            n = 0;
        }
        if (n2 != 0 && n != 0) {
            this.zzbd("No network or service available. Will retry later");
            return false;
        }
        final long n3 = Math.max(this.zzjn().zzkC(), this.zzjn().zzkD());
        final ArrayList<Long> list = new ArrayList<Long>();
        long max = 0L;
    Label_0412_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        this.zzQY.beginTransaction();
                        list.clear();
                        List<zzab> zzp = null;
                        Label_0346: {
                            try {
                                zzp = this.zzQY.zzp(n3);
                                if (zzp.isEmpty()) {
                                    this.zzbd("Store is empty, nothing to dispatch");
                                    this.zzkf();
                                    try {
                                        this.zzQY.setTransactionSuccessful();
                                        this.zzQY.endTransaction();
                                        return false;
                                    }
                                    catch (SQLiteException ex) {
                                        this.zze("Failed to commit local dispatch transaction", ex);
                                        this.zzkf();
                                        return false;
                                    }
                                }
                                this.zza("Hits loaded from store. count", zzp.size());
                                final Iterator<zzab> iterator = zzp.iterator();
                                Block_20: {
                                    while (iterator.hasNext()) {
                                        if (iterator.next().zzlq() == max) {
                                            break Block_20;
                                        }
                                    }
                                    break Label_0346;
                                }
                                this.zzd("Database contains successfully uploaded hit", max, zzp.size());
                                this.zzkf();
                                try {
                                    this.zzQY.setTransactionSuccessful();
                                    this.zzQY.endTransaction();
                                    return false;
                                }
                                catch (SQLiteException ex2) {
                                    this.zze("Failed to commit local dispatch transaction", ex2);
                                    this.zzkf();
                                    return false;
                                }
                            }
                            catch (SQLiteException ex3) {
                                this.zzd("Failed to read hits from persisted store", ex3);
                                this.zzkf();
                                try {
                                    this.zzQY.setTransactionSuccessful();
                                    this.zzQY.endTransaction();
                                    return false;
                                }
                                catch (SQLiteException ex4) {
                                    this.zze("Failed to commit local dispatch transaction", ex4);
                                    this.zzkf();
                                    return false;
                                }
                            }
                        }
                        if (!this.zzRb.isConnected() || this.zzjn().zzkr()) {
                            break;
                        }
                        this.zzbd("Service connected, sending hits to the service");
                        long n4 = 0L;
                        Label_0622: {
                            final List<Long> zzq;
                            long max2 = 0L;
                            Label_0589: {
                                Block_10: {
                                    while (!zzp.isEmpty()) {
                                        final zzab zzab = zzp.get(0);
                                        if (!this.zzRb.zzb(zzab)) {
                                            break Block_10;
                                        }
                                        max = Math.max(max, zzab.zzlq());
                                        zzp.remove(zzab);
                                        this.zzb("Hit sent do device AnalyticsService for delivery", zzab);
                                        try {
                                            this.zzQY.zzq(zzab.zzlq());
                                            list.add(zzab.zzlq());
                                            continue Label_0412_Outer;
                                        }
                                        catch (SQLiteException ex5) {
                                            this.zze("Failed to remove hit that was send for delivery", ex5);
                                            this.zzkf();
                                            try {
                                                this.zzQY.setTransactionSuccessful();
                                                this.zzQY.endTransaction();
                                                return false;
                                            }
                                            catch (SQLiteException ex6) {
                                                this.zze("Failed to commit local dispatch transaction", ex6);
                                                this.zzkf();
                                                return false;
                                            }
                                        }
                                        break Label_0589;
                                    }
                                    break;
                                }
                                n4 = max;
                                if (!this.zzQZ.zzlB()) {
                                    break Label_0622;
                                }
                                zzq = this.zzQZ.zzq(zzp);
                                final Iterator<Long> iterator2 = zzq.iterator();
                                max2 = n4;
                                while (iterator2.hasNext()) {
                                    max2 = Math.max(max2, iterator2.next());
                                }
                            }
                            zzp.removeAll(zzq);
                            try {
                                this.zzQY.zzo(zzq);
                                list.addAll((Collection<?>)zzq);
                                n4 = max2;
                                if (list.isEmpty()) {
                                    try {
                                        this.zzQY.setTransactionSuccessful();
                                        this.zzQY.endTransaction();
                                        return false;
                                    }
                                    catch (SQLiteException ex7) {
                                        this.zze("Failed to commit local dispatch transaction", ex7);
                                        this.zzkf();
                                        return false;
                                    }
                                }
                            }
                            catch (SQLiteException ex8) {
                                this.zze("Failed to remove successfully uploaded hits", ex8);
                                this.zzkf();
                                try {
                                    this.zzQY.setTransactionSuccessful();
                                    this.zzQY.endTransaction();
                                    return false;
                                }
                                catch (SQLiteException ex9) {
                                    this.zze("Failed to commit local dispatch transaction", ex9);
                                    this.zzkf();
                                    return false;
                                }
                            }
                        }
                        try {
                            this.zzQY.setTransactionSuccessful();
                            this.zzQY.endTransaction();
                            max = n4;
                        }
                        catch (SQLiteException ex10) {
                            this.zze("Failed to commit local dispatch transaction", ex10);
                            this.zzkf();
                            return false;
                        }
                    }
                }
                finally {
                    try {
                        this.zzQY.setTransactionSuccessful();
                        this.zzQY.endTransaction();
                    }
                    catch (SQLiteException ex11) {
                        this.zze("Failed to commit local dispatch transaction", ex11);
                        this.zzkf();
                        return false;
                    }
                }
                long n4 = max;
                continue;
            }
        }
    }
    
    public void zzjc() {
        com.google.android.gms.measurement.zzg.zzjk();
        this.zzjv();
        while (true) {
            if (this.zzjn().zzkr()) {
                break Label_0042;
            }
            this.zzbd("Delete all hits from local store");
            try {
                this.zzQY.zzjL();
                this.zzQY.zzjM();
                this.zzkb();
                this.zzjX();
                if (this.zzRb.zzjH()) {
                    this.zzbd("Device service unavailable. Can't clear hits stored on the device service.");
                }
            }
            catch (SQLiteException ex) {
                this.zzd("Failed to delete hits from store", ex);
                continue;
            }
            break;
        }
    }
    
    public void zzjf() {
        com.google.android.gms.measurement.zzg.zzjk();
        this.zzjv();
        this.zzbd("Service disconnected");
    }
    
    void zzjh() {
        this.zzjk();
        this.zzRg = this.zzjl().currentTimeMillis();
    }
    
    public void zzka() {
        com.google.android.gms.measurement.zzg.zzjk();
        this.zzjv();
        this.zzbe("Sync dispatching local hits");
        final long zzRg = this.zzRg;
        if (!this.zzjn().zzkr()) {
            this.zzjX();
        }
        try {
            while (this.zzjZ()) {}
            this.zzjq().zzlI();
            this.zzkb();
            if (this.zzRg != zzRg) {
                this.zzRa.zzlA();
            }
        }
        catch (Throwable t) {
            this.zze("Sync local dispatch failed", t);
            this.zzkb();
        }
    }
    
    public void zzkb() {
        this.zzji().zzjk();
        this.zzjv();
        if (!this.zzkc()) {
            this.zzRa.unregister();
            this.zzkf();
            return;
        }
        if (this.zzQY.isEmpty()) {
            this.zzRa.unregister();
            this.zzkf();
            return;
        }
        int connected;
        if (!zzy.zzSs.get()) {
            this.zzRa.zzly();
            connected = (this.zzRa.isConnected() ? 1 : 0);
        }
        else {
            connected = 1;
        }
        if (connected != 0) {
            this.zzke();
            return;
        }
        this.zzkf();
        this.zzkd();
    }
    
    public long zzki() {
        long n;
        if (this.zzRc != Long.MIN_VALUE) {
            n = this.zzRc;
        }
        else {
            n = this.zzjn().zzkz();
            if (this.zziI().zzll()) {
                return 1000L * this.zziI().zzmc();
            }
        }
        return n;
    }
    
    public void zzkj() {
        this.zzjv();
        this.zzjk();
        this.zzRh = true;
        this.zzRb.disconnect();
        this.zzkb();
    }
    
    public void zzs(long zzRc) {
        com.google.android.gms.measurement.zzg.zzjk();
        this.zzjv();
        if (zzRc < 0L) {
            zzRc = 0L;
        }
        this.zzRc = zzRc;
        this.zzkb();
    }
}
