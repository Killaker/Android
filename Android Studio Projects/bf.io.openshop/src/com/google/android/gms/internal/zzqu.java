package com.google.android.gms.internal;

import android.content.*;
import android.accounts.*;
import java.util.*;
import com.google.android.gms.common.api.*;
import android.view.*;
import android.util.*;
import android.content.pm.*;
import com.google.android.gms.playlog.internal.*;
import android.app.*;

@Deprecated
public class zzqu
{
    private final zzf zzbdy;
    private PlayLoggerContext zzbdz;
    
    public zzqu(final Context context, final int n, final String s, final String s2, final zza zza, final boolean b, final String s3) {
        final String packageName = context.getPackageName();
        while (true) {
            try {
                final int versionCode = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
                this.zzbdz = new PlayLoggerContext(packageName, versionCode, n, s, s2, b);
                this.zzbdy = new zzf(context, context.getMainLooper(), new zzd(zza), new com.google.android.gms.common.internal.zzf(null, null, null, 49, null, packageName, s3, null));
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.wtf("PlayLogger", "This can't happen.", (Throwable)ex);
                final int versionCode = 0;
                continue;
            }
            break;
        }
    }
    
    public void start() {
        this.zzbdy.start();
    }
    
    public void stop() {
        this.zzbdy.stop();
    }
    
    public void zza(final long n, final String s, final byte[] array, final String... array2) {
        this.zzbdy.zzb(this.zzbdz, new LogEvent(n, 0L, s, array, array2));
    }
    
    public void zzb(final String s, final byte[] array, final String... array2) {
        this.zza(System.currentTimeMillis(), s, array, array2);
    }
    
    public interface zza
    {
        void zzES();
        
        void zzET();
        
        void zzc(final PendingIntent p0);
    }
}
