package com.google.android.gms.common;

import android.content.*;

public class GooglePlayServicesRepairableException extends UserRecoverableException
{
    private final int zzVn;
    
    GooglePlayServicesRepairableException(final int zzVn, final String s, final Intent intent) {
        super(s, intent);
        this.zzVn = zzVn;
    }
    
    public int getConnectionStatusCode() {
        return this.zzVn;
    }
}
