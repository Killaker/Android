package com.google.android.gms.maps.model.internal;

import com.google.android.gms.maps.model.*;
import android.os.*;

private static class zza implements zzi
{
    private IBinder zzoz;
    
    zza(final IBinder zzoz) {
        this.zzoz = zzoz;
    }
    
    public IBinder asBinder() {
        return this.zzoz;
    }
    
    @Override
    public Tile getTile(final int n, final int n2, final int n3) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
            obtain.writeInt(n);
            obtain.writeInt(n2);
            obtain.writeInt(n3);
            this.zzoz.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            Tile zzfI;
            if (obtain2.readInt() != 0) {
                zzfI = Tile.CREATOR.zzfI(obtain2);
            }
            else {
                zzfI = null;
            }
            return zzfI;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
