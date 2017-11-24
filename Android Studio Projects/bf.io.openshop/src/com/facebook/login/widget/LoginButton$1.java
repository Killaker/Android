package com.facebook.login.widget;

import com.facebook.internal.*;

class LoginButton$1 implements Runnable {
    final /* synthetic */ String val$appId;
    
    @Override
    public void run() {
        LoginButton.access$100(LoginButton.this).runOnUiThread((Runnable)new Runnable() {
            final /* synthetic */ Utility.FetchedAppSettings val$settings = Utility.queryAppSettings(LoginButton$1.this.val$appId, false);
            
            @Override
            public void run() {
                LoginButton.access$000(LoginButton.this, this.val$settings);
            }
        });
    }
}