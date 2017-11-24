package com.android.volley;

import java.util.concurrent.*;
import android.os.*;

class ExecutorDelivery$1 implements Executor {
    final /* synthetic */ Handler val$handler;
    
    @Override
    public void execute(final Runnable runnable) {
        this.val$handler.post(runnable);
    }
}