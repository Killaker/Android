package com.facebook.internal;

import android.content.*;

class WebDialog$1 implements DialogInterface$OnCancelListener {
    public void onCancel(final DialogInterface dialogInterface) {
        WebDialog.this.cancel();
    }
}