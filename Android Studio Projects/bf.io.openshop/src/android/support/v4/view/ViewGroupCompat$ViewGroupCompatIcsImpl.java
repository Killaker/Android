package android.support.v4.view;

import android.view.*;
import android.view.accessibility.*;

static class ViewGroupCompatIcsImpl extends ViewGroupCompatHCImpl
{
    @Override
    public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
        return ViewGroupCompatIcs.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }
}
