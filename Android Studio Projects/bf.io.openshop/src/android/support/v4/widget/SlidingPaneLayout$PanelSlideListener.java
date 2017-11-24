package android.support.v4.widget;

import android.view.*;

public interface PanelSlideListener
{
    void onPanelClosed(final View p0);
    
    void onPanelOpened(final View p0);
    
    void onPanelSlide(final View p0, final float p1);
}
