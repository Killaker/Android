package com.android.volley.toolbox;

class NetworkImageView$1$1 implements Runnable {
    final /* synthetic */ ImageContainer val$response;
    
    @Override
    public void run() {
        ImageListener.this.onResponse(this.val$response, false);
    }
}