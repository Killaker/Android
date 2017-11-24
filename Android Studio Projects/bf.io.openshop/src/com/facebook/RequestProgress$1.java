package com.facebook;

class RequestProgress$1 implements Runnable {
    final /* synthetic */ GraphRequest.OnProgressCallback val$callbackCopy;
    final /* synthetic */ long val$currentCopy;
    final /* synthetic */ long val$maxProgressCopy;
    
    @Override
    public void run() {
        this.val$callbackCopy.onProgress(this.val$currentCopy, this.val$maxProgressCopy);
    }
}