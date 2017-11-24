package com.google.android.gms.security;

import android.content.*;

public interface ProviderInstallListener
{
    void onProviderInstallFailed(final int p0, final Intent p1);
    
    void onProviderInstalled();
}
