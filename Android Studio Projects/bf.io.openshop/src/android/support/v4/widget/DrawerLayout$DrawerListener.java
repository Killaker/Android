package android.support.v4.widget;

import android.view.*;

public interface DrawerListener
{
    void onDrawerClosed(final View p0);
    
    void onDrawerOpened(final View p0);
    
    void onDrawerSlide(final View p0, final float p1);
    
    void onDrawerStateChanged(final int p0);
}
