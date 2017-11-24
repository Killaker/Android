package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.*;
import java.util.concurrent.*;
import java.io.*;
import android.support.annotation.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public interface zzp
{
    ConnectionResult blockingConnect();
    
    ConnectionResult blockingConnect(final long p0, final TimeUnit p1);
    
    void connect();
    
    boolean disconnect();
    
    void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
    
    @Nullable
    ConnectionResult getConnectionResult(@NonNull final Api<?> p0);
    
    boolean isConnected();
    
    boolean isConnecting();
    
     <A extends Api.zzb, R extends Result, T extends com.google.android.gms.common.api.internal.zza.zza<R, A>> T zza(@NonNull final T p0);
    
    boolean zza(final zzu p0);
    
     <A extends Api.zzb, T extends com.google.android.gms.common.api.internal.zza.zza<? extends Result, A>> T zzb(@NonNull final T p0);
    
    void zzoW();
    
    void zzpj();
    
    public interface zza
    {
        void zzc(final int p0, final boolean p1);
        
        void zzd(final ConnectionResult p0);
        
        void zzi(final Bundle p0);
    }
}
