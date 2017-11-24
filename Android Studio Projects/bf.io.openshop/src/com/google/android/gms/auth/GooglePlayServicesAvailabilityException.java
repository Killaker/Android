package com.google.android.gms.auth;

import android.content.*;

public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException
{
    private final int zzVn;
    
    GooglePlayServicesAvailabilityException(final int zzVn, final String s, final Intent intent) {
        super(s, intent);
        this.zzVn = zzVn;
    }
    
    public int getConnectionStatusCode() {
        return this.zzVn;
    }
}
