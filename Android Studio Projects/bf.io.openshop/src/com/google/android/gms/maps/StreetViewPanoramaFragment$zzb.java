package com.google.android.gms.maps;

import android.app.*;
import android.content.*;
import com.google.android.gms.maps.internal.*;
import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.common.*;
import java.util.*;

static class zzb extends zza<StreetViewPanoramaFragment.zza>
{
    private Activity mActivity;
    private final List<OnStreetViewPanoramaReadyCallback> zzaSC;
    protected zzf<StreetViewPanoramaFragment.zza> zzaSh;
    private final Fragment zzavH;
    
    zzb(final Fragment zzavH) {
        this.zzaSC = new ArrayList<OnStreetViewPanoramaReadyCallback>();
        this.zzavH = zzavH;
    }
    
    private void setActivity(final Activity mActivity) {
        this.mActivity = mActivity;
        this.zzzW();
    }
    
    public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        if (this.zztU() != null) {
            ((StreetViewPanoramaFragment.zza)this.zztU()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
            return;
        }
        this.zzaSC.add(onStreetViewPanoramaReadyCallback);
    }
    
    @Override
    protected void zza(final zzf<StreetViewPanoramaFragment.zza> zzaSh) {
        this.zzaSh = zzaSh;
        this.zzzW();
    }
    
    public void zzzW() {
        if (this.mActivity == null || this.zzaSh == null || this.zztU() != null) {
            goto Label_0139;
        }
        try {
            MapsInitializer.initialize((Context)this.mActivity);
            this.zzaSh.zza(new StreetViewPanoramaFragment.zza(this.zzavH, zzad.zzaO((Context)this.mActivity).zzt(zze.zzC(this.mActivity))));
            final Iterator<OnStreetViewPanoramaReadyCallback> iterator = this.zzaSC.iterator();
            while (iterator.hasNext()) {
                ((StreetViewPanoramaFragment.zza)this.zztU()).getStreetViewPanoramaAsync(iterator.next());
            }
            goto Label_0130;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
        catch (GooglePlayServicesNotAvailableException ex2) {}
    }
}
