package android.support.v4.view;

import android.view.accessibility.*;
import android.support.v4.view.accessibility.*;
import android.view.*;
import android.os.*;

static class AccessibilityDelegateJellyBeanImpl extends AccessibilityDelegateIcsImpl
{
    @Override
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final Object o, final View view) {
        final Object accessibilityNodeProvider = AccessibilityDelegateCompatJellyBean.getAccessibilityNodeProvider(o, view);
        if (accessibilityNodeProvider != null) {
            return new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
        }
        return null;
    }
    
    @Override
    public Object newAccessiblityDelegateBridge(final AccessibilityDelegateCompat accessibilityDelegateCompat) {
        return AccessibilityDelegateCompatJellyBean.newAccessibilityDelegateBridge((AccessibilityDelegateCompatJellyBean.AccessibilityDelegateBridgeJellyBean)new AccessibilityDelegateCompatJellyBean.AccessibilityDelegateBridgeJellyBean() {
            @Override
            public boolean dispatchPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                return accessibilityDelegateCompat.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            
            @Override
            public Object getAccessibilityNodeProvider(final View view) {
                final AccessibilityNodeProviderCompat accessibilityNodeProvider = accessibilityDelegateCompat.getAccessibilityNodeProvider(view);
                if (accessibilityNodeProvider != null) {
                    return accessibilityNodeProvider.getProvider();
                }
                return null;
            }
            
            @Override
            public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                accessibilityDelegateCompat.onInitializeAccessibilityEvent(view, accessibilityEvent);
            }
            
            @Override
            public void onInitializeAccessibilityNodeInfo(final View view, final Object o) {
                accessibilityDelegateCompat.onInitializeAccessibilityNodeInfo(view, new AccessibilityNodeInfoCompat(o));
            }
            
            @Override
            public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                accessibilityDelegateCompat.onPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            
            @Override
            public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
                return accessibilityDelegateCompat.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            
            @Override
            public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
                return accessibilityDelegateCompat.performAccessibilityAction(view, n, bundle);
            }
            
            @Override
            public void sendAccessibilityEvent(final View view, final int n) {
                accessibilityDelegateCompat.sendAccessibilityEvent(view, n);
            }
            
            @Override
            public void sendAccessibilityEventUnchecked(final View view, final AccessibilityEvent accessibilityEvent) {
                accessibilityDelegateCompat.sendAccessibilityEventUnchecked(view, accessibilityEvent);
            }
        });
    }
    
    @Override
    public boolean performAccessibilityAction(final Object o, final View view, final int n, final Bundle bundle) {
        return AccessibilityDelegateCompatJellyBean.performAccessibilityAction(o, view, n, bundle);
    }
}
