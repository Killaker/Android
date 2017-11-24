package com.facebook.internal;

import java.util.concurrent.*;

class LockOnGetVariable$1 implements Callable<Void> {
    final /* synthetic */ Callable val$callable;
    
    @Override
    public Void call() throws Exception {
        try {
            LockOnGetVariable.access$002(LockOnGetVariable.this, this.val$callable.call());
            return null;
        }
        finally {
            LockOnGetVariable.access$100(LockOnGetVariable.this).countDown();
        }
    }
}