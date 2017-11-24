package com.google.android.gms.maps.internal;

import com.google.android.gms.dynamic.*;
import android.os.*;
import com.google.android.gms.maps.model.*;

private static class zza implements IProjectionDelegate
{
    private IBinder zzoz;
    
    zza(final IBinder zzoz) {
        this.zzoz = zzoz;
    }
    
    public IBinder asBinder() {
        return this.zzoz;
    }
    
    @Override
    public LatLng fromScreenLocation(final zzd zzd) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
            IBinder binder;
            if (zzd != null) {
                binder = zzd.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.zzoz.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            final int int1 = obtain2.readInt();
            LatLng zzfz = null;
            if (int1 != 0) {
                zzfz = LatLng.CREATOR.zzfz(obtain2);
            }
            return zzfz;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public VisibleRegion getVisibleRegion() throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
            this.zzoz.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            VisibleRegion zzfK;
            if (obtain2.readInt() != 0) {
                zzfK = VisibleRegion.CREATOR.zzfK(obtain2);
            }
            else {
                zzfK = null;
            }
            return zzfK;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public zzd toScreenLocation(final LatLng latLng) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
            if (latLng != null) {
                obtain.writeInt(1);
                latLng.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.zzoz.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return zzd.zza.zzbs(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
