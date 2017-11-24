package com.facebook.share.internal;

import com.facebook.internal.*;

public enum LikeDialogFeature implements DialogFeature
{
    LIKE_DIALOG(20140701);
    
    private int minVersion;
    
    private LikeDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    @Override
    public String getAction() {
        return "com.facebook.platform.action.request.LIKE_DIALOG";
    }
    
    @Override
    public int getMinVersion() {
        return this.minVersion;
    }
}
