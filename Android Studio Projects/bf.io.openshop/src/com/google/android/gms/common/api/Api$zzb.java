package com.google.android.gms.common.api;

import java.io.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import android.content.*;
import android.os.*;

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
