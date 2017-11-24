package com.facebook.login.widget;

import android.content.*;

class LoginButton$LoginClickListener$1 implements DialogInterface$OnClickListener {
    public void onClick(final DialogInterface dialogInterface, final int n) {
        LoginClickListener.this.this$0.getLoginManager().logOut();
    }
}