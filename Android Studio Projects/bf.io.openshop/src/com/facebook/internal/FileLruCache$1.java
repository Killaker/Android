package com.facebook.internal;

import java.io.*;

class FileLruCache$1 implements StreamCloseCallback {
    final /* synthetic */ File val$buffer;
    final /* synthetic */ long val$bufferFileCreateTime;
    final /* synthetic */ String val$key;
    
    @Override
    public void onClose() {
        if (this.val$bufferFileCreateTime < FileLruCache.access$000(FileLruCache.this).get()) {
            this.val$buffer.delete();
            return;
        }
        FileLruCache.access$100(FileLruCache.this, this.val$key, this.val$buffer);
    }
}