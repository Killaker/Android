package com.google.android.gms.maps;

import com.google.android.gms.common.internal.*;
import android.os.*;
import android.location.*;
import com.google.android.gms.dynamic.*;
import android.support.annotation.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.model.internal.*;
import android.graphics.*;
import com.google.android.gms.maps.internal.*;
import android.view.*;

public final class GoogleMap
{
    public static final int MAP_TYPE_HYBRID = 4;
    public static final int MAP_TYPE_NONE = 0;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;
    private final IGoogleMapDelegate zzaRr;
    private UiSettings zzaRs;
    
    protected GoogleMap(final IGoogleMapDelegate googleMapDelegate) {
        this.zzaRr = zzx.zzz(googleMapDelegate);
    }
    
    public final Circle addCircle(final CircleOptions circleOptions) {
        try {
            return new Circle(this.zzaRr.addCircle(circleOptions));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final GroundOverlay addGroundOverlay(final GroundOverlayOptions groundOverlayOptions) {
        try {
            final zzc addGroundOverlay = this.zzaRr.addGroundOverlay(groundOverlayOptions);
            if (addGroundOverlay != null) {
                return new GroundOverlay(addGroundOverlay);
            }
            return null;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final Marker addMarker(final MarkerOptions markerOptions) {
        try {
            final zzf addMarker = this.zzaRr.addMarker(markerOptions);
            if (addMarker != null) {
                return new Marker(addMarker);
            }
            return null;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final Polygon addPolygon(final PolygonOptions polygonOptions) {
        try {
            return new Polygon(this.zzaRr.addPolygon(polygonOptions));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final Polyline addPolyline(final PolylineOptions polylineOptions) {
        try {
            return new Polyline(this.zzaRr.addPolyline(polylineOptions));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final TileOverlay addTileOverlay(final TileOverlayOptions tileOverlayOptions) {
        try {
            final zzh addTileOverlay = this.zzaRr.addTileOverlay(tileOverlayOptions);
            if (addTileOverlay != null) {
                return new TileOverlay(addTileOverlay);
            }
            return null;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void animateCamera(final CameraUpdate cameraUpdate) {
        try {
            this.zzaRr.animateCamera(cameraUpdate.zzzH());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void animateCamera(final CameraUpdate cameraUpdate, final int n, final CancelableCallback cancelableCallback) {
        try {
            final IGoogleMapDelegate zzaRr = this.zzaRr;
            final zzd zzzH = cameraUpdate.zzzH();
            zzb zzb;
            if (cancelableCallback == null) {
                zzb = null;
            }
            else {
                zzb = new zza(cancelableCallback);
            }
            zzaRr.animateCameraWithDurationAndCallback(zzzH, n, zzb);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void animateCamera(final CameraUpdate cameraUpdate, final CancelableCallback cancelableCallback) {
        try {
            final IGoogleMapDelegate zzaRr = this.zzaRr;
            final zzd zzzH = cameraUpdate.zzzH();
            zzb zzb;
            if (cancelableCallback == null) {
                zzb = null;
            }
            else {
                zzb = new zza(cancelableCallback);
            }
            zzaRr.animateCameraWithCallback(zzzH, zzb);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void clear() {
        try {
            this.zzaRr.clear();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final CameraPosition getCameraPosition() {
        try {
            return this.zzaRr.getCameraPosition();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public IndoorBuilding getFocusedBuilding() {
        try {
            final com.google.android.gms.maps.model.internal.zzd focusedBuilding = this.zzaRr.getFocusedBuilding();
            if (focusedBuilding != null) {
                return new IndoorBuilding(focusedBuilding);
            }
            return null;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final int getMapType() {
        try {
            return this.zzaRr.getMapType();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final float getMaxZoomLevel() {
        try {
            return this.zzaRr.getMaxZoomLevel();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final float getMinZoomLevel() {
        try {
            return this.zzaRr.getMinZoomLevel();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Deprecated
    public final Location getMyLocation() {
        try {
            return this.zzaRr.getMyLocation();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final Projection getProjection() {
        try {
            return new Projection(this.zzaRr.getProjection());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final UiSettings getUiSettings() {
        try {
            if (this.zzaRs == null) {
                this.zzaRs = new UiSettings(this.zzaRr.getUiSettings());
            }
            return this.zzaRs;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean isBuildingsEnabled() {
        try {
            return this.zzaRr.isBuildingsEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean isIndoorEnabled() {
        try {
            return this.zzaRr.isIndoorEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean isMyLocationEnabled() {
        try {
            return this.zzaRr.isMyLocationEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean isTrafficEnabled() {
        try {
            return this.zzaRr.isTrafficEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void moveCamera(final CameraUpdate cameraUpdate) {
        try {
            this.zzaRr.moveCamera(cameraUpdate.zzzH());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setBuildingsEnabled(final boolean buildingsEnabled) {
        try {
            this.zzaRr.setBuildingsEnabled(buildingsEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setContentDescription(final String contentDescription) {
        try {
            this.zzaRr.setContentDescription(contentDescription);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean setIndoorEnabled(final boolean indoorEnabled) {
        try {
            return this.zzaRr.setIndoorEnabled(indoorEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setInfoWindowAdapter(final InfoWindowAdapter infoWindowAdapter) {
        Label_0015: {
            if (infoWindowAdapter != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setInfoWindowAdapter(null);
                return;
                this.zzaRr.setInfoWindowAdapter(new com.google.android.gms.maps.internal.zzd.zza() {
                    public com.google.android.gms.dynamic.zzd zzb(final zzf zzf) {
                        return zze.zzC(infoWindowAdapter.getInfoWindow(new Marker(zzf)));
                    }
                    
                    public com.google.android.gms.dynamic.zzd zzc(final zzf zzf) {
                        return zze.zzC(infoWindowAdapter.getInfoContents(new Marker(zzf)));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setLocationSource(final LocationSource locationSource) {
        Label_0015: {
            if (locationSource != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setLocationSource(null);
                return;
                this.zzaRr.setLocationSource(new ILocationSourceDelegate.zza() {
                    public void activate(final zzk zzk) {
                        locationSource.activate((LocationSource.OnLocationChangedListener)new LocationSource.OnLocationChangedListener() {
                            @Override
                            public void onLocationChanged(final Location location) {
                                try {
                                    zzk.zzd(location);
                                }
                                catch (RemoteException ex) {
                                    throw new RuntimeRemoteException(ex);
                                }
                            }
                        });
                    }
                    
                    public void deactivate() {
                        locationSource.deactivate();
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setMapType(final int mapType) {
        try {
            this.zzaRr.setMapType(mapType);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    public final void setMyLocationEnabled(final boolean myLocationEnabled) {
        try {
            this.zzaRr.setMyLocationEnabled(myLocationEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setOnCameraChangeListener(final OnCameraChangeListener onCameraChangeListener) {
        Label_0015: {
            if (onCameraChangeListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnCameraChangeListener(null);
                return;
                this.zzaRr.setOnCameraChangeListener(new com.google.android.gms.maps.internal.zze.zza() {
                    public void onCameraChange(final CameraPosition cameraPosition) {
                        onCameraChangeListener.onCameraChange(cameraPosition);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnGroundOverlayClickListener(final OnGroundOverlayClickListener onGroundOverlayClickListener) {
        Label_0015: {
            if (onGroundOverlayClickListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnGroundOverlayClickListener(null);
                return;
                this.zzaRr.setOnGroundOverlayClickListener(new com.google.android.gms.maps.internal.zzf.zza() {
                    public void zza(final zzc zzc) {
                        onGroundOverlayClickListener.onGroundOverlayClick(new GroundOverlay(zzc));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnIndoorStateChangeListener(final OnIndoorStateChangeListener onIndoorStateChangeListener) {
        Label_0015: {
            if (onIndoorStateChangeListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnIndoorStateChangeListener(null);
                return;
                this.zzaRr.setOnIndoorStateChangeListener(new zzg.zza() {
                    public void onIndoorBuildingFocused() {
                        onIndoorStateChangeListener.onIndoorBuildingFocused();
                    }
                    
                    public void zza(final com.google.android.gms.maps.model.internal.zzd zzd) {
                        onIndoorStateChangeListener.onIndoorLevelActivated(new IndoorBuilding(zzd));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnInfoWindowClickListener(final OnInfoWindowClickListener onInfoWindowClickListener) {
        Label_0015: {
            if (onInfoWindowClickListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnInfoWindowClickListener(null);
                return;
                this.zzaRr.setOnInfoWindowClickListener(new com.google.android.gms.maps.internal.zzh.zza() {
                    public void zzh(final zzf zzf) {
                        onInfoWindowClickListener.onInfoWindowClick(new Marker(zzf));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnInfoWindowCloseListener(final OnInfoWindowCloseListener onInfoWindowCloseListener) {
        Label_0015: {
            if (onInfoWindowCloseListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnInfoWindowCloseListener(null);
                return;
                this.zzaRr.setOnInfoWindowCloseListener(new zzi.zza() {
                    public void zza(final zzf zzf) {
                        onInfoWindowCloseListener.onInfoWindowClose(new Marker(zzf));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnInfoWindowLongClickListener(final OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        Label_0015: {
            if (onInfoWindowLongClickListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnInfoWindowLongClickListener(null);
                return;
                this.zzaRr.setOnInfoWindowLongClickListener(new zzj.zza() {
                    public void zzi(final zzf zzf) {
                        onInfoWindowLongClickListener.onInfoWindowLongClick(new Marker(zzf));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMapClickListener(final OnMapClickListener onMapClickListener) {
        Label_0015: {
            if (onMapClickListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnMapClickListener(null);
                return;
                this.zzaRr.setOnMapClickListener(new zzl.zza() {
                    public void onMapClick(final LatLng latLng) {
                        onMapClickListener.onMapClick(latLng);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public void setOnMapLoadedCallback(final OnMapLoadedCallback onMapLoadedCallback) {
        Label_0015: {
            if (onMapLoadedCallback != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnMapLoadedCallback(null);
                return;
                this.zzaRr.setOnMapLoadedCallback(new zzm.zza() {
                    public void onMapLoaded() throws RemoteException {
                        onMapLoadedCallback.onMapLoaded();
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMapLongClickListener(final OnMapLongClickListener onMapLongClickListener) {
        Label_0015: {
            if (onMapLongClickListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnMapLongClickListener(null);
                return;
                this.zzaRr.setOnMapLongClickListener(new zzn.zza() {
                    public void onMapLongClick(final LatLng latLng) {
                        onMapLongClickListener.onMapLongClick(latLng);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMarkerClickListener(final OnMarkerClickListener onMarkerClickListener) {
        Label_0015: {
            if (onMarkerClickListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnMarkerClickListener(null);
                return;
                this.zzaRr.setOnMarkerClickListener(new zzp.zza() {
                    public boolean zzd(final zzf zzf) {
                        return onMarkerClickListener.onMarkerClick(new Marker(zzf));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMarkerDragListener(final OnMarkerDragListener onMarkerDragListener) {
        Label_0015: {
            if (onMarkerDragListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnMarkerDragListener(null);
                return;
                this.zzaRr.setOnMarkerDragListener(new zzq.zza() {
                    public void zze(final zzf zzf) {
                        onMarkerDragListener.onMarkerDragStart(new Marker(zzf));
                    }
                    
                    public void zzf(final zzf zzf) {
                        onMarkerDragListener.onMarkerDragEnd(new Marker(zzf));
                    }
                    
                    public void zzg(final zzf zzf) {
                        onMarkerDragListener.onMarkerDrag(new Marker(zzf));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMyLocationButtonClickListener(final OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
        Label_0015: {
            if (onMyLocationButtonClickListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnMyLocationButtonClickListener(null);
                return;
                this.zzaRr.setOnMyLocationButtonClickListener(new zzr.zza() {
                    public boolean onMyLocationButtonClick() throws RemoteException {
                        return onMyLocationButtonClickListener.onMyLocationButtonClick();
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    @Deprecated
    public final void setOnMyLocationChangeListener(final OnMyLocationChangeListener onMyLocationChangeListener) {
        Label_0015: {
            if (onMyLocationChangeListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnMyLocationChangeListener(null);
                return;
                this.zzaRr.setOnMyLocationChangeListener(new zzs.zza() {
                    public void zzq(final zzd zzd) {
                        onMyLocationChangeListener.onMyLocationChange(zze.zzp(zzd));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnPolygonClickListener(final OnPolygonClickListener onPolygonClickListener) {
        Label_0015: {
            if (onPolygonClickListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnPolygonClickListener(null);
                return;
                this.zzaRr.setOnPolygonClickListener(new zzu.zza() {
                    public void zza(final com.google.android.gms.maps.model.internal.zzg zzg) {
                        onPolygonClickListener.onPolygonClick(new Polygon(zzg));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnPolylineClickListener(final OnPolylineClickListener onPolylineClickListener) {
        Label_0015: {
            if (onPolylineClickListener != null) {
                break Label_0015;
            }
            try {
                this.zzaRr.setOnPolylineClickListener(null);
                return;
                this.zzaRr.setOnPolylineClickListener(new zzv.zza() {
                    public void zza(final IPolylineDelegate polylineDelegate) {
                        onPolylineClickListener.onPolylineClick(new Polyline(polylineDelegate));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setPadding(final int n, final int n2, final int n3, final int n4) {
        try {
            this.zzaRr.setPadding(n, n2, n3, n4);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setTrafficEnabled(final boolean trafficEnabled) {
        try {
            this.zzaRr.setTrafficEnabled(trafficEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void snapshot(final SnapshotReadyCallback snapshotReadyCallback) {
        this.snapshot(snapshotReadyCallback, null);
    }
    
    public final void snapshot(final SnapshotReadyCallback snapshotReadyCallback, final Bitmap bitmap) {
        Label_0039: {
            if (bitmap == null) {
                break Label_0039;
            }
            zzd zzC = zze.zzC(bitmap);
            while (true) {
                final zze zze = (zze)zzC;
                try {
                    this.zzaRr.snapshot(new zzab.zza() {
                        public void onSnapshotReady(final Bitmap bitmap) throws RemoteException {
                            snapshotReadyCallback.onSnapshotReady(bitmap);
                        }
                        
                        public void zzr(final zzd zzd) throws RemoteException {
                            snapshotReadyCallback.onSnapshotReady(com.google.android.gms.dynamic.zze.zzp(zzd));
                        }
                    }, zze);
                    return;
                    zzC = null;
                }
                catch (RemoteException ex) {
                    throw new RuntimeRemoteException(ex);
                }
            }
        }
    }
    
    public final void stopAnimation() {
        try {
            this.zzaRr.stopAnimation();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    IGoogleMapDelegate zzzJ() {
        return this.zzaRr;
    }
    
    public interface CancelableCallback
    {
        void onCancel();
        
        void onFinish();
    }
    
    public interface InfoWindowAdapter
    {
        View getInfoContents(final Marker p0);
        
        View getInfoWindow(final Marker p0);
    }
    
    public interface OnCameraChangeListener
    {
        void onCameraChange(final CameraPosition p0);
    }
    
    public interface OnGroundOverlayClickListener
    {
        void onGroundOverlayClick(final GroundOverlay p0);
    }
    
    public interface OnIndoorStateChangeListener
    {
        void onIndoorBuildingFocused();
        
        void onIndoorLevelActivated(final IndoorBuilding p0);
    }
    
    public interface OnInfoWindowClickListener
    {
        void onInfoWindowClick(final Marker p0);
    }
    
    public interface OnInfoWindowCloseListener
    {
        void onInfoWindowClose(final Marker p0);
    }
    
    public interface OnInfoWindowLongClickListener
    {
        void onInfoWindowLongClick(final Marker p0);
    }
    
    public interface OnMapClickListener
    {
        void onMapClick(final LatLng p0);
    }
    
    public interface OnMapLoadedCallback
    {
        void onMapLoaded();
    }
    
    public interface OnMapLongClickListener
    {
        void onMapLongClick(final LatLng p0);
    }
    
    public interface OnMarkerClickListener
    {
        boolean onMarkerClick(final Marker p0);
    }
    
    public interface OnMarkerDragListener
    {
        void onMarkerDrag(final Marker p0);
        
        void onMarkerDragEnd(final Marker p0);
        
        void onMarkerDragStart(final Marker p0);
    }
    
    public interface OnMyLocationButtonClickListener
    {
        boolean onMyLocationButtonClick();
    }
    
    @Deprecated
    public interface OnMyLocationChangeListener
    {
        void onMyLocationChange(final Location p0);
    }
    
    public interface OnPolygonClickListener
    {
        void onPolygonClick(final Polygon p0);
    }
    
    public interface OnPolylineClickListener
    {
        void onPolylineClick(final Polyline p0);
    }
    
    public interface SnapshotReadyCallback
    {
        void onSnapshotReady(final Bitmap p0);
    }
    
    private static final class zza extends zzb.zza
    {
        private final CancelableCallback zzaRO;
        
        zza(final CancelableCallback zzaRO) {
            this.zzaRO = zzaRO;
        }
        
        public void onCancel() {
            this.zzaRO.onCancel();
        }
        
        public void onFinish() {
            this.zzaRO.onFinish();
        }
    }
}
