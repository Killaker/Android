package com.google.android.gms.maps;

import android.app.*;
import android.content.*;
import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.common.*;
import com.google.android.gms.maps.internal.*;
import java.util.*;

static class zzb extends zza<MapFragment.zza>
{
    private Activity mActivity;
    protected zzf<MapFragment.zza> zzaSh;
    private final List<OnMapReadyCallback> zzaSi;
    private final Fragment zzavH;
    
    zzb(final Fragment zzavH) {
        this.zzaSi = new ArrayList<OnMapReadyCallback>();
        this.zzavH = zzavH;
    }
    
    private void setActivity(final Activity mActivity) {
        this.mActivity = mActivity;
        this.zzzW();
    }
    
    public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
        if (this.zztU() != null) {
            ((MapFragment.zza)this.zztU()).getMapAsync(onMapReadyCallback);
            return;
        }
        this.zzaSi.add(onMapReadyCallback);
    }
    
    public void onEnterAmbient(final Bundle bundle) {
        if (this.zztU() != null) {
            ((MapFragment.zza)this.zztU()).onEnterAmbient(bundle);
        }
    }
    
    public void onExitAmbient() {
        if (this.zztU() != null) {
            ((MapFragment.zza)this.zztU()).onExitAmbient();
        }
    }
    
    @Override
    protected void zza(final zzf<MapFragment.zza> zzaSh) {
        this.zzaSh = zzaSh;
        this.zzzW();
    }
    
    public void zzzW() {
        if (this.mActivity != null && this.zzaSh != null && this.zztU() == null) {
            try {
                MapsInitializer.initialize((Context)this.mActivity);
                final IMapFragmentDelegate zzs = zzad.zzaO((Context)this.mActivity).zzs(zze.zzC(this.mActivity));
                if (zzs == null) {
                    return;
                }
                this.zzaSh.zza(new MapFragment.zza(this.zzavH, zzs));
                final Iterator<OnMapReadyCallback> iterator = this.zzaSi.iterator();
                while (iterator.hasNext()) {
                    ((MapFragment.zza)this.zztU()).getMapAsync(iterator.next());
                }
                goto Label_0136;
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
