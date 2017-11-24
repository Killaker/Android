package android.support.v4.view.accessibility;

import android.view.accessibility.*;

interface AccessibilityEventVersionImpl
{
    void appendRecord(final AccessibilityEvent p0, final Object p1);
    
    int getContentChangeTypes(final AccessibilityEvent p0);
    
    Object getRecord(final AccessibilityEvent p0, final int p1);
    
    int getRecordCount(final AccessibilityEvent p0);
    
    void setContentChangeTypes(final AccessibilityEvent p0, final int p1);
}
