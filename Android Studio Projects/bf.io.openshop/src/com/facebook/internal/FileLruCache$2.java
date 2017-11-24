package com.facebook.internal;

import java.io.*;

class FileLruCache$2 implements Runnable {
    final /* synthetic */ File[] val$filesToDelete;
    
    @Override
    public void run() {
        final File[] val$filesToDelete = this.val$filesToDelete;
        for (int length = val$filesToDelete.length, i = 0; i < length; ++i) {
            val$filesToDelete[i].delete();
        }
    }
}