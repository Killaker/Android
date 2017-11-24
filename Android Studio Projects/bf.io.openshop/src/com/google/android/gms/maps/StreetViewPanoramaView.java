package com.google.android.gms.maps;

import android.widget.*;
import android.content.*;
import android.util.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.common.internal.*;
import android.os.*;
import android.view.*;
import android.app.*;
import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.internal.*;
import com.google.android.gms.common.*;
import java.util.*;

public class StreetViewPanoramaView extends FrameLayout
{
    private final zzb zzaSK;
    private StreetViewPanorama zzaSy;
    
    public StreetViewPanoramaView(final Context context) {
        super(context);
        this.zzaSK = new zzb((ViewGroup)this, context, null);
    }
    
    public StreetViewPanoramaView(final Context context, final AttributeSet set) {
        super(context, set);
        this.zzaSK = new zzb((ViewGroup)this, context, null);
    }
    
    public StreetViewPanoramaView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.zzaSK = new zzb((ViewGroup)this, context, null);
    }
    
    public StreetViewPanoramaView(final Context context, final StreetViewPanoramaOptions streetViewPanoramaOptions) {
        super(context);
        this.zzaSK = new zzb((ViewGroup)this, context, streetViewPanoramaOptions);
    }
    
    @Deprecated
    public final StreetViewPanorama getStreetViewPanorama() {
        if (this.zzaSy != null) {
            return this.zzaSy;
        }
        this.zzaSK.zzzW();
        if (this.zzaSK.zztU() == null) {
            return null;
        }
        try {
            return this.zzaSy = new StreetViewPanorama(this.zzaSK.zztU().zzAd().getStreetViewPanorama());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        zzx.zzcD("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzaSK.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }
    
    public final void onCreate(final Bundle bundle) {
        this.zzaSK.onCreate(bundle);
        if (this.zzaSK.zztU() == null) {
            com.google.android.gms.dynamic.zza.zzb(this);
        }
    }
    
    public final void onDestroy() {
        this.zzaSK.onDestroy();
    }
    
    public final void onLowMemory() {
        this.zzaSK.onLowMemory();
    }
    
    public final void onPause() {
        this.zzaSK.onPause();
    }
    
    public final void onResume() {
        this.zzaSK.onResume();
    }
    
    public final void onSaveInstanceState(final Bundle bundle) {
        this.zzaSK.onSaveInstanceState(bundle);
    }
    
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
    
    static class zzb extends zza<StreetViewPanoramaView.zza>
    {
        private final Context mContext;
        private final List<OnStreetViewPanoramaReadyCallback> zzaSC;
        private final StreetViewPanoramaOptions zzaSO;
        protected zzf<StreetViewPanoramaView.zza> zzaSh;
        private final ViewGroup zzaSo;
        
        zzb(final ViewGroup zzaSo, final Context mContext, final StreetViewPanoramaOptions zzaSO) {
            this.zzaSC = new ArrayList<OnStreetViewPanoramaReadyCallback>();
            this.zzaSo = zzaSo;
            this.mContext = mContext;
            this.zzaSO = zzaSO;
        }
        
        public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            if (this.zztU() != null) {
                ((StreetViewPanoramaView.zza)this.zztU()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
                return;
            }
            this.zzaSC.add(onStreetViewPanoramaReadyCallback);
        }
        
        @Override
        protected void zza(final zzf<StreetViewPanoramaView.zza> zzaSh) {
            this.zzaSh = zzaSh;
            this.zzzW();
        }
        
        public void zzzW() {
            if (this.zzaSh == null || this.zztU() != null) {
                goto Label_0126;
            }
            try {
                this.zzaSh.zza(new StreetViewPanoramaView.zza(this.zzaSo, zzad.zzaO(this.mContext).zza(zze.zzC(this.mContext), this.zzaSO)));
                final Iterator<OnStreetViewPanoramaReadyCallback> iterator = this.zzaSC.iterator();
                while (iterator.hasNext()) {
                    ((StreetViewPanoramaView.zza)this.zztU()).getStreetViewPanoramaAsync(iterator.next());
                }
                goto Label_0117;
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
