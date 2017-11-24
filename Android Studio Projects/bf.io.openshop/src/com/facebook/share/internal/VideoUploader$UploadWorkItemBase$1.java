package com.facebook.share.internal;

class VideoUploader$UploadWorkItemBase$1 implements Runnable {
    @Override
    public void run() {
        UploadWorkItemBase.this.enqueueRetry(1 + UploadWorkItemBase.this.completedRetries);
    }
}