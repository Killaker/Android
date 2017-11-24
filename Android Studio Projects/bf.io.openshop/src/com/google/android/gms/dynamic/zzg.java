package com.google.android.gms.dynamic;

import android.content.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.*;
import android.os.*;

public abstract class zzg<T>
{
    private final String zzavI;
    private T zzavJ;
    
    protected zzg(final String zzavI) {
        this.zzavI = zzavI;
    }
    
    protected final T zzaB(final Context context) throws zza {
        Label_0060: {
            if (this.zzavJ != null) {
                break Label_0060;
            }
            zzx.zzz(context);
            final Context remoteContext = zze.getRemoteContext(context);
            if (remoteContext == null) {
                throw new zza("Could not get remote context.");
            }
            final ClassLoader classLoader = remoteContext.getClassLoader();
            try {
                this.zzavJ = this.zzd((IBinder)classLoader.loadClass(this.zzavI).newInstance());
                return this.zzavJ;
            }
            catch (ClassNotFoundException ex) {
                throw new zza("Could not load creator class.", ex);
            }
            catch (InstantiationException ex2) {
                throw new zza("Could not instantiate creator.", ex2);
            }
            catch (IllegalAccessException ex3) {
                throw new zza("Could not access creator.", ex3);
            }
        }
    }
    
    protected abstract T zzd(final IBinder p0);
    
    public static class zza extends Exception
    {
        public zza(final String s) {
            super(s);
        }
        
        public zza(final String s, final Throwable t) {
            super(s, t);
        }
    }
}
