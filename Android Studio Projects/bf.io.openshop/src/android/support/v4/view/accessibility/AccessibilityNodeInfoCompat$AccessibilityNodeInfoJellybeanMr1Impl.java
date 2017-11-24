package android.support.v4.view.accessibility;

import android.view.*;

static class AccessibilityNodeInfoJellybeanMr1Impl extends AccessibilityNodeInfoJellybeanImpl
{
    @Override
    public Object getLabelFor(final Object o) {
        return AccessibilityNodeInfoCompatJellybeanMr1.getLabelFor(o);
    }
    
    @Override
    public Object getLabeledBy(final Object o) {
        return AccessibilityNodeInfoCompatJellybeanMr1.getLabeledBy(o);
    }
    
    @Override
    public void setLabelFor(final Object o, final View view) {
        AccessibilityNodeInfoCompatJellybeanMr1.setLabelFor(o, view);
    }
    
    @Override
    public void setLabelFor(final Object o, final View view, final int n) {
        AccessibilityNodeInfoCompatJellybeanMr1.setLabelFor(o, view, n);
    }
    
    @Override
    public void setLabeledBy(final Object o, final View view) {
        AccessibilityNodeInfoCompatJellybeanMr1.setLabeledBy(o, view);
    }
    
    @Override
    public void setLabeledBy(final Object o, final View view, final int n) {
        AccessibilityNodeInfoCompatJellybeanMr1.setLabeledBy(o, view, n);
    }
}
