package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;

public final class zzl
{
    public static zza<Boolean> zzaVY;
    public static zza<Boolean> zzaVZ;
    public static zza<String> zzaWa;
    public static zza<Long> zzaWb;
    public static zza<Long> zzaWc;
    public static zza<Long> zzaWd;
    public static zza<String> zzaWe;
    public static zza<String> zzaWf;
    public static zza<Integer> zzaWg;
    public static zza<Integer> zzaWh;
    public static zza<Integer> zzaWi;
    public static zza<Integer> zzaWj;
    public static zza<Integer> zzaWk;
    public static zza<Integer> zzaWl;
    public static zza<Integer> zzaWm;
    public static zza<Integer> zzaWn;
    public static zza<String> zzaWo;
    public static zza<Long> zzaWp;
    public static zza<Long> zzaWq;
    public static zza<Long> zzaWr;
    public static zza<Long> zzaWs;
    public static zza<Long> zzaWt;
    public static zza<Long> zzaWu;
    public static zza<Integer> zzaWv;
    public static zza<Long> zzaWw;
    public static zza<Integer> zzaWx;
    public static zza<Long> zzaWy;
    
    static {
        zzl.zzaVY = zza.zzm("measurement.service_enabled", true);
        zzl.zzaVZ = zza.zzm("measurement.service_client_enabled", true);
        zzl.zzaWa = zza.zzl("measurement.log_tag", "GMPM", "GMPM-SVC");
        zzl.zzaWb = zza.zze("measurement.ad_id_cache_time", 10000L);
        zzl.zzaWc = zza.zze("measurement.monitoring.sample_period_millis", 86400000L);
        zzl.zzaWd = zza.zze("measurement.config.cache_time", 86400000L);
        zzl.zzaWe = zza.zzN("measurement.config.url_scheme", "https");
        zzl.zzaWf = zza.zzN("measurement.config.url_authority", "app-measurement.com");
        zzl.zzaWg = zza.zzD("measurement.upload.max_bundles", 100);
        zzl.zzaWh = zza.zzD("measurement.upload.max_batch_size", 65536);
        zzl.zzaWi = zza.zzD("measurement.upload.max_bundle_size", 65536);
        zzl.zzaWj = zza.zzD("measurement.upload.max_events_per_bundle", 1000);
        zzl.zzaWk = zza.zzD("measurement.upload.max_events_per_day", 100000);
        zzl.zzaWl = zza.zzD("measurement.upload.max_public_events_per_day", 50000);
        zzl.zzaWm = zza.zzD("measurement.upload.max_conversions_per_day", 500);
        zzl.zzaWn = zza.zzD("measurement.store.max_stored_events_per_app", 100000);
        zzl.zzaWo = zza.zzN("measurement.upload.url", "https://app-measurement.com/a");
        zzl.zzaWp = zza.zze("measurement.upload.backoff_period", 43200000L);
        zzl.zzaWq = zza.zze("measurement.upload.window_interval", 3600000L);
        zzl.zzaWr = zza.zze("measurement.upload.interval", 3600000L);
        zzl.zzaWs = zza.zze("measurement.upload.stale_data_deletion_interval", 86400000L);
        zzl.zzaWt = zza.zze("measurement.upload.initial_upload_delay_time", 15000L);
        zzl.zzaWu = zza.zze("measurement.upload.retry_time", 1800000L);
        zzl.zzaWv = zza.zzD("measurement.upload.retry_count", 6);
        zzl.zzaWw = zza.zze("measurement.upload.max_queue_time", 2419200000L);
        zzl.zzaWx = zza.zzD("measurement.lifetimevalue.max_currency_tracked", 4);
        zzl.zzaWy = zza.zze("measurement.service_client.idle_disconnect_millis", 5000L);
    }
    
    public static final class zza<V>
    {
        private final V zzSA;
        private final zzlz<V> zzSB;
        private V zzSC;
        private final String zzvs;
        
        private zza(final String zzvs, final zzlz<V> zzSB, final V zzSA) {
            zzx.zzz(zzSB);
            this.zzSB = zzSB;
            this.zzSA = zzSA;
            this.zzvs = zzvs;
        }
        
        static zza<Integer> zzD(final String s, final int n) {
            return zzo(s, n, n);
        }
        
        static zza<String> zzN(final String s, final String s2) {
            return zzl(s, s2, s2);
        }
        
        static zza<Long> zzb(final String s, final long n, final long n2) {
            return new zza<Long>(s, zzlz.zza(s, Long.valueOf(n2)), n);
        }
        
        static zza<Boolean> zzb(final String s, final boolean b, final boolean b2) {
            return new zza<Boolean>(s, zzlz.zzk(s, b2), b);
        }
        
        static zza<Long> zze(final String s, final long n) {
            return zzb(s, n, n);
        }
        
        static zza<String> zzl(final String s, final String s2, final String s3) {
            return new zza<String>(s, zzlz.zzv(s, s3), s2);
        }
        
        static zza<Boolean> zzm(final String s, final boolean b) {
            return zzb(s, b, b);
        }
        
        static zza<Integer> zzo(final String s, final int n, final int n2) {
            return new zza<Integer>(s, zzlz.zza(s, Integer.valueOf(n2)), n);
        }
        
        public V get() {
            if (this.zzSC != null) {
                return this.zzSC;
            }
            if (zzd.zzakE && zzlz.isInitialized()) {
                return this.zzSB.zzpX();
            }
            return this.zzSA;
        }
        
        public V get(V zzSC) {
            if (this.zzSC != null) {
                zzSC = this.zzSC;
            }
            else if (zzSC == null) {
                if (zzd.zzakE && zzlz.isInitialized()) {
                    return this.zzSB.zzpX();
                }
                return this.zzSA;
            }
            return zzSC;
        }
        
        public String getKey() {
            return this.zzvs;
        }
    }
}
