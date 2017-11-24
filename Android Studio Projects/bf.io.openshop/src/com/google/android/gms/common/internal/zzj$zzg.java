package com.google.android.gms.common.internal;

import com.google.android.gms.common.*;
import android.util.*;
import android.os.*;

protected final class zzg extends zza
{
    public final IBinder zzalP;
    
    public zzg(final int n, final IBinder zzalP, final Bundle bundle) {
        super(n, bundle);
        this.zzalP = zzalP;
    }
    
    @Override
    protected void zzj(final ConnectionResult connectionResult) {
        if (zzj.zzf(zzj.this) != null) {
            zzj.zzf(zzj.this).onConnectionFailed(connectionResult);
        }
        zzj.this.onConnectionFailed(connectionResult);
    }
    
    @Override
    protected boolean zzqL() {
        while (true) {
            try {
                final String interfaceDescriptor = this.zzalP.getInterfaceDescriptor();
                if (!zzj.this.zzgv().equals(interfaceDescriptor)) {
                    Log.e("GmsClient", "service descriptor mismatch: " + zzj.this.zzgv() + " vs. " + interfaceDescriptor);
                    return false;
                }
            }
            catch (RemoteException ex) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
            final IInterface zzW = zzj.this.zzW(this.zzalP);
            if (zzW != null && zzj.zza(zzj.this, 2, 3, zzW)) {
                break;
            }
            return false;
        }
        final Bundle zzoi = zzj.this.zzoi();
        if (zzj.zzc(zzj.this) != null) {
            zzj.zzc(zzj.this).onConnected(zzoi);
        }
        return true;
    }
}
