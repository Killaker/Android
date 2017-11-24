package com.squareup.picasso;

import android.os.*;

private static class PicassoThread extends Thread
{
    public PicassoThread(final Runnable runnable) {
        super(runnable);
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(10);
        super.run();
    }
}
