package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.*;
import android.os.*;

public abstract static class zza extends Binder implements zzn
{
    public zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnMapLongClickListener");
    }
    
    public static zzn zzcJ(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMapLongClickListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzn) {
            return (zzn)queryLocalInterface;
        }
        return new zzn.zza.zza(binder);
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
                parcel2.writeString("com.google.android.gms.maps.internal.IOnMapLongClickListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMapLongClickListener");
                LatLng zzfz;
                if (parcel.readInt() != 0) {
                    zzfz = LatLng.CREATOR.zzfz(parcel);
                }
                else {
                    zzfz = null;
                }
                this.onMapLongClick(zzfz);
                parcel2.writeNoException();
                return true;
            }
        }
    }
    
    private static class zza implements zzn
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public void onMapLongClick(final LatLng latLng) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMapLongClickListener");
                if (latLng != null) {
                    obtain.writeInt(1);
                    latLng.writeToParcel(obtain, 0);
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
