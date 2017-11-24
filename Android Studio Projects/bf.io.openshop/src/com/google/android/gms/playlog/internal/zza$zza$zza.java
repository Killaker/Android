package com.google.android.gms.playlog.internal;

import android.os.*;
import java.util.*;

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
