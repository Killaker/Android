package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.*;
import android.support.v4.app.*;
import android.content.*;
import android.app.*;
import com.google.android.gms.common.*;

@KeepName
public class SupportLifecycleFragmentImpl extends zzw
{
    @Override
    protected void zzb(final int n, final ConnectionResult connectionResult) {
        GooglePlayServicesUtil.showErrorDialogFragment(connectionResult.getErrorCode(), this.getActivity(), this, 2, (DialogInterface$OnCancelListener)this);
    }
    
    @Override
    protected void zzc(final int n, final ConnectionResult connectionResult) {
        this.zzaiD = zzn.zza(this.getActivity().getApplicationContext(), new zzn() {
            final /* synthetic */ Dialog zzaiL = SupportLifecycleFragmentImpl.this.zzpS().zza(this.getActivity(), (DialogInterface$OnCancelListener)this);
            
            @Override
            protected void zzpJ() {
                SupportLifecycleFragmentImpl.this.zzpP();
                this.zzaiL.dismiss();
            }
        });
    }
    
    protected GoogleApiAvailability zzpS() {
        return GoogleApiAvailability.getInstance();
    }
}
