package com.facebook;

import java.util.concurrent.*;

static final class FacebookSdk$3 implements Callable<Void> {
    final /* synthetic */ InitializeCallback val$callback;
    
    @Override
    public Void call() throws Exception {
        AccessTokenManager.getInstance().loadCurrentAccessToken();
        ProfileManager.getInstance().loadCurrentProfile();
        if (AccessToken.getCurrentAccessToken() != null && Profile.getCurrentProfile() == null) {
            Profile.fetchProfileForCurrentAccessToken();
        }
        if (this.val$callback != null) {
            this.val$callback.onInitialized();
        }
        return null;
    }
}