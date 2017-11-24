package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.*;
import android.os.*;

public abstract static class zza extends Binder implements zzo
{
    public static zzo zzaN(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzo) {
            return (zzo)queryLocalInterface;
        }
        return new zzo.zza.zza(binder);
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
                parcel2.writeString("com.google.android.gms.common.api.internal.IStatusCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.common.api.internal.IStatusCallback");
                Status status;
                if (parcel.readInt() != 0) {
                    status = (Status)Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    status = null;
                }
                this.zzp(status);
                return true;
            }
        }
    }
    
    private static class zza implements zzo
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public void zzp(final Status status) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.common.api.internal.IStatusCallback");
                if (status != null) {
                    obtain.writeInt(1);
                    status.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.zzoz.transact(1, obtain, (Parcel)null, 1);
            }
            finally {
                obtain.recycle();
            }
        }
    }
}
