package com.google.android.gms.maps;

import android.app.*;
import android.support.v4.app.*;
import android.content.*;
import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.common.*;
import com.google.android.gms.maps.internal.*;
import java.util.*;

static class zzb extends zza<SupportMapFragment.zza>
{
    private Activity mActivity;
    protected zzf<SupportMapFragment.zza> zzaSh;
    private final List<OnMapReadyCallback> zzaSi;
    private final Fragment zzalg;
    
    zzb(final Fragment zzalg) {
        this.zzaSi = new ArrayList<OnMapReadyCallback>();
        this.zzalg = zzalg;
    }
    
    private void setActivity(final Activity mActivity) {
        this.mActivity = mActivity;
        this.zzzW();
    }
    
    public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
        if (this.zztU() != null) {
            ((SupportMapFragment.zza)this.zztU()).getMapAsync(onMapReadyCallback);
            return;
        }
        this.zzaSi.add(onMapReadyCallback);
    }
    
    public void onEnterAmbient(final Bundle bundle) {
        if (this.zztU() != null) {
            ((SupportMapFragment.zza)this.zztU()).onEnterAmbient(bundle);
        }
    }
    
    public void onExitAmbient() {
        if (this.zztU() != null) {
            ((SupportMapFragment.zza)this.zztU()).onExitAmbient();
        }
    }
    
    @Override
    protected void zza(final zzf<SupportMapFragment.zza> zzaSh) {
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
                this.zzaSh.zza(new SupportMapFragment.zza(this.zzalg, zzs));
                final Iterator<OnMapReadyCallback> iterator = this.zzaSi.iterator();
                while (iterator.hasNext()) {
                    ((SupportMapFragment.zza)this.zztU()).getMapAsync(iterator.next());
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
