package com.squareup.picasso;

import android.os.*;

static final class Utils$1 extends Handler {
    public void handleMessage(final Message message) {
        this.sendMessageDelayed(this.obtainMessage(), 1000L);
    }
}