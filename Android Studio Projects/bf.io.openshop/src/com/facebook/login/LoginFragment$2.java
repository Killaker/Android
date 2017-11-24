package com.facebook.login;

import android.view.*;
import com.facebook.*;

class LoginFragment$2 implements BackgroundProcessingListener {
    final /* synthetic */ View val$view;
    
    @Override
    public void onBackgroundProcessingStarted() {
        this.val$view.findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(0);
    }
    
    @Override
    public void onBackgroundProcessingStopped() {
        this.val$view.findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(8);
    }
}