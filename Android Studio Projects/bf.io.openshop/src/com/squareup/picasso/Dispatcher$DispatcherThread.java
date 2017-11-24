package com.squareup.picasso;

import android.os.*;

static class DispatcherThread extends HandlerThread
{
    DispatcherThread() {
        super("Picasso-Dispatcher", 10);
    }
}
