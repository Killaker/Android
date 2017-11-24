package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.*;
import android.os.*;

public abstract static class zza extends Binder implements zzt
{
    public static zzt zzcP(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnPoiClickListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzt) {
            return (zzt)queryLocalInterface;
        }
        return new zzt.zza.zza(binder);
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
                parcel2.writeString("com.google.android.gms.maps.internal.IOnPoiClickListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnPoiClickListener");
                PointOfInterest zzfB;
                if (parcel.readInt() != 0) {
                    zzfB = PointOfInterest.CREATOR.zzfB(parcel);
                }
                else {
                    zzfB = null;
                }
                this.zza(zzfB);
                parcel2.writeNoException();
                return true;
            }
        }
    }
    
    private static class zza implements zzt
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public void zza(final PointOfInterest pointOfInterest) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnPoiClickListener");
                if (pointOfInterest != null) {
                    obtain.writeInt(1);
                    pointOfInterest.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.zzoz.transact(1, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
