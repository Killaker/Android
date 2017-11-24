package com.facebook.applinks;

import java.util.*;
import android.net.*;
import bolts.*;

class FacebookAppLinkResolver$1 implements Continuation<Map<Uri, AppLink>, AppLink> {
    final /* synthetic */ Uri val$uri;
    
    @Override
    public AppLink then(final Task<Map<Uri, AppLink>> task) throws Exception {
        return task.getResult().get(this.val$uri);
    }
}