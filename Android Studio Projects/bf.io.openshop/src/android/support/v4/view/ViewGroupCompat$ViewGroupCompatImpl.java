package android.support.v4.view;

import android.view.*;
import android.view.accessibility.*;

interface ViewGroupCompatImpl
{
    int getLayoutMode(final ViewGroup p0);
    
    int getNestedScrollAxes(final ViewGroup p0);
    
    boolean isTransitionGroup(final ViewGroup p0);
    
    boolean onRequestSendAccessibilityEvent(final ViewGroup p0, final View p1, final AccessibilityEvent p2);
    
    void setLayoutMode(final ViewGroup p0, final int p1);
    
    void setMotionEventSplittingEnabled(final ViewGroup p0, final boolean p1);
    
    void setTransitionGroup(final ViewGroup p0, final boolean p1);
}
