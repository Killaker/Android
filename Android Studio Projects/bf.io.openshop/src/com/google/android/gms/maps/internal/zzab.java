package com.google.android.gms.maps.internal;

import android.graphics.*;
import com.google.android.gms.dynamic.*;
import android.os.*;

public interface zzab extends IInterface
{
    void onSnapshotReady(final Bitmap p0) throws RemoteException;
    
    void zzr(final zzd p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzab
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.ISnapshotReadyCallback");
        }
        
        public static zzab zzcY(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
            if (queryLocalInterface != null && queryLocalInterface instanceof zzab) {
                return (zzab)queryLocalInterface;
            }
            return new zzab.zza.zza(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
                    Bitmap bitmap;
                    if (parcel.readInt() != 0) {
                        bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bitmap = null;
                    }
                    this.onSnapshotReady(bitmap);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
                    this.zzr(zzd.zza.zzbs(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class zza implements zzab
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public void onSnapshotReady(final Bitmap bitmap) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
                    if (bitmap != null) {
                        obtain.writeInt(1);
                        bitmap.writeToParcel(obtain, 0);
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
            
            @Override
            public void zzr(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzoz.transact(2, obtain, obtain2, 0);
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
