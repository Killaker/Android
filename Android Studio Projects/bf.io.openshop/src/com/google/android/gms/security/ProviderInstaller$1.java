package com.google.android.gms.security;

import android.os.*;
import android.content.*;
import com.google.android.gms.common.*;

static final class ProviderInstaller$1 extends AsyncTask<Void, Void, Integer> {
    final /* synthetic */ ProviderInstallListener zzbgR;
    final /* synthetic */ Context zzxh;
    
    protected Integer zzc(final Void... array) {
        try {
            ProviderInstaller.installIfNeeded(this.zzxh);
            return 0;
        }
        catch (GooglePlayServicesRepairableException ex) {
            return ex.getConnectionStatusCode();
        }
        catch (GooglePlayServicesNotAvailableException ex2) {
            return ex2.errorCode;
        }
    }
    
    protected void zze(final Integer n) {
        if (n == 0) {
            this.zzbgR.onProviderInstalled();
            return;
        }
        this.zzbgR.onProviderInstallFailed(n, ProviderInstaller.zzFE().zza(this.zzxh, n, "pi"));
    }
}