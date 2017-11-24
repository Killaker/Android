package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.*;
import java.io.*;
import com.google.android.gms.common.*;
import android.support.annotation.*;

private class zza implements OnConnectionFailedListener
{
    public final int zzaiF;
    public final GoogleApiClient zzaiG;
    public final OnConnectionFailedListener zzaiH;
    
    public zza(final int zzaiF, final GoogleApiClient zzaiG, final OnConnectionFailedListener zzaiH) {
        this.zzaiF = zzaiF;
        this.zzaiG = zzaiG;
        this.zzaiH = zzaiH;
        zzaiG.registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener)this);
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("GoogleApiClient #").print(this.zzaiF);
        printWriter.println(":");
        this.zzaiG.dump(s + "  ", fileDescriptor, printWriter, array);
    }
    
    @Override
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        zzw.zzc(zzw.this).post((Runnable)new zzb(this.zzaiF, connectionResult));
    }
    
    public void zzpR() {
        this.zzaiG.unregisterConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener)this);
        this.zzaiG.disconnect();
    }
}
