package com.google.android.gms.common.internal;

import android.os.*;

public abstract static class zza extends Binder implements zzt
{
    public static zzt zzaT(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzt) {
            return (zzt)queryLocalInterface;
        }
        return new zzt.zza.zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.common.internal.IResolveAccountCallbacks");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
                ResolveAccountResponse resolveAccountResponse;
                if (parcel.readInt() != 0) {
                    resolveAccountResponse = (ResolveAccountResponse)ResolveAccountResponse.CREATOR.createFromParcel(parcel);
                }
                else {
                    resolveAccountResponse = null;
                }
                this.zza(resolveAccountResponse);
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
        public void zza(final ResolveAccountResponse resolveAccountResponse) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.common.internal.IResolveAccountCallbacks");
                if (resolveAccountResponse != null) {
                    obtain.writeInt(1);
                    resolveAccountResponse.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
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
