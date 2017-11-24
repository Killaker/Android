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

public class MapView extends FrameLayout
{
    private GoogleMap zzaSd;
    private final zzb zzaSj;
    
    public MapView(final Context context) {
        super(context);
        this.zzaSj = new zzb((ViewGroup)this, context, null);
        this.zzex();
    }
    
    public MapView(final Context context, final AttributeSet set) {
        super(context, set);
        this.zzaSj = new zzb((ViewGroup)this, context, GoogleMapOptions.createFromAttributes(context, set));
        this.zzex();
    }
    
    public MapView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.zzaSj = new zzb((ViewGroup)this, context, GoogleMapOptions.createFromAttributes(context, set));
        this.zzex();
    }
    
    public MapView(final Context context, final GoogleMapOptions googleMapOptions) {
        super(context);
        this.zzaSj = new zzb((ViewGroup)this, context, googleMapOptions);
        this.zzex();
    }
    
    private void zzex() {
        this.setClickable(true);
    }
    
    @Deprecated
    public final GoogleMap getMap() {
        if (this.zzaSd != null) {
            return this.zzaSd;
        }
        this.zzaSj.zzzW();
        if (this.zzaSj.zztU() == null) {
            return null;
        }
        try {
            return this.zzaSd = new GoogleMap(this.zzaSj.zztU().zzzX().getMap());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
        zzx.zzcD("getMapAsync() must be called on the main thread");
        this.zzaSj.getMapAsync(onMapReadyCallback);
    }
    
    public final void onCreate(final Bundle bundle) {
        this.zzaSj.onCreate(bundle);
        if (this.zzaSj.zztU() == null) {
            com.google.android.gms.dynamic.zza.zzb(this);
        }
    }
    
    public final void onDestroy() {
        this.zzaSj.onDestroy();
    }
    
    public final void onEnterAmbient(final Bundle bundle) {
        zzx.zzcD("onEnterAmbient() must be called on the main thread");
        this.zzaSj.onEnterAmbient(bundle);
    }
    
    public final void onExitAmbient() {
        zzx.zzcD("onExitAmbient() must be called on the main thread");
        this.zzaSj.onExitAmbient();
    }
    
    public final void onLowMemory() {
        this.zzaSj.onLowMemory();
    }
    
    public final void onPause() {
        this.zzaSj.onPause();
    }
    
    public final void onResume() {
        this.zzaSj.onResume();
    }
    
    public final void onSaveInstanceState(final Bundle bundle) {
        this.zzaSj.onSaveInstanceState(bundle);
    }
    
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
}
