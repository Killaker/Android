package android.support.v4.view.accessibility;

import android.graphics.*;

private interface AccessibilityWindowInfoImpl
{
    void getBoundsInScreen(final Object p0, final Rect p1);
    
    Object getChild(final Object p0, final int p1);
    
    int getChildCount(final Object p0);
    
    int getId(final Object p0);
    
    int getLayer(final Object p0);
    
    Object getParent(final Object p0);
    
    Object getRoot(final Object p0);
    
    int getType(final Object p0);
    
    boolean isAccessibilityFocused(final Object p0);
    
    boolean isActive(final Object p0);
    
    boolean isFocused(final Object p0);
    
    Object obtain();
    
    Object obtain(final Object p0);
    
    void recycle(final Object p0);
}
