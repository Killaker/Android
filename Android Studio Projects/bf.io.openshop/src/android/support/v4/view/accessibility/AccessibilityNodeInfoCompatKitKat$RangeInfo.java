package android.support.v4.view.accessibility;

import android.view.accessibility.*;

static class RangeInfo
{
    static float getCurrent(final Object o) {
        return ((AccessibilityNodeInfo$RangeInfo)o).getCurrent();
    }
    
    static float getMax(final Object o) {
        return ((AccessibilityNodeInfo$RangeInfo)o).getMax();
    }
    
    static float getMin(final Object o) {
        return ((AccessibilityNodeInfo$RangeInfo)o).getMin();
    }
    
    static int getType(final Object o) {
        return ((AccessibilityNodeInfo$RangeInfo)o).getType();
    }
}
