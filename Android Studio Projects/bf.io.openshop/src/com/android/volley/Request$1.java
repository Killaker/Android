package com.android.volley;

class Request$1 implements Runnable {
    final /* synthetic */ String val$tag;
    final /* synthetic */ long val$threadId;
    
    @Override
    public void run() {
        Request.access$000(Request.this).add(this.val$tag, this.val$threadId);
        Request.access$000(Request.this).finish(this.toString());
    }
}