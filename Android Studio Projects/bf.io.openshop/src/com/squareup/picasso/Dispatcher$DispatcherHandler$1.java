package com.squareup.picasso;

import android.os.*;

class Dispatcher$DispatcherHandler$1 implements Runnable {
    final /* synthetic */ Message val$msg;
    
    @Override
    public void run() {
        throw new AssertionError((Object)("Unknown handler message received: " + this.val$msg.what));
    }
}