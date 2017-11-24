package com.facebook.share.internal;

import com.facebook.internal.*;

public enum ShareDialogFeature implements DialogFeature
{
    PHOTOS(20140204), 
    SHARE_DIALOG(20130618), 
    VIDEO(20141028);
    
    private int minVersion;
    
    private ShareDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    @Override
    public String getAction() {
        return "com.facebook.platform.action.request.FEED_DIALOG";
    }
    
    @Override
    public int getMinVersion() {
        return this.minVersion;
    }
}
