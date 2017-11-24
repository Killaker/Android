package com.google.android.gms.maps;

import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import android.app.*;
import android.view.*;
import android.util.*;
import android.content.*;
import android.annotation.*;
import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.internal.*;
import com.google.android.gms.common.*;
import java.util.*;

@TargetApi(11)
public class MapFragment extends Fragment
{
    private final zzb zzaSc;
    private GoogleMap zzaSd;
    
    public MapFragment() {
        this.zzaSc = new zzb(this);
    }
    
    public static MapFragment newInstance() {
        return new MapFragment();
    }
    
    public static MapFragment newInstance(final GoogleMapOptions googleMapOptions) {
        final MapFragment mapFragment = new MapFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("MapOptions", (Parcelable)googleMapOptions);
        mapFragment.setArguments(arguments);
        return mapFragment;
    }
    
    @Deprecated
    public final GoogleMap getMap() {
        final IMapFragmentDelegate zzzV = this.zzzV();
        if (zzzV != null) {
            try {
                final IGoogleMapDelegate map = zzzV.getMap();
                if (map != null) {
                    if (this.zzaSd == null || this.zzaSd.zzzJ().asBinder() != map.asBinder()) {
                        this.zzaSd = new GoogleMap(map);
                    }
                    return this.zzaSd;
                }
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        return null;
    }
    
    public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
        zzx.zzcD("getMapAsync must be called on the main thread.");
        this.zzaSc.getMapAsync(onMapReadyCallback);
    }
    
    public void onActivityCreated(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }
    
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.zzaSc.setActivity(activity);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.zzaSc.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = this.zzaSc.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setClickable(true);
        return onCreateView;
    }
    
    public void onDestroy() {
        this.zzaSc.onDestroy();
        super.onDestroy();
    }
    
    public void onDestroyView() {
        this.zzaSc.onDestroyView();
        super.onDestroyView();
    }
    
    public final void onEnterAmbient(final Bundle bundle) {
        zzx.zzcD("onEnterAmbient must be called on the main thread.");
        this.zzaSc.onEnterAmbient(bundle);
    }
    
    public final void onExitAmbient() {
        zzx.zzcD("onExitAmbient must be called on the main thread.");
        this.zzaSc.onExitAmbient();
    }
    
    @SuppressLint({ "NewApi" })
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        this.zzaSc.setActivity(activity);
        final GoogleMapOptions fromAttributes = GoogleMapOptions.createFromAttributes((Context)activity, set);
        final Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", (Parcelable)fromAttributes);
        this.zzaSc.onInflate(activity, bundle2, bundle);
    }
    
    public void onLowMemory() {
        this.zzaSc.onLowMemory();
        super.onLowMemory();
    }
    
    public void onPause() {
        this.zzaSc.onPause();
        super.onPause();
    }
    
    public void onResume() {
        super.onResume();
        this.zzaSc.onResume();
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzaSc.onSaveInstanceState(bundle);
    }
    
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
    }
    
    protected IMapFragmentDelegate zzzV() {
        this.zzaSc.zzzW();
        if (this.zzaSc.zztU() == null) {
            return null;
        }
        return this.zzaSc.zztU().zzzV();
    }
    
    static class zza implements MapLifecycleDelegate
    {
        private final IMapFragmentDelegate zzaSe;
        private final Fragment zzavH;
        
        public zza(final Fragment fragment, final IMapFragmentDelegate mapFragmentDelegate) {
            this.zzaSe = zzx.zzz(mapFragmentDelegate);
            this.zzavH = zzx.zzz(fragment);
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
                    final Bundle arguments = this.zzavH.getArguments();
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
}
