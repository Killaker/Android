package com.google.android.gms.common.internal;

import android.accounts.*;
import android.os.*;

private static class zza implements zzp
{
    private IBinder zzoz;
    
    zza(final IBinder zzoz) {
        this.zzoz = zzoz;
    }
    
    public IBinder asBinder() {
        return this.zzoz;
    }
    
    @Override
    public Account getAccount() throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.IAccountAccessor");
            this.zzoz.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            Account account;
            if (obtain2.readInt() != 0) {
                account = (Account)Account.CREATOR.createFromParcel(obtain2);
            }
            else {
                account = null;
            }
            return account;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
