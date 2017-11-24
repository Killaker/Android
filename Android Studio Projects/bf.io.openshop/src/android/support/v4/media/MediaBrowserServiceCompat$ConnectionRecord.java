package android.support.v4.media;

import android.os.*;
import java.util.*;

private class ConnectionRecord
{
    ServiceCallbacks callbacks;
    String pkg;
    BrowserRoot root;
    Bundle rootHints;
    HashMap<String, List<Bundle>> subscriptions;
    
    private ConnectionRecord() {
        this.subscriptions = new HashMap<String, List<Bundle>>();
    }
}
