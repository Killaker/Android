package com.google.android.gms.maps.internal;

import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.*;

public interface StreetViewLifecycleDelegate extends LifecycleDelegate
{
    void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback p0);
}
