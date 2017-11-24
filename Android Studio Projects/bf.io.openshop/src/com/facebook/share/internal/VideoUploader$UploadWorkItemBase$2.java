package com.facebook.share.internal;

import com.facebook.*;

class VideoUploader$UploadWorkItemBase$2 implements Runnable {
    final /* synthetic */ FacebookException val$error;
    final /* synthetic */ String val$videoId;
    
    @Override
    public void run() {
        VideoUploader.access$900(UploadWorkItemBase.this.uploadContext, this.val$error, this.val$videoId);
    }
}