package com.facebook.internal;

import android.view.*;

class WebDialog$4 implements View$OnTouchListener {
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (!view.hasFocus()) {
            view.requestFocus();
        }
        return false;
    }
}