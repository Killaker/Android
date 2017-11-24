package com.google.android.gms.maps;

import com.google.android.gms.common.internal.*;
import com.google.android.gms.maps.internal.*;
import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.dynamic.*;
import android.view.*;
import android.app.*;

static class zza implements StreetViewLifecycleDelegate
{
    private final IStreetViewPanoramaViewDelegate zzaSL;
    private View zzaSM;
    private final ViewGroup zzaSk;
    
    public zza(final ViewGroup viewGroup, final IStreetViewPanoramaViewDelegate streetViewPanoramaViewDelegate) {
        this.zzaSL = zzx.zzz(streetViewPanoramaViewDelegate);
        this.zzaSk = zzx.zzz(viewGroup);
    }
    
    @Override
    public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        try {
            this.zzaSL.getStreetViewPanoramaAsync(new zzaa.zza() {
                public void zza(final IStreetViewPanoramaDelegate streetViewPanoramaDelegate) throws RemoteException {
                    onStreetViewPanoramaReadyCallback.onStreetViewPanoramaReady(new StreetViewPanorama(streetViewPanoramaDelegate));
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
            this.zzaSL.onCreate(bundle);
            this.zzaSM = zze.zzp(this.zzaSL.getView());
            this.zzaSk.removeAllViews();
            this.zzaSk.addView(this.zzaSM);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    @Override
    public void onDestroy() {
        try {
            this.zzaSL.onDestroy();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onDestroyView() {
        throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    @Override
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
    }
    
    @Override
    public void onLowMemory() {
        try {
            this.zzaSL.onLowMemory();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onPause() {
        try {
            this.zzaSL.onPause();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onResume() {
        try {
            this.zzaSL.onResume();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        try {
            this.zzaSL.onSaveInstanceState(bundle);
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
    
    public IStreetViewPanoramaViewDelegate zzAd() {
        return this.zzaSL;
    }
}
