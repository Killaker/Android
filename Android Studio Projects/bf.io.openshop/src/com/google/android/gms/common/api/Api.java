package com.google.android.gms.common.api;

import java.io.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import android.content.*;
import android.os.*;

public final class Api<O extends ApiOptions>
{
    private final String mName;
    private final zzc<?> zzaeE;
    private final zza<?, O> zzafW;
    private final zze<?, O> zzafX;
    private final zzf<?> zzafY;
    
    public Api(final String mName, final zza<C, O> zzafW, final zzc<C> zzaeE) {
        zzx.zzb(zzafW, "Cannot construct an Api with a null ClientBuilder");
        zzx.zzb(zzaeE, "Cannot construct an Api with a null ClientKey");
        this.mName = mName;
        this.zzafW = zzafW;
        this.zzafX = null;
        this.zzaeE = zzaeE;
        this.zzafY = null;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public zza<?, O> zzoP() {
        zzx.zza(this.zzafW != null, (Object)"This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.zzafW;
    }
    
    public zze<?, O> zzoQ() {
        zzx.zza(this.zzafX != null, (Object)"This API was constructed with a ClientBuilder. Use getClientBuilder");
        return this.zzafX;
    }
    
    public zzc<?> zzoR() {
        zzx.zza(this.zzaeE != null, (Object)"This API was constructed with a SimpleClientKey. Use getSimpleClientKey");
        return this.zzaeE;
    }
    
    public boolean zzoS() {
        return this.zzafY != null;
    }
    
    public interface ApiOptions
    {
        public interface HasOptions extends ApiOptions
        {
        }
        
        public static final class NoOptions implements NotRequiredOptions
        {
        }
        
        public interface NotRequiredOptions extends ApiOptions
        {
        }
        
        public interface Optional extends HasOptions, NotRequiredOptions
        {
        }
    }
    
    public abstract static class zza<T extends zzb, O>
    {
        public int getPriority() {
            return Integer.MAX_VALUE;
        }
        
        public abstract T zza(final Context p0, final Looper p1, final com.google.android.gms.common.internal.zzf p2, final O p3, final GoogleApiClient.ConnectionCallbacks p4, final GoogleApiClient.OnConnectionFailedListener p5);
        
        public List<Scope> zzo(final O o) {
            return Collections.emptyList();
        }
    }
    
    public interface zzb
    {
        void disconnect();
        
        void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
        
        boolean isConnected();
        
        void zza(final GoogleApiClient.zza p0);
        
        void zza(final zzp p0, final Set<Scope> p1);
        
        boolean zzmE();
        
        boolean zznb();
        
        Intent zznc();
        
        IBinder zzoT();
    }
    
    public static final class zzc<C extends zzb>
    {
    }
    
    public interface zzd<T extends IInterface>
    {
        T zzW(final IBinder p0);
        
        void zza(final int p0, final T p1);
        
        String zzgu();
        
        String zzgv();
    }
    
    public interface zze<T extends zzd, O>
    {
        int getPriority();
        
        int zzoU();
        
        T zzq(final O p0);
    }
    
    public static final class zzf<C extends zzd>
    {
    }
}
