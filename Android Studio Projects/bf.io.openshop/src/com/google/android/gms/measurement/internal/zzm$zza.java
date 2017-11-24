package com.google.android.gms.measurement.internal;

import java.util.*;
import android.os.*;

public abstract static class zza extends Binder implements zzm
{
    public zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.measurement.internal.IMeasurementService");
    }
    
    public static zzm zzdn(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzm) {
            return (zzm)queryLocalInterface;
        }
        return new zzm.zza.zza(binder);
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
                parcel2.writeString("com.google.android.gms.measurement.internal.IMeasurementService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                EventParcel zzfN;
                if (parcel.readInt() != 0) {
                    zzfN = EventParcel.CREATOR.zzfN(parcel);
                }
                else {
                    zzfN = null;
                }
                final int int1 = parcel.readInt();
                AppMetadata zzfL = null;
                if (int1 != 0) {
                    zzfL = AppMetadata.CREATOR.zzfL(parcel);
                }
                this.zza(zzfN, zzfL);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                UserAttributeParcel zzfO;
                if (parcel.readInt() != 0) {
                    zzfO = UserAttributeParcel.CREATOR.zzfO(parcel);
                }
                else {
                    zzfO = null;
                }
                final int int2 = parcel.readInt();
                AppMetadata zzfL2 = null;
                if (int2 != 0) {
                    zzfL2 = AppMetadata.CREATOR.zzfL(parcel);
                }
                this.zza(zzfO, zzfL2);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                final int int3 = parcel.readInt();
                AppMetadata zzfL3 = null;
                if (int3 != 0) {
                    zzfL3 = AppMetadata.CREATOR.zzfL(parcel);
                }
                this.zza(zzfL3);
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                final int int4 = parcel.readInt();
                EventParcel zzfN2 = null;
                if (int4 != 0) {
                    zzfN2 = EventParcel.CREATOR.zzfN(parcel);
                }
                this.zza(zzfN2, parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                final int int5 = parcel.readInt();
                AppMetadata zzfL4 = null;
                if (int5 != 0) {
                    zzfL4 = AppMetadata.CREATOR.zzfL(parcel);
                }
                this.zzb(zzfL4);
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                final int int6 = parcel.readInt();
                AppMetadata zzfL5 = null;
                if (int6 != 0) {
                    zzfL5 = AppMetadata.CREATOR.zzfL(parcel);
                }
                final List<UserAttributeParcel> zza = this.zza(zzfL5, parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeTypedList((List)zza);
                return true;
            }
        }
    }
    
    private static class zza implements zzm
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public List<UserAttributeParcel> zza(final AppMetadata appMetadata, final boolean b) throws RemoteException {
        Label_0114_Outer:
            while (true) {
                int n = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                while (true) {
                    while (true) {
                        Label_0119: {
                            try {
                                obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                                if (appMetadata != null) {
                                    obtain.writeInt(1);
                                    appMetadata.writeToParcel(obtain, 0);
                                    break Label_0119;
                                }
                                obtain.writeInt(0);
                                break Label_0119;
                                obtain.writeInt(n);
                                this.zzoz.transact(7, obtain, obtain2, 0);
                                obtain2.readException();
                                return (List<UserAttributeParcel>)obtain2.createTypedArrayList((Parcelable$Creator)UserAttributeParcel.CREATOR);
                            }
                            finally {
                                obtain2.recycle();
                                obtain.recycle();
                            }
                            n = 0;
                            continue Label_0114_Outer;
                        }
                        if (b) {
                            continue Label_0114_Outer;
                        }
                        break;
                    }
                    continue;
                }
            }
        }
        
        @Override
        public void zza(final AppMetadata appMetadata) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                if (appMetadata != null) {
                    obtain.writeInt(1);
                    appMetadata.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.zzoz.transact(4, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void zza(final EventParcel eventParcel, final AppMetadata appMetadata) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                        if (eventParcel != null) {
                            obtain.writeInt(1);
                            eventParcel.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        if (appMetadata != null) {
                            obtain.writeInt(1);
                            appMetadata.writeToParcel(obtain, 0);
                            this.zzoz.transact(1, obtain, obtain2, 0);
                            obtain2.readException();
                            return;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    obtain.writeInt(0);
                    continue;
                }
            }
        }
        
        @Override
        public void zza(final EventParcel eventParcel, final String s, final String s2) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                if (eventParcel != null) {
                    obtain.writeInt(1);
                    eventParcel.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                obtain.writeString(s);
                obtain.writeString(s2);
                this.zzoz.transact(5, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void zza(final UserAttributeParcel userAttributeParcel, final AppMetadata appMetadata) throws RemoteException {
            while (true) {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                        if (userAttributeParcel != null) {
                            obtain.writeInt(1);
                            userAttributeParcel.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        if (appMetadata != null) {
                            obtain.writeInt(1);
                            appMetadata.writeToParcel(obtain, 0);
                            this.zzoz.transact(2, obtain, obtain2, 0);
                            obtain2.readException();
                            return;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    obtain.writeInt(0);
                    continue;
                }
            }
        }
        
        @Override
        public void zzb(final AppMetadata appMetadata) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                if (appMetadata != null) {
                    obtain.writeInt(1);
                    appMetadata.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.zzoz.transact(6, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
