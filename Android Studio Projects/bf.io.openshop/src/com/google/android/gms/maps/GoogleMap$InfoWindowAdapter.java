package com.google.android.gms.maps;

import com.google.android.gms.maps.model.*;
import android.view.*;

public interface InfoWindowAdapter
{
    View getInfoContents(final Marker p0);
    
    View getInfoWindow(final Marker p0);
}
