package android.support.v4.view.accessibility;

import android.view.accessibility.*;
import java.util.*;
import android.os.*;

static final class AccessibilityNodeProviderCompatJellyBean$1 extends AccessibilityNodeProvider {
    final /* synthetic */ AccessibilityNodeInfoBridge val$bridge;
    
    public AccessibilityNodeInfo createAccessibilityNodeInfo(final int n) {
        return (AccessibilityNodeInfo)this.val$bridge.createAccessibilityNodeInfo(n);
    }
    
    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(final String s, final int n) {
        return (List<AccessibilityNodeInfo>)this.val$bridge.findAccessibilityNodeInfosByText(s, n);
    }
    
    public boolean performAction(final int n, final int n2, final Bundle bundle) {
        return this.val$bridge.performAction(n, n2, bundle);
    }
}