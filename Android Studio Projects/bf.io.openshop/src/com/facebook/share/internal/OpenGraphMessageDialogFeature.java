package com.facebook.share.internal;

import com.facebook.internal.*;

public enum OpenGraphMessageDialogFeature implements DialogFeature
{
    OG_MESSAGE_DIALOG(20140204);
    
    private int minVersion;
    
    private OpenGraphMessageDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    @Override
    public String getAction() {
        return "com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG";
    }
    
    @Override
    public int getMinVersion() {
        return this.minVersion;
    }
}
