package com.google.android.gms.internal;

import java.lang.reflect.*;
import android.os.*;
import android.util.*;
import android.content.*;
import java.util.*;
import android.content.pm.*;

public class zznj
{
    private static final Method zzaol;
    private static final Method zzaom;
    private static final Method zzaon;
    private static final Method zzaoo;
    private static final Method zzaop;
    
    static {
        zzaol = zzsp();
        zzaom = zzsq();
        zzaon = zzsr();
        zzaoo = zzss();
        zzaop = zzst();
    }
    
    public static int zza(final WorkSource workSource) {
        if (zznj.zzaon != null) {
            try {
                return (int)zznj.zzaon.invoke(workSource, new Object[0]);
            }
            catch (Exception ex) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", (Throwable)ex);
            }
        }
        return 0;
    }
    
    public static String zza(final WorkSource workSource, final int n) {
        if (zznj.zzaop != null) {
            try {
                return (String)zznj.zzaop.invoke(workSource, n);
            }
            catch (Exception ex) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", (Throwable)ex);
            }
        }
        return null;
    }
    
    public static void zza(final WorkSource workSource, final int n, String s) {
        Label_0060: {
            if (zznj.zzaom == null) {
                break Label_0060;
            }
            if (s == null) {
                s = "";
            }
            try {
                zznj.zzaom.invoke(workSource, n, s);
                return;
            }
            catch (Exception ex) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", (Throwable)ex);
                return;
            }
        }
        if (zznj.zzaol == null) {
            return;
        }
        try {
            zznj.zzaol.invoke(workSource, n);
        }
        catch (Exception ex2) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", (Throwable)ex2);
        }
    }
    
    public static boolean zzaA(final Context context) {
        if (context != null) {
            final PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && packageManager.checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName()) == 0) {
                return true;
            }
        }
        return false;
    }
    
    public static List<String> zzb(final WorkSource workSource) {
        int i = 0;
        int zza;
        if (workSource == null) {
            zza = 0;
        }
        else {
            zza = zza(workSource);
        }
        List<String> empty_LIST;
        if (zza == 0) {
            empty_LIST = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            empty_LIST = new ArrayList<String>();
            while (i < zza) {
                final String zza2 = zza(workSource, i);
                if (!zzni.zzcV(zza2)) {
                    empty_LIST.add(zza2);
                }
                ++i;
            }
        }
        return empty_LIST;
    }
    
    public static WorkSource zzf(final int n, final String s) {
        final WorkSource workSource = new WorkSource();
        zza(workSource, n, s);
        return workSource;
    }
    
    public static WorkSource zzl(final Context context, final String s) {
        if (context == null || context.getPackageManager() == null) {
            return null;
        }
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(s, 0);
            if (applicationInfo == null) {
                Log.e("WorkSourceUtil", "Could not get applicationInfo from package: " + s);
                return null;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.e("WorkSourceUtil", "Could not find package: " + s);
            return null;
        }
        return zzf(applicationInfo.uid, s);
    }
    
    private static Method zzsp() {
        try {
            return WorkSource.class.getMethod("add", Integer.TYPE);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzsq() {
        final boolean zzsj = zzne.zzsj();
        Method method = null;
        if (!zzsj) {
            return method;
        }
        try {
            method = WorkSource.class.getMethod("add", Integer.TYPE, String.class);
            return method;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzsr() {
        try {
            return WorkSource.class.getMethod("size", (Class<?>[])new Class[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzss() {
        try {
            return WorkSource.class.getMethod("get", Integer.TYPE);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzst() {
        final boolean zzsj = zzne.zzsj();
        Method method = null;
        if (!zzsj) {
            return method;
        }
        try {
            method = WorkSource.class.getMethod("getName", Integer.TYPE);
            return method;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
