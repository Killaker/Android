package com.google.android.gms.maps.internal;

import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.*;

public interface MapLifecycleDelegate extends LifecycleDelegate
{
    void getMapAsync(final OnMapReadyCallback p0);
}
