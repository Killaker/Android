package android.support.v4.view.accessibility;

import android.view.accessibility.*;

static class AccessibilityEventStubImpl implements AccessibilityEventVersionImpl
{
    @Override
    public void appendRecord(final AccessibilityEvent accessibilityEvent, final Object o) {
    }
    
    @Override
    public int getContentChangeTypes(final AccessibilityEvent accessibilityEvent) {
        return 0;
    }
    
    @Override
    public Object getRecord(final AccessibilityEvent accessibilityEvent, final int n) {
        return null;
    }
    
    @Override
    public int getRecordCount(final AccessibilityEvent accessibilityEvent) {
        return 0;
    }
    
    @Override
    public void setContentChangeTypes(final AccessibilityEvent accessibilityEvent, final int n) {
    }
}
