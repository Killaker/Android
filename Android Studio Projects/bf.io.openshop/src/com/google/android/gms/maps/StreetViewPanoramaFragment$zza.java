package com.google.android.gms.maps;

import com.google.android.gms.common.internal.*;
import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.maps.internal.*;
import android.view.*;
import com.google.android.gms.dynamic.*;
import android.app.*;

static class zza implements StreetViewLifecycleDelegate
{
    private final IStreetViewPanoramaFragmentDelegate zzaSz;
    private final Fragment zzavH;
    
    public zza(final Fragment fragment, final IStreetViewPanoramaFragmentDelegate streetViewPanoramaFragmentDelegate) {
        this.zzaSz = zzx.zzz(streetViewPanoramaFragmentDelegate);
        this.zzavH = zzx.zzz(fragment);
    }
    
    @Override
    public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        try {
            this.zzaSz.getStreetViewPanoramaAsync(new zzaa.zza() {
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
    public void onCreate(Bundle bundle) {
        Label_0012: {
            if (bundle != null) {
                break Label_0012;
            }
            try {
                bundle = new Bundle();
                final Bundle arguments = this.zzavH.getArguments();
                if (arguments != null && arguments.containsKey("StreetViewPanoramaOptions")) {
                    zzac.zza(bundle, "StreetViewPanoramaOptions", arguments.getParcelable("StreetViewPanoramaOptions"));
                }
                this.zzaSz.onCreate(bundle);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        try {
            return zze.zzp(this.zzaSz.onCreateView(zze.zzC(layoutInflater), zze.zzC(viewGroup), bundle));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onDestroy() {
        try {
            this.zzaSz.onDestroy();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onDestroyView() {
        try {
            this.zzaSz.onDestroyView();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        try {
            this.zzaSz.onInflate(zze.zzC(activity), null, bundle2);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onLowMemory() {
        try {
            this.zzaSz.onLowMemory();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onPause() {
        try {
            this.zzaSz.onPause();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onResume() {
        try {
            this.zzaSz.onResume();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        try {
            this.zzaSz.onSaveInstanceState(bundle);
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
    
    public IStreetViewPanoramaFragmentDelegate zzzZ() {
        return this.zzaSz;
    }
}
