package android.support.v4.view.accessibility;

import android.view.accessibility.*;
import java.util.*;
import android.accessibilityservice.*;

interface AccessibilityManagerVersionImpl
{
    boolean addAccessibilityStateChangeListener(final AccessibilityManager p0, final AccessibilityStateChangeListenerCompat p1);
    
    List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager p0, final int p1);
    
    List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager p0);
    
    boolean isTouchExplorationEnabled(final AccessibilityManager p0);
    
    Object newAccessiblityStateChangeListener(final AccessibilityStateChangeListenerCompat p0);
    
    boolean removeAccessibilityStateChangeListener(final AccessibilityManager p0, final AccessibilityStateChangeListenerCompat p1);
}
