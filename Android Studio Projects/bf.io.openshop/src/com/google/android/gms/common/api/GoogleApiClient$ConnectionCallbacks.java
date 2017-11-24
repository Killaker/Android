package com.google.android.gms.common.api;

import android.os.*;
import android.support.annotation.*;

public interface ConnectionCallbacks
{
    public static final int CAUSE_NETWORK_LOST = 2;
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    
    void onConnected(@Nullable final Bundle p0);
    
    void onConnectionSuspended(final int p0);
}
