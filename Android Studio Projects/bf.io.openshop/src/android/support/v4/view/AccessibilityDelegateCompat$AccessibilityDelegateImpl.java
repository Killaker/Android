package android.support.v4.view;

import android.view.accessibility.*;
import android.support.v4.view.accessibility.*;
import android.view.*;
import android.os.*;

interface AccessibilityDelegateImpl
{
    boolean dispatchPopulateAccessibilityEvent(final Object p0, final View p1, final AccessibilityEvent p2);
    
    AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final Object p0, final View p1);
    
    Object newAccessiblityDelegateBridge(final AccessibilityDelegateCompat p0);
    
    Object newAccessiblityDelegateDefaultImpl();
    
    void onInitializeAccessibilityEvent(final Object p0, final View p1, final AccessibilityEvent p2);
    
    void onInitializeAccessibilityNodeInfo(final Object p0, final View p1, final AccessibilityNodeInfoCompat p2);
    
    void onPopulateAccessibilityEvent(final Object p0, final View p1, final AccessibilityEvent p2);
    
    boolean onRequestSendAccessibilityEvent(final Object p0, final ViewGroup p1, final View p2, final AccessibilityEvent p3);
    
    boolean performAccessibilityAction(final Object p0, final View p1, final int p2, final Bundle p3);
    
    void sendAccessibilityEvent(final Object p0, final View p1, final int p2);
    
    void sendAccessibilityEventUnchecked(final Object p0, final View p1, final AccessibilityEvent p2);
}
