package com.google.android.gms.iid;

import android.annotation.*;
import android.os.*;

public class MessengerCompat implements Parcelable
{
    public static final Parcelable$Creator<MessengerCompat> CREATOR;
    Messenger zzaNd;
    zzb zzaNe;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<MessengerCompat>() {
            public MessengerCompat zzeO(final Parcel parcel) {
                final IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    return new MessengerCompat(strongBinder);
                }
                return null;
            }
            
            public MessengerCompat[] zzhm(final int n) {
                return new MessengerCompat[n];
            }
        };
    }
    
    public MessengerCompat(final Handler handler) {
        if (Build$VERSION.SDK_INT >= 21) {
            this.zzaNd = new Messenger(handler);
            return;
        }
        this.zzaNe = new zza(handler);
    }
    
    public MessengerCompat(final IBinder binder) {
        if (Build$VERSION.SDK_INT >= 21) {
            this.zzaNd = new Messenger(binder);
            return;
        }
        this.zzaNe = zzb.zza.zzcd(binder);
    }
    
    public static int zzc(final Message message) {
        if (Build$VERSION.SDK_INT >= 21) {
            return zzd(message);
        }
        return message.arg2;
    }
    
    @TargetApi(21)
    private static int zzd(final Message message) {
        return message.sendingUid;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        try {
            return this.getBinder().equals(((MessengerCompat)o).getBinder());
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public IBinder getBinder() {
        if (this.zzaNd != null) {
            return this.zzaNd.getBinder();
        }
        return this.zzaNe.asBinder();
    }
    
    @Override
    public int hashCode() {
        return this.getBinder().hashCode();
    }
    
    public void send(final Message message) throws RemoteException {
        if (this.zzaNd != null) {
            this.zzaNd.send(message);
            return;
        }
        this.zzaNe.send(message);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (this.zzaNd != null) {
            parcel.writeStrongBinder(this.zzaNd.getBinder());
            return;
        }
        parcel.writeStrongBinder(this.zzaNe.asBinder());
    }
    
    private final class zza extends zzb.zza
    {
        Handler handler;
        
        zza(final Handler handler) {
            this.handler = handler;
        }
        
        public void send(final Message message) throws RemoteException {
            message.arg2 = Binder.getCallingUid();
            this.handler.dispatchMessage(message);
        }
    }
}
