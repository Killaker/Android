package com.google.android.gms.maps;

import android.location.*;

public interface LocationSource
{
    void activate(final OnLocationChangedListener p0);
    
    void deactivate();
    
    public interface OnLocationChangedListener
    {
        void onLocationChanged(final Location p0);
    }
}
