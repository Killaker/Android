package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.*;
import android.os.*;

public interface zzx extends IInterface
{
    void onStreetViewPanoramaChange(final StreetViewPanoramaLocation p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzx
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
        }
        
        public static zzx zzcT(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
            if (queryLocalInterface != null && queryLocalInterface instanceof zzx) {
                return (zzx)queryLocalInterface;
            }
            return new zzx.zza.zza(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
                    StreetViewPanoramaLocation zzfG;
                    if (parcel.readInt() != 0) {
                        zzfG = StreetViewPanoramaLocation.CREATOR.zzfG(parcel);
                    }
                    else {
                        zzfG = null;
                    }
                    this.onStreetViewPanoramaChange(zzfG);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class zza implements zzx
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public void onStreetViewPanoramaChange(final StreetViewPanoramaLocation streetViewPanoramaLocation) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
                    if (streetViewPanoramaLocation != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaLocation.writeToParcel(obtain, 0);
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
