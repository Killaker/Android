package com.facebook.share.internal;

import com.facebook.share.widget.*;

private static class CreateLikeActionControllerWorkItem implements Runnable
{
    private CreationCallback callback;
    private String objectId;
    private LikeView.ObjectType objectType;
    
    CreateLikeActionControllerWorkItem(final String objectId, final LikeView.ObjectType objectType, final CreationCallback callback) {
        this.objectId = objectId;
        this.objectType = objectType;
        this.callback = callback;
    }
    
    @Override
    public void run() {
        LikeActionController.access$2700(this.objectId, this.objectType, this.callback);
    }
}
