package com.squareup.picasso;

import android.os.*;

class Stats$StatsHandler$1 implements Runnable {
    final /* synthetic */ Message val$msg;
    
    @Override
    public void run() {
        throw new AssertionError((Object)("Unhandled stats message." + this.val$msg.what));
    }
}