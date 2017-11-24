package com.facebook;

class AccessTokenManager$1 implements Runnable {
    final /* synthetic */ AccessToken.AccessTokenRefreshCallback val$callback;
    
    @Override
    public void run() {
        AccessTokenManager.access$000(AccessTokenManager.this, this.val$callback);
    }
}