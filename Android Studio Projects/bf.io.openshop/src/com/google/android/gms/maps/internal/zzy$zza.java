package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.*;
import android.os.*;

public abstract static class zza extends Binder implements zzy
{
    public zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
    }
    
    public static zzy zzcU(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzy) {
            return (zzy)queryLocalInterface;
        }
        return new zzy.zza.zza(binder);
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
                parcel2.writeString("com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
                StreetViewPanoramaOrientation zzfH;
                if (parcel.readInt() != 0) {
                    zzfH = StreetViewPanoramaOrientation.CREATOR.zzfH(parcel);
                }
                else {
                    zzfH = null;
                }
                this.onStreetViewPanoramaClick(zzfH);
                parcel2.writeNoException();
                return true;
            }
        }
    }
    
    private static class zza implements zzy
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public void onStreetViewPanoramaClick(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
                if (streetViewPanoramaOrientation != null) {
                    obtain.writeInt(1);
                    streetViewPanoramaOrientation.writeToParcel(obtain, 0);
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
