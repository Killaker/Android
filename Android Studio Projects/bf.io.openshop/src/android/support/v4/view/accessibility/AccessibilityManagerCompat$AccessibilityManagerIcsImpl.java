package android.support.v4.view.accessibility;

import android.view.accessibility.*;
import java.util.*;
import android.accessibilityservice.*;

static class AccessibilityManagerIcsImpl extends AccessibilityManagerStubImpl
{
    @Override
    public boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
        return AccessibilityManagerCompatIcs.addAccessibilityStateChangeListener(accessibilityManager, accessibilityStateChangeListenerCompat.mListener);
    }
    
    @Override
    public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager accessibilityManager, final int n) {
        return AccessibilityManagerCompatIcs.getEnabledAccessibilityServiceList(accessibilityManager, n);
    }
    
    @Override
    public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager accessibilityManager) {
        return AccessibilityManagerCompatIcs.getInstalledAccessibilityServiceList(accessibilityManager);
    }
    
    @Override
    public boolean isTouchExplorationEnabled(final AccessibilityManager accessibilityManager) {
        return AccessibilityManagerCompatIcs.isTouchExplorationEnabled(accessibilityManager);
    }
    
    @Override
    public Object newAccessiblityStateChangeListener(final AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
        return AccessibilityManagerCompatIcs.newAccessibilityStateChangeListener((AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerBridge)new AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerBridge() {
            @Override
            public void onAccessibilityStateChanged(final boolean b) {
                accessibilityStateChangeListenerCompat.onAccessibilityStateChanged(b);
            }
        });
    }
    
    @Override
    public boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
        return AccessibilityManagerCompatIcs.removeAccessibilityStateChangeListener(accessibilityManager, accessibilityStateChangeListenerCompat.mListener);
    }
}
