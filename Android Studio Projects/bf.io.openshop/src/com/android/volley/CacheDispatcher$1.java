package com.android.volley;

class CacheDispatcher$1 implements Runnable {
    final /* synthetic */ Request val$request;
    
    @Override
    public void run() {
        try {
            CacheDispatcher.access$000(CacheDispatcher.this).put(this.val$request);
        }
        catch (InterruptedException ex) {}
    }
}