package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.*;
import android.os.*;

public interface zzz extends IInterface
{
    void onStreetViewPanoramaLongClick(final StreetViewPanoramaOrientation p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzz
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaLongClickListener");
        }
        
        public static zzz zzcV(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaLongClickListener");
            if (queryLocalInterface != null && queryLocalInterface instanceof zzz) {
                return (zzz)queryLocalInterface;
            }
            return new zzz.zza.zza(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnStreetViewPanoramaLongClickListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaLongClickListener");
                    StreetViewPanoramaOrientation zzfH;
                    if (parcel.readInt() != 0) {
                        zzfH = StreetViewPanoramaOrientation.CREATOR.zzfH(parcel);
                    }
                    else {
                        zzfH = null;
                    }
                    this.onStreetViewPanoramaLongClick(zzfH);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class zza implements zzz
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public void onStreetViewPanoramaLongClick(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnStreetViewPanoramaLongClickListener");
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
}
