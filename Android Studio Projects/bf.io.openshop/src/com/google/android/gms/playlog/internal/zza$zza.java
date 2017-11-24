package com.google.android.gms.playlog.internal;

import java.util.*;
import android.os.*;

public abstract static class zza extends Binder implements zza
{
    public static zza zzdN(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.playlog.internal.IPlayLogService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zza) {
            return (zza)queryLocalInterface;
        }
        return new zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.playlog.internal.IPlayLogService");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
                final String string = parcel.readString();
                PlayLoggerContext zzgz;
                if (parcel.readInt() != 0) {
                    zzgz = PlayLoggerContext.CREATOR.zzgz(parcel);
                }
                else {
                    zzgz = null;
                }
                final int int1 = parcel.readInt();
                LogEvent zzgy = null;
                if (int1 != 0) {
                    zzgy = LogEvent.CREATOR.zzgy(parcel);
                }
                this.zza(string, zzgz, zzgy);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
                final String string2 = parcel.readString();
                final int int2 = parcel.readInt();
                PlayLoggerContext zzgz2 = null;
                if (int2 != 0) {
                    zzgz2 = PlayLoggerContext.CREATOR.zzgz(parcel);
                }
                this.zza(string2, zzgz2, parcel.createTypedArrayList((Parcelable$Creator)LogEvent.CREATOR));
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
                final String string3 = parcel.readString();
                final int int3 = parcel.readInt();
                PlayLoggerContext zzgz3 = null;
                if (int3 != 0) {
                    zzgz3 = PlayLoggerContext.CREATOR.zzgz(parcel);
                }
                this.zza(string3, zzgz3, parcel.createByteArray());
                return true;
            }
        }
    }
    
    private static class zza implements com.google.android.gms.playlog.internal.zza
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public void zza(final String s, final PlayLoggerContext playLoggerContext, final LogEvent logEvent) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
                        obtain.writeString(s);
                        if (playLoggerContext != null) {
                            obtain.writeInt(1);
                            playLoggerContext.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        if (logEvent != null) {
                            obtain.writeInt(1);
                            logEvent.writeToParcel(obtain, 0);
                            this.zzoz.transact(2, obtain, (Parcel)null, 1);
                            return;
                        }
                    }
                    finally {
                        obtain.recycle();
                    }
                    obtain.writeInt(0);
                    continue;
                }
            }
        }
        
        @Override
        public void zza(final String s, final PlayLoggerContext playLoggerContext, final List<LogEvent> list) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
                obtain.writeString(s);
                if (playLoggerContext != null) {
                    obtain.writeInt(1);
                    playLoggerContext.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                obtain.writeTypedList((List)list);
                this.zzoz.transact(3, obtain, (Parcel)null, 1);
            }
            finally {
                obtain.recycle();
            }
        }
        
        @Override
        public void zza(final String s, final PlayLoggerContext playLoggerContext, final byte[] array) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
                obtain.writeString(s);
                if (playLoggerContext != null) {
                    obtain.writeInt(1);
                    playLoggerContext.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                obtain.writeByteArray(array);
                this.zzoz.transact(4, obtain, (Parcel)null, 1);
            }
            finally {
                obtain.recycle();
            }
        }
    }
}
