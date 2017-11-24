package android.support.v7.app;

import android.support.v4.app.*;

public interface TabListener
{
    void onTabReselected(final Tab p0, final FragmentTransaction p1);
    
    void onTabSelected(final Tab p0, final FragmentTransaction p1);
    
    void onTabUnselected(final Tab p0, final FragmentTransaction p1);
}
