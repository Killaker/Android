package com.facebook.share.widget;

import android.view.*;

class ShareButtonBase$1 implements View$OnClickListener {
    public void onClick(final View view) {
        ShareButtonBase.access$000(ShareButtonBase.this, view);
        ShareButtonBase.this.getDialog().show(ShareButtonBase.this.getShareContent());
    }
}