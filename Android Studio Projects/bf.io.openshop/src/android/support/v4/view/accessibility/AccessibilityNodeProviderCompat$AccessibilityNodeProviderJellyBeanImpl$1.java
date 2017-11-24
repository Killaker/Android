package android.support.v4.view.accessibility;

import java.util.*;
import android.os.*;

class AccessibilityNodeProviderCompat$AccessibilityNodeProviderJellyBeanImpl$1 implements AccessibilityNodeInfoBridge {
    final /* synthetic */ AccessibilityNodeProviderCompat val$compat;
    
    @Override
    public Object createAccessibilityNodeInfo(final int n) {
        final AccessibilityNodeInfoCompat accessibilityNodeInfo = this.val$compat.createAccessibilityNodeInfo(n);
        if (accessibilityNodeInfo == null) {
            return null;
        }
        return accessibilityNodeInfo.getInfo();
    }
    
    @Override
    public List<Object> findAccessibilityNodeInfosByText(final String s, final int n) {
        final List<AccessibilityNodeInfoCompat> accessibilityNodeInfosByText = this.val$compat.findAccessibilityNodeInfosByText(s, n);
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int size = accessibilityNodeInfosByText.size(), i = 0; i < size; ++i) {
            list.add(accessibilityNodeInfosByText.get(i).getInfo());
        }
        return list;
    }
    
    @Override
    public boolean performAction(final int n, final int n2, final Bundle bundle) {
        return this.val$compat.performAction(n, n2, bundle);
    }
}