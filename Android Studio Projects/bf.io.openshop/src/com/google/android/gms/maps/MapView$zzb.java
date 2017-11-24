package com.google.android.gms.maps;

import android.content.*;
import android.view.*;
import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.common.*;
import com.google.android.gms.maps.internal.*;
import java.util.*;

static class zzb extends zza<MapView.zza>
{
    private final Context mContext;
    protected zzf<MapView.zza> zzaSh;
    private final List<OnMapReadyCallback> zzaSi;
    private final ViewGroup zzaSo;
    private final GoogleMapOptions zzaSp;
    
    zzb(final ViewGroup zzaSo, final Context mContext, final GoogleMapOptions zzaSp) {
        this.zzaSi = new ArrayList<OnMapReadyCallback>();
        this.zzaSo = zzaSo;
        this.mContext = mContext;
        this.zzaSp = zzaSp;
    }
    
    public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
        if (this.zztU() != null) {
            ((MapView.zza)this.zztU()).getMapAsync(onMapReadyCallback);
            return;
        }
        this.zzaSi.add(onMapReadyCallback);
    }
    
    public void onEnterAmbient(final Bundle bundle) {
        if (this.zztU() != null) {
            ((MapView.zza)this.zztU()).onEnterAmbient(bundle);
        }
    }
    
    public void onExitAmbient() {
        if (this.zztU() != null) {
            ((MapView.zza)this.zztU()).onExitAmbient();
        }
    }
    
    @Override
    protected void zza(final zzf<MapView.zza> zzaSh) {
        this.zzaSh = zzaSh;
        this.zzzW();
    }
    
    public void zzzW() {
        if (this.zzaSh != null && this.zztU() == null) {
            try {
                MapsInitializer.initialize(this.mContext);
                final IMapViewDelegate zza = zzad.zzaO(this.mContext).zza(zze.zzC(this.mContext), this.zzaSp);
                if (zza == null) {
                    return;
                }
                this.zzaSh.zza(new MapView.zza(this.zzaSo, zza));
                final Iterator<OnMapReadyCallback> iterator = this.zzaSi.iterator();
                while (iterator.hasNext()) {
                    ((MapView.zza)this.zztU()).getMapAsync(iterator.next());
                }
                goto Label_0133;
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
