package com.google.android.gms.internal;

import android.os.*;
import com.google.android.gms.dynamic.*;

private static class zza implements zzpk
{
    private IBinder zzoz;
    
    zza(final IBinder zzoz) {
        this.zzoz = zzoz;
    }
    
    public IBinder asBinder() {
        return this.zzoz;
    }
    
    @Override
    public boolean getBooleanFlagValue(final String s, final boolean b, final int n) throws RemoteException {
        int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.flags.IFlagProvider");
            obtain.writeString(s);
            int n3;
            if (b) {
                n3 = n2;
            }
            else {
                n3 = 0;
            }
            obtain.writeInt(n3);
            obtain.writeInt(n);
            this.zzoz.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() == 0) {
                n2 = 0;
            }
            return n2 != 0;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public int getIntFlagValue(final String s, final int n, final int n2) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.flags.IFlagProvider");
            obtain.writeString(s);
            obtain.writeInt(n);
            obtain.writeInt(n2);
            this.zzoz.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public long getLongFlagValue(final String s, final long n, final int n2) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.flags.IFlagProvider");
            obtain.writeString(s);
            obtain.writeLong(n);
            obtain.writeInt(n2);
            this.zzoz.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readLong();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public String getStringFlagValue(final String s, final String s2, final int n) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.flags.IFlagProvider");
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeInt(n);
            this.zzoz.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void init(final zzd zzd) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.flags.IFlagProvider");
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
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
