package android.support.v4.view.accessibility;

import java.util.*;
import android.os.*;

interface AccessibilityNodeInfoBridge
{
    Object createAccessibilityNodeInfo(final int p0);
    
    List<Object> findAccessibilityNodeInfosByText(final String p0, final int p1);
    
    boolean performAction(final int p0, final int p1, final Bundle p2);
}
