package com.google.android.gms.common.api;

import android.content.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import java.util.*;

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
