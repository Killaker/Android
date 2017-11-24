package com.google.android.gms.tagmanager;

import android.content.*;
import android.content.res.*;

class TagManager$3 implements ComponentCallbacks2 {
    public void onConfigurationChanged(final Configuration configuration) {
    }
    
    public void onLowMemory() {
    }
    
    public void onTrimMemory(final int n) {
        if (n == 20) {
            TagManager.this.dispatch();
        }
    }
}