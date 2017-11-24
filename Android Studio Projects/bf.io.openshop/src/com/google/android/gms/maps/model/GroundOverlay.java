package com.google.android.gms.maps.model;

import com.google.android.gms.maps.model.internal.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public final class GroundOverlay
{
    private final zzc zzaTj;
    
    public GroundOverlay(final zzc zzc) {
        this.zzaTj = zzx.zzz(zzc);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof GroundOverlay)) {
            return false;
        }
        try {
            return this.zzaTj.zzb(((GroundOverlay)o).zzaTj);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getBearing() {
        try {
            return this.zzaTj.getBearing();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public LatLngBounds getBounds() {
        try {
            return this.zzaTj.getBounds();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getHeight() {
        try {
            return this.zzaTj.getHeight();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getId() {
        try {
            return this.zzaTj.getId();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public LatLng getPosition() {
        try {
            return this.zzaTj.getPosition();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getTransparency() {
        try {
            return this.zzaTj.getTransparency();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getWidth() {
        try {
            return this.zzaTj.getWidth();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getZIndex() {
        try {
            return this.zzaTj.getZIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.zzaTj.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isClickable() {
        try {
            return this.zzaTj.isClickable();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isVisible() {
        try {
            return this.zzaTj.isVisible();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void remove() {
        try {
            this.zzaTj.remove();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setBearing(final float bearing) {
        try {
            this.zzaTj.setBearing(bearing);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setClickable(final boolean clickable) {
        try {
            this.zzaTj.setClickable(clickable);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setDimensions(final float dimensions) {
        try {
            this.zzaTj.setDimensions(dimensions);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setDimensions(final float n, final float n2) {
        try {
            this.zzaTj.zza(n, n2);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setImage(final BitmapDescriptor bitmapDescriptor) {
        try {
            this.zzaTj.zzv(bitmapDescriptor.zzzH());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPosition(final LatLng position) {
        try {
            this.zzaTj.setPosition(position);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPositionFromBounds(final LatLngBounds positionFromBounds) {
        try {
            this.zzaTj.setPositionFromBounds(positionFromBounds);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setTransparency(final float transparency) {
        try {
            this.zzaTj.setTransparency(transparency);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setVisible(final boolean visible) {
        try {
            this.zzaTj.setVisible(visible);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZIndex(final float zIndex) {
        try {
            this.zzaTj.setZIndex(zIndex);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
