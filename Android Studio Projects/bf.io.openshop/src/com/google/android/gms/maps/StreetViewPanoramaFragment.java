package com.google.android.gms.maps;

import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import android.app.*;
import android.view.*;
import android.util.*;
import android.annotation.*;
import com.google.android.gms.dynamic.*;
import android.content.*;
import com.google.android.gms.maps.internal.*;
import com.google.android.gms.common.*;
import java.util.*;

@TargetApi(11)
public class StreetViewPanoramaFragment extends Fragment
{
    private final zzb zzaSx;
    private StreetViewPanorama zzaSy;
    
    public StreetViewPanoramaFragment() {
        this.zzaSx = new zzb(this);
    }
    
    public static StreetViewPanoramaFragment newInstance() {
        return new StreetViewPanoramaFragment();
    }
    
    public static StreetViewPanoramaFragment newInstance(final StreetViewPanoramaOptions streetViewPanoramaOptions) {
        final StreetViewPanoramaFragment streetViewPanoramaFragment = new StreetViewPanoramaFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("StreetViewPanoramaOptions", (Parcelable)streetViewPanoramaOptions);
        streetViewPanoramaFragment.setArguments(arguments);
        return streetViewPanoramaFragment;
    }
    
    @Deprecated
    public final StreetViewPanorama getStreetViewPanorama() {
        final IStreetViewPanoramaFragmentDelegate zzzZ = this.zzzZ();
        if (zzzZ != null) {
            try {
                final IStreetViewPanoramaDelegate streetViewPanorama = zzzZ.getStreetViewPanorama();
                if (streetViewPanorama != null) {
                    if (this.zzaSy == null || this.zzaSy.zzzY().asBinder() != streetViewPanorama.asBinder()) {
                        this.zzaSy = new StreetViewPanorama(streetViewPanorama);
                    }
                    return this.zzaSy;
                }
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        return null;
    }
    
    public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        zzx.zzcD("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzaSx.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }
    
    public void onActivityCreated(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }
    
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.zzaSx.setActivity(activity);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.zzaSx.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.zzaSx.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    public void onDestroy() {
        this.zzaSx.onDestroy();
        super.onDestroy();
    }
    
    public void onDestroyView() {
        this.zzaSx.onDestroyView();
        super.onDestroyView();
    }
    
    @SuppressLint({ "NewApi" })
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        this.zzaSx.setActivity(activity);
        this.zzaSx.onInflate(activity, new Bundle(), bundle);
    }
    
    public void onLowMemory() {
        this.zzaSx.onLowMemory();
        super.onLowMemory();
    }
    
    public void onPause() {
        this.zzaSx.onPause();
        super.onPause();
    }
    
    public void onResume() {
        super.onResume();
        this.zzaSx.onResume();
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzaSx.onSaveInstanceState(bundle);
    }
    
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
    }
    
    protected IStreetViewPanoramaFragmentDelegate zzzZ() {
        this.zzaSx.zzzW();
        if (this.zzaSx.zztU() == null) {
            return null;
        }
        return this.zzaSx.zztU().zzzZ();
    }
    
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
    
    static class zzb extends zza<StreetViewPanoramaFragment.zza>
    {
        private Activity mActivity;
        private final List<OnStreetViewPanoramaReadyCallback> zzaSC;
        protected zzf<StreetViewPanoramaFragment.zza> zzaSh;
        private final Fragment zzavH;
        
        zzb(final Fragment zzavH) {
            this.zzaSC = new ArrayList<OnStreetViewPanoramaReadyCallback>();
            this.zzavH = zzavH;
        }
        
        private void setActivity(final Activity mActivity) {
            this.mActivity = mActivity;
            this.zzzW();
        }
        
        public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            if (this.zztU() != null) {
                ((StreetViewPanoramaFragment.zza)this.zztU()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
                return;
            }
            this.zzaSC.add(onStreetViewPanoramaReadyCallback);
        }
        
        @Override
        protected void zza(final zzf<StreetViewPanoramaFragment.zza> zzaSh) {
            this.zzaSh = zzaSh;
            this.zzzW();
        }
        
        public void zzzW() {
            if (this.mActivity == null || this.zzaSh == null || this.zztU() != null) {
                goto Label_0139;
            }
            try {
                MapsInitializer.initialize((Context)this.mActivity);
                this.zzaSh.zza(new StreetViewPanoramaFragment.zza(this.zzavH, zzad.zzaO((Context)this.mActivity).zzt(zze.zzC(this.mActivity))));
                final Iterator<OnStreetViewPanoramaReadyCallback> iterator = this.zzaSC.iterator();
                while (iterator.hasNext()) {
                    ((StreetViewPanoramaFragment.zza)this.zztU()).getStreetViewPanoramaAsync(iterator.next());
                }
                goto Label_0130;
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
