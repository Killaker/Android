package com.facebook;

import android.view.*;

class FacebookButtonBase$1 implements View$OnClickListener {
    public void onClick(final View view) {
        FacebookButtonBase.access$000(FacebookButtonBase.this, FacebookButtonBase.this.getContext());
        if (FacebookButtonBase.access$100(FacebookButtonBase.this) != null) {
            FacebookButtonBase.access$100(FacebookButtonBase.this).onClick(view);
        }
        else if (FacebookButtonBase.access$200(FacebookButtonBase.this) != null) {
            FacebookButtonBase.access$200(FacebookButtonBase.this).onClick(view);
        }
    }
}