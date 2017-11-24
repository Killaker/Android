package com.google.android.gms.maps;

import com.google.android.gms.common.internal.*;
import com.google.android.gms.maps.internal.*;
import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.dynamic.*;
import android.view.*;
import android.app.*;

static class zza implements MapLifecycleDelegate
{
    private final ViewGroup zzaSk;
    private final IMapViewDelegate zzaSl;
    private View zzaSm;
    
    public zza(final ViewGroup viewGroup, final IMapViewDelegate mapViewDelegate) {
        this.zzaSl = zzx.zzz(mapViewDelegate);
        this.zzaSk = zzx.zzz(viewGroup);
    }
    
    @Override
    public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
        try {
            this.zzaSl.getMapAsync(new zzo.zza() {
                public void zza(final IGoogleMapDelegate googleMapDelegate) throws RemoteException {
                    onMapReadyCallback.onMapReady(new GoogleMap(googleMapDelegate));
                }
            });
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        try {
            this.zzaSl.onCreate(bundle);
            this.zzaSm = zze.zzp(this.zzaSl.getView());
            this.zzaSk.removeAllViews();
            this.zzaSk.addView(this.zzaSm);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
    }
    
    @Override
    public void onDestroy() {
        try {
            this.zzaSl.onDestroy();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onDestroyView() {
        throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
    }
    
    public void onEnterAmbient(final Bundle bundle) {
        try {
            this.zzaSl.onEnterAmbient(bundle);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void onExitAmbient() {
        try {
            this.zzaSl.onExitAmbient();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
    }
    
    @Override
    public void onLowMemory() {
        try {
            this.zzaSl.onLowMemory();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onPause() {
        try {
            this.zzaSl.onPause();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onResume() {
        try {
            this.zzaSl.onResume();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        try {
            this.zzaSl.onSaveInstanceState(bundle);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onStart() {
    }
    
    @Override
    public void onStop() {
    }
    
    public IMapViewDelegate zzzX() {
        return this.zzaSl;
    }
}
