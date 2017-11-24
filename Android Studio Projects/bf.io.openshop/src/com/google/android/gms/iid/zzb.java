package com.google.android.gms.iid;

import android.os.*;

public interface zzb extends IInterface
{
    void send(final Message p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzb
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.iid.IMessengerCompat");
        }
        
        public static zzb zzcd(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.iid.IMessengerCompat");
            if (queryLocalInterface != null && queryLocalInterface instanceof zzb) {
                return (zzb)queryLocalInterface;
            }
            return new zzb.zza.zza(binder);
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
                    parcel2.writeString("com.google.android.gms.iid.IMessengerCompat");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.iid.IMessengerCompat");
                    Message message;
                    if (parcel.readInt() != 0) {
                        message = (Message)Message.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        message = null;
                    }
                    this.send(message);
                    return true;
                }
            }
        }
        
        private static class zza implements zzb
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public void send(final Message message) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
                    if (message != null) {
                        obtain.writeInt(1);
                        message.writeToParcel(obtain, 0);
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
}
