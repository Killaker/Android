package com.google.android.gms.maps.model;

import android.os.*;

public final class RuntimeRemoteException extends RuntimeException
{
    public RuntimeRemoteException(final RemoteException ex) {
        super((Throwable)ex);
    }
}
