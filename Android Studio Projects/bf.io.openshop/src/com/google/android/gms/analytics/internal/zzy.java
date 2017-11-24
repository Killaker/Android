package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;

public final class zzy
{
    public static zza<Boolean> zzRJ;
    public static zza<Boolean> zzRK;
    public static zza<String> zzRL;
    public static zza<Long> zzRM;
    public static zza<Float> zzRN;
    public static zza<Integer> zzRO;
    public static zza<Integer> zzRP;
    public static zza<Integer> zzRQ;
    public static zza<Long> zzRR;
    public static zza<Long> zzRS;
    public static zza<Long> zzRT;
    public static zza<Long> zzRU;
    public static zza<Long> zzRV;
    public static zza<Long> zzRW;
    public static zza<Integer> zzRX;
    public static zza<Integer> zzRY;
    public static zza<String> zzRZ;
    public static zza<String> zzSa;
    public static zza<String> zzSb;
    public static zza<String> zzSc;
    public static zza<Integer> zzSd;
    public static zza<String> zzSe;
    public static zza<String> zzSf;
    public static zza<Integer> zzSg;
    public static zza<Integer> zzSh;
    public static zza<Integer> zzSi;
    public static zza<Integer> zzSj;
    public static zza<String> zzSk;
    public static zza<Integer> zzSl;
    public static zza<Long> zzSm;
    public static zza<Integer> zzSn;
    public static zza<Integer> zzSo;
    public static zza<Long> zzSp;
    public static zza<String> zzSq;
    public static zza<Integer> zzSr;
    public static zza<Boolean> zzSs;
    public static zza<Long> zzSt;
    public static zza<Long> zzSu;
    public static zza<Long> zzSv;
    public static zza<Long> zzSw;
    public static zza<Long> zzSx;
    public static zza<Long> zzSy;
    public static zza<Long> zzSz;
    
    static {
        zzy.zzRJ = zza.zzg("analytics.service_enabled", false);
        zzy.zzRK = zza.zzg("analytics.service_client_enabled", true);
        zzy.zzRL = zza.zze("analytics.log_tag", "GAv4", "GAv4-SVC");
        zzy.zzRM = zza.zzb("analytics.max_tokens", 60L);
        zzy.zzRN = zza.zza("analytics.tokens_per_sec", 0.5f);
        zzy.zzRO = zza.zza("analytics.max_stored_hits", 2000, 20000);
        zzy.zzRP = zza.zzd("analytics.max_stored_hits_per_app", 2000);
        zzy.zzRQ = zza.zzd("analytics.max_stored_properties_per_app", 100);
        zzy.zzRR = zza.zza("analytics.local_dispatch_millis", 1800000L, 120000L);
        zzy.zzRS = zza.zza("analytics.initial_local_dispatch_millis", 5000L, 5000L);
        zzy.zzRT = zza.zzb("analytics.min_local_dispatch_millis", 120000L);
        zzy.zzRU = zza.zzb("analytics.max_local_dispatch_millis", 7200000L);
        zzy.zzRV = zza.zzb("analytics.dispatch_alarm_millis", 7200000L);
        zzy.zzRW = zza.zzb("analytics.max_dispatch_alarm_millis", 32400000L);
        zzy.zzRX = zza.zzd("analytics.max_hits_per_dispatch", 20);
        zzy.zzRY = zza.zzd("analytics.max_hits_per_batch", 20);
        zzy.zzRZ = zza.zzl("analytics.insecure_host", "http://www.google-analytics.com");
        zzy.zzSa = zza.zzl("analytics.secure_host", "https://ssl.google-analytics.com");
        zzy.zzSb = zza.zzl("analytics.simple_endpoint", "/collect");
        zzy.zzSc = zza.zzl("analytics.batching_endpoint", "/batch");
        zzy.zzSd = zza.zzd("analytics.max_get_length", 2036);
        zzy.zzSe = zza.zze("analytics.batching_strategy.k", zzm.zzRo.name(), zzm.zzRo.name());
        zzy.zzSf = zza.zzl("analytics.compression_strategy.k", zzo.zzRv.name());
        zzy.zzSg = zza.zzd("analytics.max_hits_per_request.k", 20);
        zzy.zzSh = zza.zzd("analytics.max_hit_length.k", 8192);
        zzy.zzSi = zza.zzd("analytics.max_post_length.k", 8192);
        zzy.zzSj = zza.zzd("analytics.max_batch_post_length", 8192);
        zzy.zzSk = zza.zzl("analytics.fallback_responses.k", "404,502");
        zzy.zzSl = zza.zzd("analytics.batch_retry_interval.seconds.k", 3600);
        zzy.zzSm = zza.zzb("analytics.service_monitor_interval", 86400000L);
        zzy.zzSn = zza.zzd("analytics.http_connection.connect_timeout_millis", 60000);
        zzy.zzSo = zza.zzd("analytics.http_connection.read_timeout_millis", 61000);
        zzy.zzSp = zza.zzb("analytics.campaigns.time_limit", 86400000L);
        zzy.zzSq = zza.zzl("analytics.first_party_experiment_id", "");
        zzy.zzSr = zza.zzd("analytics.first_party_experiment_variant", 0);
        zzy.zzSs = zza.zzg("analytics.test.disable_receiver", false);
        zzy.zzSt = zza.zza("analytics.service_client.idle_disconnect_millis", 10000L, 10000L);
        zzy.zzSu = zza.zzb("analytics.service_client.connect_timeout_millis", 5000L);
        zzy.zzSv = zza.zzb("analytics.service_client.second_connect_delay_millis", 5000L);
        zzy.zzSw = zza.zzb("analytics.service_client.unexpected_reconnect_millis", 60000L);
        zzy.zzSx = zza.zzb("analytics.service_client.reconnect_throttle_millis", 1800000L);
        zzy.zzSy = zza.zzb("analytics.monitoring.sample_period_millis", 86400000L);
        zzy.zzSz = zza.zzb("analytics.initialization_warning_threshold", 5000L);
    }
    
    public static final class zza<V>
    {
        private final V zzSA;
        private final zzlz<V> zzSB;
        private V zzSC;
        
        private zza(final zzlz<V> zzSB, final V zzSA) {
            zzx.zzz(zzSB);
            this.zzSB = zzSB;
            this.zzSA = zzSA;
        }
        
        static zza<Float> zza(final String s, final float n) {
            return zza(s, n, n);
        }
        
        static zza<Float> zza(final String s, final float n, final float n2) {
            return new zza<Float>(zzlz.zza(s, Float.valueOf(n2)), n);
        }
        
        static zza<Integer> zza(final String s, final int n, final int n2) {
            return new zza<Integer>(zzlz.zza(s, Integer.valueOf(n2)), n);
        }
        
        static zza<Long> zza(final String s, final long n, final long n2) {
            return new zza<Long>(zzlz.zza(s, Long.valueOf(n2)), n);
        }
        
        static zza<Boolean> zza(final String s, final boolean b, final boolean b2) {
            return new zza<Boolean>(zzlz.zzk(s, b2), b);
        }
        
        static zza<Long> zzb(final String s, final long n) {
            return zza(s, n, n);
        }
        
        static zza<Integer> zzd(final String s, final int n) {
            return zza(s, n, n);
        }
        
        static zza<String> zze(final String s, final String s2, final String s3) {
            return new zza<String>(zzlz.zzv(s, s3), s2);
        }
        
        static zza<Boolean> zzg(final String s, final boolean b) {
            return zza(s, b, b);
        }
        
        static zza<String> zzl(final String s, final String s2) {
            return zze(s, s2, s2);
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
    }
}
