package com.google.android.gms.maps;

import android.app.*;
import android.support.v4.app.*;
import android.content.*;
import com.google.android.gms.maps.internal.*;
import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.common.*;
import java.util.*;

static class zzb extends zza<SupportStreetViewPanoramaFragment.zza>
{
    private Activity mActivity;
    private final List<OnStreetViewPanoramaReadyCallback> zzaSC;
    protected zzf<SupportStreetViewPanoramaFragment.zza> zzaSh;
    private final Fragment zzalg;
    
    zzb(final Fragment zzalg) {
        this.zzaSC = new ArrayList<OnStreetViewPanoramaReadyCallback>();
        this.zzalg = zzalg;
    }
    
    private void setActivity(final Activity mActivity) {
        this.mActivity = mActivity;
        this.zzzW();
    }
    
    public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        if (this.zztU() != null) {
            ((SupportStreetViewPanoramaFragment.zza)this.zztU()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
            return;
        }
        this.zzaSC.add(onStreetViewPanoramaReadyCallback);
    }
    
    @Override
    protected void zza(final zzf<SupportStreetViewPanoramaFragment.zza> zzaSh) {
        this.zzaSh = zzaSh;
        this.zzzW();
    }
    
    public void zzzW() {
        if (this.mActivity == null || this.zzaSh == null || this.zztU() != null) {
            goto Label_0139;
        }
        try {
            MapsInitializer.initialize((Context)this.mActivity);
            this.zzaSh.zza(new SupportStreetViewPanoramaFragment.zza(this.zzalg, zzad.zzaO((Context)this.mActivity).zzt(zze.zzC(this.mActivity))));
            final Iterator<OnStreetViewPanoramaReadyCallback> iterator = this.zzaSC.iterator();
            while (iterator.hasNext()) {
                ((SupportStreetViewPanoramaFragment.zza)this.zztU()).getStreetViewPanoramaAsync(iterator.next());
            }
            goto Label_0130;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
        catch (GooglePlayServicesNotAvailableException ex2) {}
    }
}
