package android.support.v4.view.accessibility;

import android.graphics.*;

private static class AccessibilityWindowInfoApi21Impl extends AccessibilityWindowInfoStubImpl
{
    @Override
    public void getBoundsInScreen(final Object o, final Rect rect) {
        AccessibilityWindowInfoCompatApi21.getBoundsInScreen(o, rect);
    }
    
    @Override
    public Object getChild(final Object o, final int n) {
        return AccessibilityWindowInfoCompatApi21.getChild(o, n);
    }
    
    @Override
    public int getChildCount(final Object o) {
        return AccessibilityWindowInfoCompatApi21.getChildCount(o);
    }
    
    @Override
    public int getId(final Object o) {
        return AccessibilityWindowInfoCompatApi21.getId(o);
    }
    
    @Override
    public int getLayer(final Object o) {
        return AccessibilityWindowInfoCompatApi21.getLayer(o);
    }
    
    @Override
    public Object getParent(final Object o) {
        return AccessibilityWindowInfoCompatApi21.getParent(o);
    }
    
    @Override
    public Object getRoot(final Object o) {
        return AccessibilityWindowInfoCompatApi21.getRoot(o);
    }
    
    @Override
    public int getType(final Object o) {
        return AccessibilityWindowInfoCompatApi21.getType(o);
    }
    
    @Override
    public boolean isAccessibilityFocused(final Object o) {
        return AccessibilityWindowInfoCompatApi21.isAccessibilityFocused(o);
    }
    
    @Override
    public boolean isActive(final Object o) {
        return AccessibilityWindowInfoCompatApi21.isActive(o);
    }
    
    @Override
    public boolean isFocused(final Object o) {
        return AccessibilityWindowInfoCompatApi21.isFocused(o);
    }
    
    @Override
    public Object obtain() {
        return AccessibilityWindowInfoCompatApi21.obtain();
    }
    
    @Override
    public Object obtain(final Object o) {
        return AccessibilityWindowInfoCompatApi21.obtain(o);
    }
    
    @Override
    public void recycle(final Object o) {
        AccessibilityWindowInfoCompatApi21.recycle(o);
    }
}
