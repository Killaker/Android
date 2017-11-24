package com.google.android.gms.clearcut;

import android.content.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import android.util.*;
import android.content.pm.*;
import java.util.concurrent.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.playlog.internal.*;
import java.util.*;

public final class zzb
{
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.zzc<zzlw> zzUI;
    public static final Api.zza<zzlw, Api.ApiOptions.NoOptions> zzUJ;
    public static final com.google.android.gms.clearcut.zzc zzaeQ;
    private final Context mContext;
    private final String zzTJ;
    private final int zzaeR;
    private String zzaeS;
    private int zzaeT;
    private String zzaeU;
    private String zzaeV;
    private final boolean zzaeW;
    private int zzaeX;
    private final com.google.android.gms.clearcut.zzc zzaeY;
    private final com.google.android.gms.clearcut.zza zzaeZ;
    private zzc zzafa;
    private final zzmq zzqW;
    
    static {
        zzUI = new Api.zzc();
        zzUJ = new Api.zza<zzlw, Api.ApiOptions.NoOptions>() {
            public zzlw zze(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzf zzf, final NoOptions noOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new zzlw(context, looper, zzf, connectionCallbacks, onConnectionFailedListener);
            }
        };
        API = new Api<Api.ApiOptions.NoOptions>("ClearcutLogger.API", (Api.zza<C, Api.ApiOptions.NoOptions>)zzb.zzUJ, (Api.zzc<C>)zzb.zzUI);
        zzaeQ = new zzlv();
    }
    
    public zzb(final Context context, final int zzaeT, final String zzaeS, final String zzaeU, final String zzaeV, final boolean zzaeW, final com.google.android.gms.clearcut.zzc zzaeY, final zzmq zzqW, zzc zzafa, final com.google.android.gms.clearcut.zza zzaeZ) {
        this.zzaeT = -1;
        this.zzaeX = 0;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            applicationContext = context;
        }
        this.mContext = applicationContext;
        this.zzTJ = context.getPackageName();
        this.zzaeR = this.zzai(context);
        this.zzaeT = zzaeT;
        this.zzaeS = zzaeS;
        this.zzaeU = zzaeU;
        this.zzaeV = zzaeV;
        this.zzaeW = zzaeW;
        this.zzaeY = zzaeY;
        this.zzqW = zzqW;
        if (zzafa == null) {
            zzafa = new zzc();
        }
        this.zzafa = zzafa;
        this.zzaeZ = zzaeZ;
        this.zzaeX = 0;
        if (this.zzaeW) {
            zzx.zzb(this.zzaeU == null, (Object)"can't be anonymous with an upload account");
        }
    }
    
    public zzb(final Context context, final String s, final String s2, final String s3) {
        this(context, -1, s, s2, s3, false, zzb.zzaeQ, zzmt.zzsc(), null, com.google.android.gms.clearcut.zza.zzaeP);
    }
    
    private int zzai(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.wtf("ClearcutLogger", "This can't happen.");
            return 0;
        }
    }
    
    private static int[] zzb(final ArrayList<Integer> list) {
        if (list == null) {
            return null;
        }
        final int[] array = new int[list.size()];
        final Iterator<Integer> iterator = list.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final int intValue = iterator.next();
            final int n2 = n + 1;
            array[n] = intValue;
            n = n2;
        }
        return array;
    }
    
    public boolean zza(final GoogleApiClient googleApiClient, final long n, final TimeUnit timeUnit) {
        return this.zzaeY.zza(googleApiClient, n, timeUnit);
    }
    
    public zza zzi(final byte[] array) {
        return new zza(array);
    }
    
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
            this.zzaeT = zzb.this.zzaeT;
            this.zzaeS = zzb.this.zzaeS;
            this.zzaeU = zzb.this.zzaeU;
            this.zzaeV = zzb.this.zzaeV;
            this.zzaeX = zzb.this.zzaeX;
            this.zzafd = null;
            this.zzafe = new zzsz.zzd();
            this.zzaff = false;
            this.zzaeU = zzb.this.zzaeU;
            this.zzaeV = zzb.this.zzaeV;
            this.zzafe.zzbuR = zzb.this.zzqW.currentTimeMillis();
            this.zzafe.zzbuS = zzb.this.zzqW.elapsedRealtime();
            this.zzafe.zzbvi = zzb.this.zzaeZ.zzah(zzb.this.mContext);
            this.zzafe.zzbvd = zzb.this.zzafa.zzC(this.zzafe.zzbuR);
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
            return zzb.this.zzaeY.zza(googleApiClient, this.zzoE());
        }
        
        public LogEventParcelable zzoE() {
            return new LogEventParcelable(new PlayLoggerContext(zzb.this.zzTJ, zzb.this.zzaeR, this.zzaeT, this.zzaeS, this.zzaeU, this.zzaeV, zzb.this.zzaeW, this.zzaeX), this.zzafe, this.zzafb, this.zzafc, zzb(this.zzafd));
        }
    }
    
    public interface zzb
    {
        byte[] zzoF();
    }
    
    public static class zzc
    {
        public long zzC(final long n) {
            return TimeZone.getDefault().getOffset(n) / 1000;
        }
    }
}
