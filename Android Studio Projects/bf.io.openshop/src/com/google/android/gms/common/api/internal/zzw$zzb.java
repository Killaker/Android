package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.*;
import android.app.*;
import android.content.*;
import android.support.annotation.*;

private class zzb implements Runnable
{
    private final int zzaiJ;
    private final ConnectionResult zzaiK;
    
    public zzb(final int zzaiJ, final ConnectionResult zzaiK) {
        this.zzaiJ = zzaiJ;
        this.zzaiK = zzaiK;
    }
    
    @MainThread
    @Override
    public void run() {
        if (!zzw.zza(zzw.this) || zzw.zzb(zzw.this)) {
            return;
        }
        zzw.zza(zzw.this, true);
        zzw.zza(zzw.this, this.zzaiJ);
        zzw.zza(zzw.this, this.zzaiK);
        if (this.zzaiK.hasResolution()) {
            try {
                this.zzaiK.startResolutionForResult(zzw.this.getActivity(), 1 + (1 + zzw.this.getActivity().getSupportFragmentManager().getFragments().indexOf(zzw.this) << 16));
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                zzw.this.zzpP();
                return;
            }
        }
        if (zzw.this.zzpQ().isUserResolvableError(this.zzaiK.getErrorCode())) {
            zzw.this.zzb(this.zzaiJ, this.zzaiK);
            return;
        }
        if (this.zzaiK.getErrorCode() == 18) {
            zzw.this.zzc(this.zzaiJ, this.zzaiK);
            return;
        }
        zzw.zza(zzw.this, this.zzaiJ, this.zzaiK);
    }
}
