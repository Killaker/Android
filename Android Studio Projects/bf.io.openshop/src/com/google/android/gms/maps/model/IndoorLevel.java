package com.google.android.gms.maps.model;

import com.google.android.gms.maps.model.internal.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public final class IndoorLevel
{
    private final zze zzaTt;
    
    public IndoorLevel(final zze zze) {
        this.zzaTt = zzx.zzz(zze);
    }
    
    public void activate() {
        try {
            this.zzaTt.activate();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof IndoorLevel)) {
            return false;
        }
        try {
            return this.zzaTt.zza(((IndoorLevel)o).zzaTt);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getName() {
        try {
            return this.zzaTt.getName();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getShortName() {
        try {
            return this.zzaTt.getShortName();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.zzaTt.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
