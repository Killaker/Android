package com.google.android.gms.maps;

import android.content.*;
import android.view.*;
import com.google.android.gms.maps.internal.*;
import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.common.*;
import java.util.*;

static class zzb extends zza<StreetViewPanoramaView.zza>
{
    private final Context mContext;
    private final List<OnStreetViewPanoramaReadyCallback> zzaSC;
    private final StreetViewPanoramaOptions zzaSO;
    protected zzf<StreetViewPanoramaView.zza> zzaSh;
    private final ViewGroup zzaSo;
    
    zzb(final ViewGroup zzaSo, final Context mContext, final StreetViewPanoramaOptions zzaSO) {
        this.zzaSC = new ArrayList<OnStreetViewPanoramaReadyCallback>();
        this.zzaSo = zzaSo;
        this.mContext = mContext;
        this.zzaSO = zzaSO;
    }
    
    public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        if (this.zztU() != null) {
            ((StreetViewPanoramaView.zza)this.zztU()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
            return;
        }
        this.zzaSC.add(onStreetViewPanoramaReadyCallback);
    }
    
    @Override
    protected void zza(final zzf<StreetViewPanoramaView.zza> zzaSh) {
        this.zzaSh = zzaSh;
        this.zzzW();
    }
    
    public void zzzW() {
        if (this.zzaSh == null || this.zztU() != null) {
            goto Label_0126;
        }
        try {
            this.zzaSh.zza(new StreetViewPanoramaView.zza(this.zzaSo, zzad.zzaO(this.mContext).zza(zze.zzC(this.mContext), this.zzaSO)));
            final Iterator<OnStreetViewPanoramaReadyCallback> iterator = this.zzaSC.iterator();
            while (iterator.hasNext()) {
                ((StreetViewPanoramaView.zza)this.zztU()).getStreetViewPanoramaAsync(iterator.next());
            }
            goto Label_0117;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
        catch (GooglePlayServicesNotAvailableException ex2) {}
    }
}
