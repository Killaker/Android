package com.facebook.login;

import com.facebook.internal.*;
import com.facebook.*;
import android.content.*;

class LoginManager$1 implements Callback {
    final /* synthetic */ FacebookCallback val$callback;
    
    @Override
    public boolean onActivityResult(final int n, final Intent intent) {
        return LoginManager.this.onActivityResult(n, intent, this.val$callback);
    }
}