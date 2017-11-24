package com.squareup.picasso;

import java.lang.ref.*;
import android.os.*;

private static class CleanupThread extends Thread
{
    private final Handler handler;
    private final ReferenceQueue<Object> referenceQueue;
    
    CleanupThread(final ReferenceQueue<Object> referenceQueue, final Handler handler) {
        this.referenceQueue = referenceQueue;
        this.handler = handler;
        this.setDaemon(true);
        this.setName("Picasso-refQueue");
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(10);
        try {
            while (true) {
                final Action.RequestWeakReference requestWeakReference = (Action.RequestWeakReference)this.referenceQueue.remove(1000L);
                final Message obtainMessage = this.handler.obtainMessage();
                if (requestWeakReference != null) {
                    obtainMessage.what = 3;
                    obtainMessage.obj = requestWeakReference.action;
                    this.handler.sendMessage(obtainMessage);
                }
                else {
                    obtainMessage.recycle();
                }
            }
        }
        catch (Exception ex) {
            this.handler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    throw new RuntimeException(ex);
                }
            });
        }
        catch (InterruptedException ex2) {}
    }
    
    void shutdown() {
        this.interrupt();
    }
}
