package android.support.v4.view;

import android.view.*;
import android.view.accessibility.*;

static class ViewParentCompatICSImpl extends ViewParentCompatStubImpl
{
    @Override
    public boolean requestSendAccessibilityEvent(final ViewParent viewParent, final View view, final AccessibilityEvent accessibilityEvent) {
        return ViewParentCompatICS.requestSendAccessibilityEvent(viewParent, view, accessibilityEvent);
    }
}
