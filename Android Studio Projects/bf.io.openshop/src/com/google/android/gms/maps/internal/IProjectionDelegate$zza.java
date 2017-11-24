package com.google.android.gms.maps.internal;

import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.model.*;
import android.os.*;

public abstract static class zza extends Binder implements IProjectionDelegate
{
    public static IProjectionDelegate zzcX(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof IProjectionDelegate) {
            return (IProjectionDelegate)queryLocalInterface;
        }
        return new IProjectionDelegate.zza.zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.IProjectionDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                final LatLng fromScreenLocation = this.fromScreenLocation(zzd.zza.zzbs(parcel.readStrongBinder()));
                parcel2.writeNoException();
                if (fromScreenLocation != null) {
                    parcel2.writeInt(1);
                    fromScreenLocation.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                LatLng zzfz;
                if (parcel.readInt() != 0) {
                    zzfz = LatLng.CREATOR.zzfz(parcel);
                }
                else {
                    zzfz = null;
                }
                final zzd screenLocation = this.toScreenLocation(zzfz);
                parcel2.writeNoException();
                IBinder binder = null;
                if (screenLocation != null) {
                    binder = screenLocation.asBinder();
                }
                parcel2.writeStrongBinder(binder);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                final VisibleRegion visibleRegion = this.getVisibleRegion();
                parcel2.writeNoException();
                if (visibleRegion != null) {
                    parcel2.writeInt(1);
                    visibleRegion.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
        }
    }
    
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
}
