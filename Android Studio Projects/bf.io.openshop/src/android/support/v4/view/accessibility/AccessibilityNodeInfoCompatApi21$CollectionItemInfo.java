package android.support.v4.view.accessibility;

import android.view.accessibility.*;

static class CollectionItemInfo
{
    public static boolean isSelected(final Object o) {
        return ((AccessibilityNodeInfo$CollectionItemInfo)o).isSelected();
    }
}
