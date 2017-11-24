package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.*;
import android.os.*;
import com.google.android.gms.maps.model.internal.*;
import java.util.*;

public final class IndoorBuilding
{
    private final zzd zzaTs;
    
    public IndoorBuilding(final zzd zzd) {
        this.zzaTs = zzx.zzz(zzd);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof IndoorBuilding)) {
            return false;
        }
        try {
            return this.zzaTs.zzb(((IndoorBuilding)o).zzaTs);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getActiveLevelIndex() {
        try {
            return this.zzaTs.getActiveLevelIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getDefaultLevelIndex() {
        try {
            return this.zzaTs.getActiveLevelIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public List<IndoorLevel> getLevels() {
        ArrayList list;
        try {
            final List<IBinder> levels = this.zzaTs.getLevels();
            list = new ArrayList<IndoorLevel>(levels.size());
            final Iterator<IBinder> iterator = levels.iterator();
            while (iterator.hasNext()) {
                list.add(new IndoorLevel(zze.zza.zzdh(iterator.next())));
            }
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
        return (List<IndoorLevel>)list;
    }
    
    @Override
    public int hashCode() {
        try {
            return this.zzaTs.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isUnderground() {
        try {
            return this.zzaTs.isUnderground();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
