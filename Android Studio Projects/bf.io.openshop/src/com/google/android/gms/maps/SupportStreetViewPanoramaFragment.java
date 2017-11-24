package com.google.android.gms.maps;

import android.support.v4.app.*;
import com.google.android.gms.maps.model.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import android.app.*;
import android.view.*;
import android.util.*;
import com.google.android.gms.dynamic.*;
import android.content.*;
import com.google.android.gms.maps.internal.*;
import com.google.android.gms.common.*;
import java.util.*;

public class SupportStreetViewPanoramaFragment extends Fragment
{
    private final zzb zzaSR;
    private StreetViewPanorama zzaSy;
    
    public SupportStreetViewPanoramaFragment() {
        this.zzaSR = new zzb(this);
    }
    
    public static SupportStreetViewPanoramaFragment newInstance() {
        return new SupportStreetViewPanoramaFragment();
    }
    
    public static SupportStreetViewPanoramaFragment newInstance(final StreetViewPanoramaOptions streetViewPanoramaOptions) {
        final SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment = new SupportStreetViewPanoramaFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("StreetViewPanoramaOptions", (Parcelable)streetViewPanoramaOptions);
        supportStreetViewPanoramaFragment.setArguments(arguments);
        return supportStreetViewPanoramaFragment;
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
        this.zzaSR.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }
    
    @Override
    public void onActivityCreated(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }
    
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.zzaSR.setActivity(activity);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.zzaSR.onCreate(bundle);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.zzaSR.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    @Override
    public void onDestroy() {
        this.zzaSR.onDestroy();
        super.onDestroy();
    }
    
    @Override
    public void onDestroyView() {
        this.zzaSR.onDestroyView();
        super.onDestroyView();
    }
    
    @Override
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        this.zzaSR.setActivity(activity);
        this.zzaSR.onInflate(activity, new Bundle(), bundle);
    }
    
    @Override
    public void onLowMemory() {
        this.zzaSR.onLowMemory();
        super.onLowMemory();
    }
    
    @Override
    public void onPause() {
        this.zzaSR.onPause();
        super.onPause();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        this.zzaSR.onResume();
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzaSR.onSaveInstanceState(bundle);
    }
    
    @Override
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
    }
    
    protected IStreetViewPanoramaFragmentDelegate zzzZ() {
        this.zzaSR.zzzW();
        if (this.zzaSR.zztU() == null) {
            return null;
        }
        return this.zzaSR.zztU().zzzZ();
    }
    
    static class zza implements StreetViewLifecycleDelegate
    {
        private final IStreetViewPanoramaFragmentDelegate zzaSz;
        private final Fragment zzalg;
        
        public zza(final Fragment fragment, final IStreetViewPanoramaFragmentDelegate streetViewPanoramaFragmentDelegate) {
            this.zzaSz = zzx.zzz(streetViewPanoramaFragmentDelegate);
            this.zzalg = zzx.zzz(fragment);
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
                    final Bundle arguments = this.zzalg.getArguments();
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
    
    static class zzb extends zza<SupportStreetViewPanoramaFragment.zza>
    {
        private Activity mActivity;
        private final List<OnStreetViewPanoramaReadyCallback> zzaSC;
        protected zzf<SupportStreetViewPanoramaFragment.zza> zzaSh;
        private final Fragment zzalg;
        
        zzb(final Fragment zzalg) {
            this.zzaSC = new ArrayList<OnStreetViewPanoramaReadyCallback>();
            this.zzalg = zzalg;
        }
        
        private void setActivity(final Activity mActivity) {
            this.mActivity = mActivity;
            this.zzzW();
        }
        
        public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            if (this.zztU() != null) {
                ((SupportStreetViewPanoramaFragment.zza)this.zztU()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
                return;
            }
            this.zzaSC.add(onStreetViewPanoramaReadyCallback);
        }
        
        @Override
        protected void zza(final zzf<SupportStreetViewPanoramaFragment.zza> zzaSh) {
            this.zzaSh = zzaSh;
            this.zzzW();
        }
        
        public void zzzW() {
            if (this.mActivity == null || this.zzaSh == null || this.zztU() != null) {
                goto Label_0139;
            }
            try {
                MapsInitializer.initialize((Context)this.mActivity);
                this.zzaSh.zza(new SupportStreetViewPanoramaFragment.zza(this.zzalg, zzad.zzaO((Context)this.mActivity).zzt(zze.zzC(this.mActivity))));
                final Iterator<OnStreetViewPanoramaReadyCallback> iterator = this.zzaSC.iterator();
                while (iterator.hasNext()) {
                    ((SupportStreetViewPanoramaFragment.zza)this.zztU()).getStreetViewPanoramaAsync(iterator.next());
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
