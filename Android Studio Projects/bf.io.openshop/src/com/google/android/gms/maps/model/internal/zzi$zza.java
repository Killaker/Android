package com.google.android.gms.maps.model.internal;

import com.google.android.gms.maps.model.*;
import android.os.*;

public abstract static class zza extends Binder implements zzi
{
    public zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }
    
    public static zzi zzdm(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzi) {
            return (zzi)queryLocalInterface;
        }
        return new zzi.zza.zza(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
                final Tile tile = this.getTile(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                if (tile != null) {
                    parcel2.writeInt(1);
                    tile.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
        }
    }
    
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
}
