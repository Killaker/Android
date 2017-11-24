package com.google.android.gms.maps;

import android.support.v4.app.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.maps.internal.*;
import android.view.*;
import com.google.android.gms.dynamic.*;
import android.app.*;

static class zza implements MapLifecycleDelegate
{
    private final IMapFragmentDelegate zzaSe;
    private final Fragment zzalg;
    
    public zza(final Fragment fragment, final IMapFragmentDelegate mapFragmentDelegate) {
        this.zzaSe = zzx.zzz(mapFragmentDelegate);
        this.zzalg = zzx.zzz(fragment);
    }
    
    @Override
    public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
        try {
            this.zzaSe.getMapAsync(new zzo.zza() {
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
    public void onCreate(Bundle bundle) {
        Label_0012: {
            if (bundle != null) {
                break Label_0012;
            }
            try {
                bundle = new Bundle();
                final Bundle arguments = this.zzalg.getArguments();
                if (arguments != null && arguments.containsKey("MapOptions")) {
                    zzac.zza(bundle, "MapOptions", arguments.getParcelable("MapOptions"));
                }
                this.zzaSe.onCreate(bundle);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        try {
            return zze.zzp(this.zzaSe.onCreateView(zze.zzC(layoutInflater), zze.zzC(viewGroup), bundle));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onDestroy() {
        try {
            this.zzaSe.onDestroy();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onDestroyView() {
        try {
            this.zzaSe.onDestroyView();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void onEnterAmbient(final Bundle bundle) {
        try {
            this.zzaSe.onEnterAmbient(bundle);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void onExitAmbient() {
        try {
            this.zzaSe.onExitAmbient();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        final GoogleMapOptions googleMapOptions = (GoogleMapOptions)bundle.getParcelable("MapOptions");
        try {
            this.zzaSe.onInflate(zze.zzC(activity), googleMapOptions, bundle2);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onLowMemory() {
        try {
            this.zzaSe.onLowMemory();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onPause() {
        try {
            this.zzaSe.onPause();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onResume() {
        try {
            this.zzaSe.onResume();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        try {
            this.zzaSe.onSaveInstanceState(bundle);
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
    
    public IMapFragmentDelegate zzzV() {
        return this.zzaSe;
    }
}
