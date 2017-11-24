package com.google.android.gms.common.stats;

import com.google.android.gms.internal.*;
import android.util.*;
import android.os.*;
import android.content.*;
import android.content.pm.*;
import java.util.*;
import android.annotation.*;

public class zzb
{
    private static final Object zzalX;
    private static zzb zzanp;
    private static Integer zzanv;
    private final List<String> zzanq;
    private final List<String> zzanr;
    private final List<String> zzans;
    private final List<String> zzant;
    private zze zzanu;
    private zze zzanw;
    
    static {
        zzalX = new Object();
    }
    
    private zzb() {
        if (getLogLevel() == zzd.LOG_LEVEL_OFF) {
            this.zzanq = (List<String>)Collections.EMPTY_LIST;
            this.zzanr = (List<String>)Collections.EMPTY_LIST;
            this.zzans = (List<String>)Collections.EMPTY_LIST;
            this.zzant = (List<String>)Collections.EMPTY_LIST;
            return;
        }
        final String s = zzc.zza.zzanA.get();
        List<String> zzanq;
        if (s == null) {
            zzanq = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            zzanq = Arrays.asList(s.split(","));
        }
        this.zzanq = zzanq;
        final String s2 = zzc.zza.zzanB.get();
        List<String> zzanr;
        if (s2 == null) {
            zzanr = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            zzanr = Arrays.asList(s2.split(","));
        }
        this.zzanr = zzanr;
        final String s3 = zzc.zza.zzanC.get();
        List<String> zzans;
        if (s3 == null) {
            zzans = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            zzans = Arrays.asList(s3.split(","));
        }
        this.zzans = zzans;
        final String s4 = zzc.zza.zzanD.get();
        List<String> zzant;
        if (s4 == null) {
            zzant = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            zzant = Arrays.asList(s4.split(","));
        }
        this.zzant = zzant;
        this.zzanu = new zze(1024, zzc.zza.zzanE.get());
        this.zzanw = new zze(1024, zzc.zza.zzanE.get());
    }
    
    private static int getLogLevel() {
        Label_0032: {
            if (zzb.zzanv != null) {
                break Label_0032;
            }
            try {
                int n;
                if (zzmp.zzkr()) {
                    n = zzc.zza.zzanz.get();
                }
                else {
                    n = zzd.LOG_LEVEL_OFF;
                }
                zzb.zzanv = n;
                return zzb.zzanv;
            }
            catch (SecurityException ex) {
                zzb.zzanv = zzd.LOG_LEVEL_OFF;
                return zzb.zzanv;
            }
        }
    }
    
    private void zza(final Context context, final String s, final int n, final String s2, final String s3, final String s4, final String s5) {
        final long currentTimeMillis = System.currentTimeMillis();
        final int n2 = getLogLevel() & zzd.zzanJ;
        String zzn = null;
        if (n2 != 0) {
            zzn = null;
            if (n != 13) {
                zzn = zznf.zzn(3, 5);
            }
        }
        long nativeHeapAllocatedSize = 0L;
        if ((getLogLevel() & zzd.zzanL) != 0x0) {
            nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize();
        }
        ConnectionEvent connectionEvent;
        if (n == 1 || n == 4 || n == 14) {
            connectionEvent = new ConnectionEvent(currentTimeMillis, n, null, null, null, null, zzn, s, SystemClock.elapsedRealtime(), nativeHeapAllocatedSize);
        }
        else {
            connectionEvent = new ConnectionEvent(currentTimeMillis, n, s2, s3, s4, s5, zzn, s, SystemClock.elapsedRealtime(), nativeHeapAllocatedSize);
        }
        context.startService(new Intent().setComponent(zzd.zzanF).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", (Parcelable)connectionEvent));
    }
    
    private void zza(final Context context, final String s, final String s2, final Intent intent, final int n) {
        String zzaz = null;
        if (this.zzrQ() && this.zzanu != null) {
            String name;
            String processName;
            if (n == 4 || n == 1) {
                if (!this.zzanu.zzcT(s)) {
                    return;
                }
                name = null;
                processName = null;
            }
            else {
                final ServiceInfo zzd = zzd(context, intent);
                if (zzd == null) {
                    Log.w("ConnectionTracker", String.format("Client %s made an invalid request %s", s2, intent.toUri(0)));
                    return;
                }
                processName = zzd.processName;
                name = zzd.name;
                zzaz = zznf.zzaz(context);
                if (!this.zzb(zzaz, s2, processName, name)) {
                    return;
                }
                this.zzanu.zzcS(s);
            }
            this.zza(context, s, n, zzaz, s2, processName, name);
        }
    }
    
    private String zzb(final ServiceConnection serviceConnection) {
        return String.valueOf(Process.myPid() << 32 | System.identityHashCode(serviceConnection));
    }
    
    private boolean zzb(final String s, final String s2, final String s3, final String s4) {
        final int logLevel = getLogLevel();
        return !this.zzanq.contains(s) && !this.zzanr.contains(s2) && !this.zzans.contains(s3) && !this.zzant.contains(s4) && (!s3.equals(s) || (logLevel & zzd.zzanK) == 0x0);
    }
    
    private boolean zzc(final Context context, final Intent intent) {
        final ComponentName component = intent.getComponent();
        return component != null && (!com.google.android.gms.common.internal.zzd.zzakE || !"com.google.android.gms".equals(component.getPackageName())) && zzmp.zzk(context, component.getPackageName());
    }
    
    private static ServiceInfo zzd(final Context context, final Intent intent) {
        final List queryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            Log.w("ConnectionTracker", String.format("There are no handler of this intent: %s\n Stack trace: %s", intent.toUri(0), zznf.zzn(3, 20)));
            return null;
        }
        if (queryIntentServices.size() > 1) {
            Log.w("ConnectionTracker", String.format("Multiple handlers found for this intent: %s\n Stack trace: %s", intent.toUri(0), zznf.zzn(3, 20)));
            final Iterator<ResolveInfo> iterator = queryIntentServices.iterator();
            if (iterator.hasNext()) {
                Log.w("ConnectionTracker", iterator.next().serviceInfo.name);
                return null;
            }
        }
        return queryIntentServices.get(0).serviceInfo;
    }
    
    public static zzb zzrP() {
        synchronized (zzb.zzalX) {
            if (zzb.zzanp == null) {
                zzb.zzanp = new zzb();
            }
            return zzb.zzanp;
        }
    }
    
    private boolean zzrQ() {
        return com.google.android.gms.common.internal.zzd.zzakE && getLogLevel() != zzd.LOG_LEVEL_OFF;
    }
    
    @SuppressLint({ "UntrackedBindService" })
    public void zza(final Context context, final ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
        this.zza(context, this.zzb(serviceConnection), null, (Intent)null, 1);
    }
    
    public void zza(final Context context, final ServiceConnection serviceConnection, final String s, final Intent intent) {
        this.zza(context, this.zzb(serviceConnection), s, intent, 3);
    }
    
    public boolean zza(final Context context, final Intent intent, final ServiceConnection serviceConnection, final int n) {
        return this.zza(context, context.getClass().getName(), intent, serviceConnection, n);
    }
    
    @SuppressLint({ "UntrackedBindService" })
    public boolean zza(final Context context, final String s, final Intent intent, final ServiceConnection serviceConnection, final int n) {
        if (this.zzc(context, intent)) {
            Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
            return false;
        }
        final boolean bindService = context.bindService(intent, serviceConnection, n);
        if (bindService) {
            this.zza(context, this.zzb(serviceConnection), s, intent, 2);
        }
        return bindService;
    }
    
    public void zzb(final Context context, final ServiceConnection serviceConnection) {
        this.zza(context, this.zzb(serviceConnection), null, (Intent)null, 4);
    }
}
