package com.facebook;

class ProgressOutputStream$1 implements Runnable {
    final /* synthetic */ GraphRequestBatch.OnProgressCallback val$progressCallback;
    
    @Override
    public void run() {
        this.val$progressCallback.onBatchProgress(ProgressOutputStream.access$000(ProgressOutputStream.this), ProgressOutputStream.access$100(ProgressOutputStream.this), ProgressOutputStream.access$200(ProgressOutputStream.this));
    }
}