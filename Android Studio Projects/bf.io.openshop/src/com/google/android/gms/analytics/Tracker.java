package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.*;
import android.app.*;
import android.text.*;
import android.content.*;
import java.util.*;
import com.google.android.gms.analytics.internal.*;
import com.google.android.gms.internal.*;
import android.net.*;

public class Tracker extends zzd
{
    private boolean zzPs;
    private final Map<String, String> zzPt;
    private final zzad zzPu;
    private final zza zzPv;
    private ExceptionReporter zzPw;
    private zzal zzPx;
    private final Map<String, String> zzxA;
    
    Tracker(final zzf zzf, final String s, final zzad zzPu) {
        super(zzf);
        this.zzxA = new HashMap<String, String>();
        this.zzPt = new HashMap<String, String>();
        if (s != null) {
            this.zzxA.put("&tid", s);
        }
        this.zzxA.put("useSecure", "1");
        this.zzxA.put("&a", Integer.toString(1 + new Random().nextInt(Integer.MAX_VALUE)));
        if (zzPu == null) {
            this.zzPu = new zzad("tracking", this.zzjl());
        }
        else {
            this.zzPu = zzPu;
        }
        this.zzPv = new zza(zzf);
    }
    
    private static boolean zza(final Map.Entry<String, String> entry) {
        final String s = entry.getKey();
        final String s2 = entry.getValue();
        return s.startsWith("&") && s.length() >= 2;
    }
    
    private static String zzb(final Map.Entry<String, String> entry) {
        if (!zza(entry)) {
            return null;
        }
        return entry.getKey().substring(1);
    }
    
    private static void zzb(final Map<String, String> map, final Map<String, String> map2) {
        zzx.zzz(map2);
        if (map != null) {
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                final String zzb = zzb(entry);
                if (zzb != null) {
                    map2.put(zzb, entry.getValue());
                }
            }
        }
    }
    
    private static void zzc(final Map<String, String> map, final Map<String, String> map2) {
        zzx.zzz(map2);
        if (map != null) {
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                final String zzb = zzb(entry);
                if (zzb != null && !map2.containsKey(zzb)) {
                    map2.put(zzb, entry.getValue());
                }
            }
        }
    }
    
    private boolean zziK() {
        return this.zzPw != null;
    }
    
    static String zzn(final Activity activity) {
        zzx.zzz(activity);
        final Intent intent = activity.getIntent();
        if (intent != null) {
            final String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
            if (!TextUtils.isEmpty((CharSequence)stringExtra)) {
                return stringExtra;
            }
        }
        return null;
    }
    
    public void enableAdvertisingIdCollection(final boolean zzPs) {
        this.zzPs = zzPs;
    }
    
    public void enableAutoActivityTracking(final boolean b) {
        this.zzPv.enableAutoActivityTracking(b);
    }
    
    public void enableExceptionReporting(final boolean b) {
        while (true) {
            synchronized (this) {
                if (this.zziK() == b) {
                    return;
                }
                if (b) {
                    Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)(this.zzPw = new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), this.getContext())));
                    this.zzbd("Uncaught exceptions will be reported to Google Analytics");
                    return;
                }
            }
            Thread.setDefaultUncaughtExceptionHandler(this.zzPw.zziD());
            this.zzbd("Uncaught exceptions will not be reported to Google Analytics");
        }
    }
    
    public String get(final String s) {
        this.zzjv();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            if (this.zzxA.containsKey(s)) {
                return this.zzxA.get(s);
            }
            if (s.equals("&ul")) {
                return zzam.zza(Locale.getDefault());
            }
            if (s.equals("&cid")) {
                return this.zzjr().zzkk();
            }
            if (s.equals("&sr")) {
                return this.zzju().zzla();
            }
            if (s.equals("&aid")) {
                return this.zzjt().zzjS().zzwK();
            }
            if (s.equals("&an")) {
                return this.zzjt().zzjS().zzlg();
            }
            if (s.equals("&av")) {
                return this.zzjt().zzjS().zzli();
            }
            if (s.equals("&aiid")) {
                return this.zzjt().zzjS().zzAJ();
            }
        }
        return null;
    }
    
    public void send(final Map<String, String> map) {
        final long currentTimeMillis = this.zzjl().currentTimeMillis();
        if (this.zziC().getAppOptOut()) {
            this.zzbe("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        final boolean dryRunEnabled = this.zziC().isDryRunEnabled();
        final HashMap<Object, String> hashMap = new HashMap<Object, String>();
        zzb(this.zzxA, (Map<String, String>)hashMap);
        zzb(map, (Map<String, String>)hashMap);
        final boolean zzh = zzam.zzh(this.zzxA.get("useSecure"), true);
        zzc(this.zzPt, (Map<String, String>)hashMap);
        this.zzPt.clear();
        final String s = hashMap.get("t");
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.zzjm().zzh((Map<String, String>)hashMap, "Missing hit type parameter");
            return;
        }
        final String s2 = hashMap.get("tid");
        if (TextUtils.isEmpty((CharSequence)s2)) {
            this.zzjm().zzh((Map<String, String>)hashMap, "Missing tracking id parameter");
            return;
        }
        final boolean zziL = this.zziL();
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(s) || "pageview".equalsIgnoreCase(s) || "appview".equalsIgnoreCase(s) || TextUtils.isEmpty((CharSequence)s)) {
                int n = 1 + Integer.parseInt(this.zzxA.get("&a"));
                if (n >= Integer.MAX_VALUE) {
                    n = 1;
                }
                this.zzxA.put("&a", Integer.toString(n));
            }
            // monitorexit(this)
            this.zzjo().zzf(new Runnable() {
                @Override
                public void run() {
                    boolean b = true;
                    if (Tracker.this.zzPv.zziM()) {
                        hashMap.put("sc", "start");
                    }
                    zzam.zzd(hashMap, "cid", Tracker.this.zziC().getClientId());
                    final String s = hashMap.get("sf");
                    if (s != null) {
                        final double zza = zzam.zza(s, 100.0);
                        if (zzam.zza(zza, hashMap.get("cid"))) {
                            Tracker.this.zzb("Sampling enabled. Hit sampled out. sample rate", zza);
                            return;
                        }
                    }
                    final com.google.android.gms.analytics.internal.zza zzb = Tracker.this.zzjs();
                    if (zziL) {
                        zzam.zzb(hashMap, "ate", zzb.zziU());
                        zzam.zzc(hashMap, "adid", zzb.zziY());
                    }
                    else {
                        hashMap.remove("ate");
                        hashMap.remove("adid");
                    }
                    final zzpq zzjS = Tracker.this.zzjt().zzjS();
                    zzam.zzc(hashMap, "an", zzjS.zzlg());
                    zzam.zzc(hashMap, "av", zzjS.zzli());
                    zzam.zzc(hashMap, "aid", zzjS.zzwK());
                    zzam.zzc(hashMap, "aiid", zzjS.zzAJ());
                    hashMap.put("v", "1");
                    hashMap.put("_v", zze.zzQm);
                    zzam.zzc(hashMap, "ul", Tracker.this.zzju().zzkZ().getLanguage());
                    zzam.zzc(hashMap, "sr", Tracker.this.zzju().zzla());
                    if (((!s.equals("transaction") && !s.equals("item")) || !b) && !Tracker.this.zzPu.zzlw()) {
                        Tracker.this.zzjm().zzh(hashMap, "Too many hits sent too quickly, rate limiting invoked");
                        return;
                    }
                    long n = zzam.zzbt(hashMap.get("ht"));
                    if (n == 0L) {
                        n = currentTimeMillis;
                    }
                    if (dryRunEnabled) {
                        Tracker.this.zzjm().zzc("Dry run enabled. Would have sent hit", new zzab(Tracker.this, hashMap, n, zzh));
                        return;
                    }
                    final String s2 = hashMap.get("cid");
                    final HashMap<String, String> hashMap = new HashMap<String, String>();
                    zzam.zza(hashMap, "uid", hashMap);
                    zzam.zza(hashMap, "an", hashMap);
                    zzam.zza(hashMap, "aid", hashMap);
                    zzam.zza(hashMap, "av", hashMap);
                    zzam.zza(hashMap, "aiid", hashMap);
                    final String zzPE = s2;
                    if (TextUtils.isEmpty((CharSequence)hashMap.get("adid"))) {
                        b = false;
                    }
                    hashMap.put("_s", String.valueOf(Tracker.this.zziH().zza(new zzh(0L, s2, zzPE, b, 0L, hashMap))));
                    Tracker.this.zziH().zza(new zzab(Tracker.this, hashMap, n, zzh));
                }
            });
        }
    }
    
    public void set(final String s, final String s2) {
        zzx.zzb(s, "Key should be non-null");
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.zzxA.put(s, s2);
    }
    
    public void setAnonymizeIp(final boolean b) {
        this.set("&aip", zzam.zzK(b));
    }
    
    public void setAppId(final String s) {
        this.set("&aid", s);
    }
    
    public void setAppInstallerId(final String s) {
        this.set("&aiid", s);
    }
    
    public void setAppName(final String s) {
        this.set("&an", s);
    }
    
    public void setAppVersion(final String s) {
        this.set("&av", s);
    }
    
    public void setCampaignParamsOnNextHit(final Uri uri) {
        if (uri != null && !uri.isOpaque()) {
            final String queryParameter = uri.getQueryParameter("referrer");
            if (!TextUtils.isEmpty((CharSequence)queryParameter)) {
                final Uri parse = Uri.parse("http://hostname/?" + queryParameter);
                final String queryParameter2 = parse.getQueryParameter("utm_id");
                if (queryParameter2 != null) {
                    this.zzPt.put("&ci", queryParameter2);
                }
                final String queryParameter3 = parse.getQueryParameter("anid");
                if (queryParameter3 != null) {
                    this.zzPt.put("&anid", queryParameter3);
                }
                final String queryParameter4 = parse.getQueryParameter("utm_campaign");
                if (queryParameter4 != null) {
                    this.zzPt.put("&cn", queryParameter4);
                }
                final String queryParameter5 = parse.getQueryParameter("utm_content");
                if (queryParameter5 != null) {
                    this.zzPt.put("&cc", queryParameter5);
                }
                final String queryParameter6 = parse.getQueryParameter("utm_medium");
                if (queryParameter6 != null) {
                    this.zzPt.put("&cm", queryParameter6);
                }
                final String queryParameter7 = parse.getQueryParameter("utm_source");
                if (queryParameter7 != null) {
                    this.zzPt.put("&cs", queryParameter7);
                }
                final String queryParameter8 = parse.getQueryParameter("utm_term");
                if (queryParameter8 != null) {
                    this.zzPt.put("&ck", queryParameter8);
                }
                final String queryParameter9 = parse.getQueryParameter("dclid");
                if (queryParameter9 != null) {
                    this.zzPt.put("&dclid", queryParameter9);
                }
                final String queryParameter10 = parse.getQueryParameter("gclid");
                if (queryParameter10 != null) {
                    this.zzPt.put("&gclid", queryParameter10);
                }
                final String queryParameter11 = parse.getQueryParameter("aclid");
                if (queryParameter11 != null) {
                    this.zzPt.put("&aclid", queryParameter11);
                }
            }
        }
    }
    
    public void setClientId(final String s) {
        this.set("&cid", s);
    }
    
    public void setEncoding(final String s) {
        this.set("&de", s);
    }
    
    public void setHostname(final String s) {
        this.set("&dh", s);
    }
    
    public void setLanguage(final String s) {
        this.set("&ul", s);
    }
    
    public void setLocation(final String s) {
        this.set("&dl", s);
    }
    
    public void setPage(final String s) {
        this.set("&dp", s);
    }
    
    public void setReferrer(final String s) {
        this.set("&dr", s);
    }
    
    public void setSampleRate(final double n) {
        this.set("&sf", Double.toString(n));
    }
    
    public void setScreenColors(final String s) {
        this.set("&sd", s);
    }
    
    public void setScreenName(final String s) {
        this.set("&cd", s);
    }
    
    public void setScreenResolution(final int n, final int n2) {
        if (n < 0 && n2 < 0) {
            this.zzbg("Invalid width or height. The values should be non-negative.");
            return;
        }
        this.set("&sr", n + "x" + n2);
    }
    
    public void setSessionTimeout(final long n) {
        this.zzPv.setSessionTimeout(1000L * n);
    }
    
    public void setTitle(final String s) {
        this.set("&dt", s);
    }
    
    public void setUseSecure(final boolean b) {
        this.set("useSecure", zzam.zzK(b));
    }
    
    public void setViewportSize(final String s) {
        this.set("&vp", s);
    }
    
    void zza(final zzal zzPx) {
        this.zzbd("Loading Tracker config values");
        this.zzPx = zzPx;
        if (this.zzPx.zzlT()) {
            final String trackingId = this.zzPx.getTrackingId();
            this.set("&tid", trackingId);
            this.zza("trackingId loaded", trackingId);
        }
        if (this.zzPx.zzlU()) {
            final String string = Double.toString(this.zzPx.zzlV());
            this.set("&sf", string);
            this.zza("Sample frequency loaded", string);
        }
        if (this.zzPx.zzlW()) {
            final int sessionTimeout = this.zzPx.getSessionTimeout();
            this.setSessionTimeout(sessionTimeout);
            this.zza("Session timeout loaded", sessionTimeout);
        }
        if (this.zzPx.zzlX()) {
            final boolean zzlY = this.zzPx.zzlY();
            this.enableAutoActivityTracking(zzlY);
            this.zza("Auto activity tracking loaded", zzlY);
        }
        if (this.zzPx.zzlZ()) {
            final boolean zzma = this.zzPx.zzma();
            if (zzma) {
                this.set("&aip", "1");
            }
            this.zza("Anonymize ip loaded", zzma);
        }
        this.enableExceptionReporting(this.zzPx.zzmb());
    }
    
    @Override
    protected void zziJ() {
        this.zzPv.zza();
        final String zzlg = this.zziI().zzlg();
        if (zzlg != null) {
            this.set("&an", zzlg);
        }
        final String zzli = this.zziI().zzli();
        if (zzli != null) {
            this.set("&av", zzli);
        }
    }
    
    boolean zziL() {
        return this.zzPs;
    }
    
    private class zza extends zzd implements GoogleAnalytics.zza
    {
        private boolean zzPG;
        private int zzPH;
        private long zzPI;
        private boolean zzPJ;
        private long zzPK;
        
        protected zza(final com.google.android.gms.analytics.internal.zzf zzf) {
            super(zzf);
            this.zzPI = -1L;
        }
        
        private void zziN() {
            if (this.zzPI >= 0L || this.zzPG) {
                this.zziC().zza((GoogleAnalytics.zza)Tracker.this.zzPv);
                return;
            }
            this.zziC().zzb((GoogleAnalytics.zza)Tracker.this.zzPv);
        }
        
        public void enableAutoActivityTracking(final boolean zzPG) {
            this.zzPG = zzPG;
            this.zziN();
        }
        
        public void setSessionTimeout(final long zzPI) {
            this.zzPI = zzPI;
            this.zziN();
        }
        
        @Override
        protected void zziJ() {
        }
        
        public boolean zziM() {
            synchronized (this) {
                final boolean zzPJ = this.zzPJ;
                this.zzPJ = false;
                return zzPJ;
            }
        }
        
        boolean zziO() {
            return this.zzjl().elapsedRealtime() >= this.zzPK + Math.max(1000L, this.zzPI);
        }
        
        @Override
        public void zzl(final Activity activity) {
            if (this.zzPH == 0 && this.zziO()) {
                this.zzPJ = true;
            }
            ++this.zzPH;
            if (this.zzPG) {
                final Intent intent = activity.getIntent();
                if (intent != null) {
                    Tracker.this.setCampaignParamsOnNextHit(intent.getData());
                }
                final HashMap<Object, CharSequence> hashMap = new HashMap<Object, CharSequence>();
                hashMap.put("&t", "screenview");
                final Tracker zzPF = Tracker.this;
                String s;
                if (Tracker.this.zzPx != null) {
                    s = Tracker.this.zzPx.zzo(activity);
                }
                else {
                    s = activity.getClass().getCanonicalName();
                }
                zzPF.set("&cd", s);
                if (TextUtils.isEmpty((CharSequence)hashMap.get("&dr"))) {
                    final String zzn = Tracker.zzn(activity);
                    if (!TextUtils.isEmpty((CharSequence)zzn)) {
                        hashMap.put("&dr", zzn);
                    }
                }
                Tracker.this.send((Map<String, String>)hashMap);
            }
        }
        
        @Override
        public void zzm(final Activity activity) {
            --this.zzPH;
            this.zzPH = Math.max(0, this.zzPH);
            if (this.zzPH == 0) {
                this.zzPK = this.zzjl().elapsedRealtime();
            }
        }
    }
}
