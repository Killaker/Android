package android.support.v4.view.accessibility;

import android.view.accessibility.*;

static final class AccessibilityManagerCompatIcs$1 implements AccessibilityManager$AccessibilityStateChangeListener {
    final /* synthetic */ AccessibilityStateChangeListenerBridge val$bridge;
    
    public void onAccessibilityStateChanged(final boolean b) {
        this.val$bridge.onAccessibilityStateChanged(b);
    }
}