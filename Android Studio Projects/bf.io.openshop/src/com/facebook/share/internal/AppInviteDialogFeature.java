package com.facebook.share.internal;

import com.facebook.internal.*;

public enum AppInviteDialogFeature implements DialogFeature
{
    APP_INVITES_DIALOG(20140701);
    
    private int minVersion;
    
    private AppInviteDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    @Override
    public String getAction() {
        return "com.facebook.platform.action.request.APPINVITES_DIALOG";
    }
    
    @Override
    public int getMinVersion() {
        return this.minVersion;
    }
}
