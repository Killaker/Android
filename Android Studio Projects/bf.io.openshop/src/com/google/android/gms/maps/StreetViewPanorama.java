package com.google.android.gms.maps;

import com.google.android.gms.common.internal.*;
import android.os.*;
import android.graphics.*;
import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.internal.*;
import com.google.android.gms.maps.model.*;

public class StreetViewPanorama
{
    private final IStreetViewPanoramaDelegate zzaSr;
    
    protected StreetViewPanorama(final IStreetViewPanoramaDelegate streetViewPanoramaDelegate) {
        this.zzaSr = zzx.zzz(streetViewPanoramaDelegate);
    }
    
    public void animateTo(final StreetViewPanoramaCamera streetViewPanoramaCamera, final long n) {
        try {
            this.zzaSr.animateTo(streetViewPanoramaCamera, n);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public StreetViewPanoramaLocation getLocation() {
        try {
            return this.zzaSr.getStreetViewPanoramaLocation();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public StreetViewPanoramaCamera getPanoramaCamera() {
        try {
            return this.zzaSr.getPanoramaCamera();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isPanningGesturesEnabled() {
        try {
            return this.zzaSr.isPanningGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isStreetNamesEnabled() {
        try {
            return this.zzaSr.isStreetNamesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isUserNavigationEnabled() {
        try {
            return this.zzaSr.isUserNavigationEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isZoomGesturesEnabled() {
        try {
            return this.zzaSr.isZoomGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public Point orientationToPoint(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        try {
            final zzd orientationToPoint = this.zzaSr.orientationToPoint(streetViewPanoramaOrientation);
            if (orientationToPoint == null) {
                return null;
            }
            return (Point)zze.zzp(orientationToPoint);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public StreetViewPanoramaOrientation pointToOrientation(final Point point) {
        try {
            return this.zzaSr.pointToOrientation(zze.zzC(point));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setOnStreetViewPanoramaCameraChangeListener(final OnStreetViewPanoramaCameraChangeListener onStreetViewPanoramaCameraChangeListener) {
        Label_0015: {
            if (onStreetViewPanoramaCameraChangeListener != null) {
                break Label_0015;
            }
            try {
                this.zzaSr.setOnStreetViewPanoramaCameraChangeListener(null);
                return;
                this.zzaSr.setOnStreetViewPanoramaCameraChangeListener(new zzw.zza() {
                    public void onStreetViewPanoramaCameraChange(final StreetViewPanoramaCamera streetViewPanoramaCamera) {
                        onStreetViewPanoramaCameraChangeListener.onStreetViewPanoramaCameraChange(streetViewPanoramaCamera);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnStreetViewPanoramaChangeListener(final OnStreetViewPanoramaChangeListener onStreetViewPanoramaChangeListener) {
        Label_0015: {
            if (onStreetViewPanoramaChangeListener != null) {
                break Label_0015;
            }
            try {
                this.zzaSr.setOnStreetViewPanoramaChangeListener(null);
                return;
                this.zzaSr.setOnStreetViewPanoramaChangeListener(new com.google.android.gms.maps.internal.zzx.zza() {
                    public void onStreetViewPanoramaChange(final StreetViewPanoramaLocation streetViewPanoramaLocation) {
                        onStreetViewPanoramaChangeListener.onStreetViewPanoramaChange(streetViewPanoramaLocation);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnStreetViewPanoramaClickListener(final OnStreetViewPanoramaClickListener onStreetViewPanoramaClickListener) {
        Label_0015: {
            if (onStreetViewPanoramaClickListener != null) {
                break Label_0015;
            }
            try {
                this.zzaSr.setOnStreetViewPanoramaClickListener(null);
                return;
                this.zzaSr.setOnStreetViewPanoramaClickListener(new zzy.zza() {
                    public void onStreetViewPanoramaClick(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
                        onStreetViewPanoramaClickListener.onStreetViewPanoramaClick(streetViewPanoramaOrientation);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnStreetViewPanoramaLongClickListener(final OnStreetViewPanoramaLongClickListener onStreetViewPanoramaLongClickListener) {
        Label_0015: {
            if (onStreetViewPanoramaLongClickListener != null) {
                break Label_0015;
            }
            try {
                this.zzaSr.setOnStreetViewPanoramaLongClickListener(null);
                return;
                this.zzaSr.setOnStreetViewPanoramaLongClickListener(new zzz.zza() {
                    public void onStreetViewPanoramaLongClick(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
                        onStreetViewPanoramaLongClickListener.onStreetViewPanoramaLongClick(streetViewPanoramaOrientation);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public void setPanningGesturesEnabled(final boolean b) {
        try {
            this.zzaSr.enablePanning(b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPosition(final LatLng position) {
        try {
            this.zzaSr.setPosition(position);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPosition(final LatLng latLng, final int n) {
        try {
            this.zzaSr.setPositionWithRadius(latLng, n);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPosition(final String positionWithID) {
        try {
            this.zzaSr.setPositionWithID(positionWithID);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setStreetNamesEnabled(final boolean b) {
        try {
            this.zzaSr.enableStreetNames(b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setUserNavigationEnabled(final boolean b) {
        try {
            this.zzaSr.enableUserNavigation(b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZoomGesturesEnabled(final boolean b) {
        try {
            this.zzaSr.enableZoom(b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    IStreetViewPanoramaDelegate zzzY() {
        return this.zzaSr;
    }
    
    public interface OnStreetViewPanoramaCameraChangeListener
    {
        void onStreetViewPanoramaCameraChange(final StreetViewPanoramaCamera p0);
    }
    
    public interface OnStreetViewPanoramaChangeListener
    {
        void onStreetViewPanoramaChange(final StreetViewPanoramaLocation p0);
    }
    
    public interface OnStreetViewPanoramaClickListener
    {
        void onStreetViewPanoramaClick(final StreetViewPanoramaOrientation p0);
    }
    
    public interface OnStreetViewPanoramaLongClickListener
    {
        void onStreetViewPanoramaLongClick(final StreetViewPanoramaOrientation p0);
    }
}
