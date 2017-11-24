package com.google.android.gms.maps.model;

import com.google.android.gms.maps.model.internal.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public final class Marker
{
    private final zzf zzaTy;
    
    public Marker(final zzf zzf) {
        this.zzaTy = zzx.zzz(zzf);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Marker)) {
            return false;
        }
        try {
            return this.zzaTy.zzj(((Marker)o).zzaTy);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getAlpha() {
        try {
            return this.zzaTy.getAlpha();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getId() {
        try {
            return this.zzaTy.getId();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public LatLng getPosition() {
        try {
            return this.zzaTy.getPosition();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getRotation() {
        try {
            return this.zzaTy.getRotation();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getSnippet() {
        try {
            return this.zzaTy.getSnippet();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getTitle() {
        try {
            return this.zzaTy.getTitle();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.zzaTy.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void hideInfoWindow() {
        try {
            this.zzaTy.hideInfoWindow();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isDraggable() {
        try {
            return this.zzaTy.isDraggable();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isFlat() {
        try {
            return this.zzaTy.isFlat();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isInfoWindowShown() {
        try {
            return this.zzaTy.isInfoWindowShown();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isVisible() {
        try {
            return this.zzaTy.isVisible();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void remove() {
        try {
            this.zzaTy.remove();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setAlpha(final float alpha) {
        try {
            this.zzaTy.setAlpha(alpha);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setAnchor(final float n, final float n2) {
        try {
            this.zzaTy.setAnchor(n, n2);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setDraggable(final boolean draggable) {
        try {
            this.zzaTy.setDraggable(draggable);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setFlat(final boolean flat) {
        try {
            this.zzaTy.setFlat(flat);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setIcon(final BitmapDescriptor bitmapDescriptor) {
        try {
            this.zzaTy.zzw(bitmapDescriptor.zzzH());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setInfoWindowAnchor(final float n, final float n2) {
        try {
            this.zzaTy.setInfoWindowAnchor(n, n2);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPosition(final LatLng position) {
        try {
            this.zzaTy.setPosition(position);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setRotation(final float rotation) {
        try {
            this.zzaTy.setRotation(rotation);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setSnippet(final String snippet) {
        try {
            this.zzaTy.setSnippet(snippet);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setTitle(final String title) {
        try {
            this.zzaTy.setTitle(title);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setVisible(final boolean visible) {
        try {
            this.zzaTy.setVisible(visible);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void showInfoWindow() {
        try {
            this.zzaTy.showInfoWindow();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
