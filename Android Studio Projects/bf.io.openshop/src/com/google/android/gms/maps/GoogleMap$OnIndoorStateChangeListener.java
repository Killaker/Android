package com.google.android.gms.maps;

import com.google.android.gms.maps.model.*;

public interface OnIndoorStateChangeListener
{
    void onIndoorBuildingFocused();
    
    void onIndoorLevelActivated(final IndoorBuilding p0);
}
