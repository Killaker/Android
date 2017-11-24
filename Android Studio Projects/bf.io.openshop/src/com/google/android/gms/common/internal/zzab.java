package com.google.android.gms.common.internal;

import android.content.*;
import com.google.android.gms.common.api.*;
import android.view.*;
import com.google.android.gms.dynamic.*;
import android.os.*;

public final class zzab extends zzg<zzu>
{
    private static final zzab zzamw;
    
    static {
        zzamw = new zzab();
    }
    
    private zzab() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }
    
    public static View zzb(final Context context, final int n, final int n2, final Scope[] array) throws zza {
        return zzab.zzamw.zzc(context, n, n2, array);
    }
    
    private View zzc(final Context context, final int n, final int n2, final Scope[] array) throws zza {
        try {
            return zze.zzp(this.zzaB(context).zza(zze.zzC(context), new SignInButtonConfig(n, n2, array)));
        }
        catch (Exception ex) {
            throw new zza("Could not get button with size " + n + " and color " + n2, ex);
        }
    }
    
    public zzu zzaV(final IBinder binder) {
        return zzu.zza.zzaU(binder);
    }
}
