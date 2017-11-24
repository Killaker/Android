package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;
import com.google.android.gms.maps.model.*;
import android.os.*;

public final class UiSettings
{
    private final IUiSettingsDelegate zzaST;
    
    UiSettings(final IUiSettingsDelegate zzaST) {
        this.zzaST = zzaST;
    }
    
    public boolean isCompassEnabled() {
        try {
            return this.zzaST.isCompassEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isIndoorLevelPickerEnabled() {
        try {
            return this.zzaST.isIndoorLevelPickerEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isMapToolbarEnabled() {
        try {
            return this.zzaST.isMapToolbarEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isMyLocationButtonEnabled() {
        try {
            return this.zzaST.isMyLocationButtonEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isRotateGesturesEnabled() {
        try {
            return this.zzaST.isRotateGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isScrollGesturesEnabled() {
        try {
            return this.zzaST.isScrollGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isTiltGesturesEnabled() {
        try {
            return this.zzaST.isTiltGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isZoomControlsEnabled() {
        try {
            return this.zzaST.isZoomControlsEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isZoomGesturesEnabled() {
        try {
            return this.zzaST.isZoomGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setAllGesturesEnabled(final boolean allGesturesEnabled) {
        try {
            this.zzaST.setAllGesturesEnabled(allGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setCompassEnabled(final boolean compassEnabled) {
        try {
            this.zzaST.setCompassEnabled(compassEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setIndoorLevelPickerEnabled(final boolean indoorLevelPickerEnabled) {
        try {
            this.zzaST.setIndoorLevelPickerEnabled(indoorLevelPickerEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setMapToolbarEnabled(final boolean mapToolbarEnabled) {
        try {
            this.zzaST.setMapToolbarEnabled(mapToolbarEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setMyLocationButtonEnabled(final boolean myLocationButtonEnabled) {
        try {
            this.zzaST.setMyLocationButtonEnabled(myLocationButtonEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setRotateGesturesEnabled(final boolean rotateGesturesEnabled) {
        try {
            this.zzaST.setRotateGesturesEnabled(rotateGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setScrollGesturesEnabled(final boolean scrollGesturesEnabled) {
        try {
            this.zzaST.setScrollGesturesEnabled(scrollGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setTiltGesturesEnabled(final boolean tiltGesturesEnabled) {
        try {
            this.zzaST.setTiltGesturesEnabled(tiltGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZoomControlsEnabled(final boolean zoomControlsEnabled) {
        try {
            this.zzaST.setZoomControlsEnabled(zoomControlsEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZoomGesturesEnabled(final boolean zoomGesturesEnabled) {
        try {
            this.zzaST.setZoomGesturesEnabled(zoomGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
